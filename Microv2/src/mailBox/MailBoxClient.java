package mailBox;

import java.util.Scanner;

import javax.naming.InitialContext;

import directory.IUserDirectory;

public class MailBoxClient {

	private final static Scanner sc = new Scanner(System.in); 
	private static int menu;
	
	public static void main(String args[]) {
		
	
		try {
			InitialContext ic = new InitialContext();
			IMailBoxManager iMailBoxManager = (IMailBoxManager) ic.lookup("mailBox.IMailBoxManager");
			
			
			iMailBoxManager.addUser("yang");
			iMailBoxManager.addUser("thomas");
			
			Message message = new Message("yang", "thomas", "bonjour", "coucou");
			iMailBoxManager.sendAMessageToABox(senderName, ReceiverName, message);
			
			
			
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
		}
}
