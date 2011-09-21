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

import java.util.*;
import java.lang.Throwable;

public class ParseOptions {
	private String DEFAULT_FILE = System.getProperty("user.home")+"/.pb.properties";
	private boolean VERBOSE = false;
	
	public ParseOptions(String args[]) {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-v")) {
					VERBOSE = true;
				} else if (args[i].equals("-h")) {
					printHelp();
					System.exit(0);
				} else if (args[i].equals("-p") && ( i+1 < args.length )) {
					DEFAULT_FILE = args[i+1];
				}
			}
		}
	}

	public boolean getVerbose() {
		return VERBOSE;
	}

	public String getDefaultFile() {
		return DEFAULT_FILE;
	}

	public void printHelp() {
		System.out.println("NAME");
		System.out.println("\tPasswordBouncer - Returns a password over TCP\n");

		System.out.println("SYNOPSIS");
		System.out.println("\t./PasswordBouncer.jar [-v] [-h] [-d defaults-file]\n");

		System.out.println("DESCRIPTION");
		System.out.println("\tNeed to have a password handy for scripts and can't keep it on the filesystem?");
		System.out.println("\tEnter your password, then background the process using whatever method your OS supports.");
		System.out.println("\tTo retrieve your password send \"MYPASSWORD\" to localhost port 10128 or any other address");
		System.out.println("\tspecified via configuration file.\n");

		System.out.println("OPTIONS");
		System.out.println("\t-v\tEnables verbose output\n");

                System.out.println("\t-h\tThis help message\n");

                System.out.println("\t-p\tUse an alternate properties file as the configuration source. Defaults to ~/.pb.properties or none.\n");

                System.out.println("FILE");
                System.out.println("\t~/.pb.properties");
                System.out.println("\t\tThis file need not exist for the default behavior of binding to 127.0.0.1:10128 and using \"MYPASSWORD\".");
                System.out.println("\t\tExpects options to be in the <key>=<value> format.\n");

                System.out.println("\t\tSample config:\n");

                System.out.println("\t\t\tport=10128");
                System.out.println("\t\t\taddress=127.0.0.1");
                System.out.println("\t\t\tstring=MYPASSWORD\n");

                System.out.println("BUGS");
                System.out.println("\tNone found yet.\n");

                System.out.println("WARRANTY");
                System.out.println("\tTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.\n");

                System.out.println("AUTHOR");
                System.out.println("\tGreg Walters <allrightname at gmail>");

	}
}
