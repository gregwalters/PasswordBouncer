/*
 *  PasswordBouncer Written by Greg Walters
 *  Copyright (C) 2011, Contegix, LLC, www.contegix.com
 *
 *  This is free software; you can redistribute it and/or modify
 *  it under the terms version 2 of the GNU General Public License as
 *  published by the Free Software Foundation. This program is distributed
 *  in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more
 *  details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA.

 *  About Contegix:
 *  Contegix provides high-level managed hosting solutions for enterprise 
 *  applications and infrastructure.  The company delivers proactive, 
 *  passionate support that is unparalleled in the industry. All Contegix 
 *  solutions encompass supporting dedicated hardware and operating system 
 *  management, deploying and configuring software, and offering complete 
 *  licensing management. Contegix\u2019s award-winning service is delivered 
 *  by a staff of Tier-3 engineers from its global headquarters in St. Louis, 
 *  MO. Current clients and partners include Six Apart, ReadWriteWeb, VMware
 *  and Atlassian. For additional information, visit www.contegix.com or call 
 *  1(877) 426-6834.
*/


import java.io.*; // for IOException, InputStreamReader, etc.
import java.net.*; // for Socket, ServerSock
import java.util.Properties;

public class PasswordBouncer {
	public static void main(String args[]) {
		boolean listening = true;
		ServerSocket serverSocket = null;
		ParseOptions options = new ParseOptions(args);
		boolean verbose = options.getVerbose();
		String properties_file = options.getDefaultFile();
		Properties props = new Properties();
		String file = options.getDefaultFile();
		try { 
			FileInputStream in = new FileInputStream(file);
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.err.println("Could not load properties file " + file + ".");
		}
		String port = props.getProperty("port", "10128");
		String address = props.getProperty("address", "127.0.0.1");
		String queue = props.getProperty("string", "MYPASSWORD");
		listen(listening, port, address, queue, verbose);
	}

	public static void listen(boolean listening, String port, String address, String queue, boolean verbose) {
		String password = PasswordField.readPassword("Enter your password: ");
		try {
			InetAddress inet = InetAddress.getByName(address);
			Integer iport = Integer.parseInt(port);
			Integer blog = 10;
			ServerSocket serverSocket = null;
				try {
					serverSocket = new ServerSocket(iport, blog, inet);
                			while (listening) {
			                        try {
			                                new PasswordBouncerThread(serverSocket.accept(), password, queue, verbose).run();
			                        } catch (IOException e) {
			                                e.printStackTrace();
			                        }
			                }

		                try {
		                        serverSocket.close();
		                } catch (IOException e) {
		                        e.printStackTrace();
		                }
				} catch (IOException e) {
					System.err.println("Couldn't bind on " + address + ":" + port);
					System.exit(1);
				}
		} catch(UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
	
	}

	public static String getPass() {
		String password = null;
		System.out.print("Enter your password: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			password = br.readLine();
		} catch (IOException e) {
			System.out.println("Can't read password");
			System.exit(1);
		}
		return password;
	}
}
