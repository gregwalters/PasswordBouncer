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

public class PasswordBouncer {
	public static void main(String args[]) {
		boolean listening = true;
		ServerSocket serverSocket = null;
		listen(listening);
	}

	public static void listen(boolean listening) {
		String password = getPass();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10128);
		} catch (IOException e) {
			System.err.println("Couldn't listen on port 10128.");
			System.exit(1);
		}

                while (listening) {
                        try {
                                new PasswordBouncerThread(serverSocket.accept(), password).run();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

                try {
                        serverSocket.close();
                } catch (IOException e) {
                        e.printStackTrace();
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
