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


import java.net.*;
import java.io.*;

public class PasswordBouncerThread extends Thread {
	private Socket socket = null;
	private String password = null;

	public PasswordBouncerThread(Socket socket, String password) {
		super("PasswordBouncerThread");
		this.socket = socket;
		this.password = password;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

			String inputLine, outputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("MYPASSWORD"));{
					outputLine = password;
					out.println(outputLine);
					break;
				}
			}

			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
