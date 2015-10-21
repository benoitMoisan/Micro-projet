package mailBox;

import java.util.Collection;
import java.util.Iterator;
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
			boolean again = true;
			
			while(again) {
				
				afficherMenu();
				
				switch(menu) {
					case 1 : 
						ajouterUtilisateur(iMailBoxManager);
						break;
					case 2 :
						supprimerUtilisateur(iMailBoxManager);
						break;
					case 3 : 
						lireAllMailAUtilisateur(iMailBoxManager);
						break;
					case 4 : 
						lireNewMailAUtilisateur(iMailBoxManager);
						break;
					case 5 :
						supprimerUnMail(iMailBoxManager);
						break;
					case 6 :
						supprimerAllMail(iMailBoxManager);
						break;	
					case 7 :
						envoyerUnMail(iMailBoxManager);
						break;
					case 8 :
						again = false;
						break;
					default :
						break;
				}
			}
				
			} catch(Exception e) {
	            e.printStackTrace();
	        }
		}
	
	public static void afficherMenu() {
		System.out.println();
		System.out.println("Bienvenue sur la console d'administration !");
		System.out.println();
		System.out.println("1 - Ajouter un utilisateur");
		System.out.println("2 - Supprimer un utilisateur");
		System.out.println("3 - Lire tous les mails d'un utilisateur");
		System.out.println("4 - Lire les nouveaux mails d'un utilisateur");
		System.out.println("5 - Supprimer un mail d'un utilisateur");
		System.out.println("6 - Supprimer tous les mails d'un utilisateur");
		System.out.println("7 - Envoyer un mail");
		System.out.println("8 - Quitter l'application");
		System.out.println();
		System.out.print("Veuillez saisir le numéro de l'action à executer : ");
		menu = sc.nextInt();
		sc.nextLine();
		System.out.println();	
	}
	
	public static void ajouterUtilisateur(IMailBoxManager iMailBoxManager) {
		String nomUtilisateur;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur à ajouer : ");
		nomUtilisateur = sc.nextLine();
		iMailBoxManager.addUser(nomUtilisateur);
		System.out.println();
	}
	
	public static void supprimerUtilisateur(IMailBoxManager iMailBoxManager) {
		String nomUtilisateur;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur à supprimer : ");
		nomUtilisateur = sc.nextLine();
		iMailBoxManager.removeUser(nomUtilisateur);
		System.out.println();
	}
	
	public static void lireAllMailAUtilisateur(IMailBoxManager iMailBoxManager) {
String nomUtilisateur;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez voir les mails : ");
		nomUtilisateur = sc.nextLine();
		Collection<Message> list = iMailBoxManager.readAUserAllMessages(nomUtilisateur);
		Iterator<Message> it = list.iterator();
		while(it.hasNext()) {
			Message message = it.next();
			System.out.println("Id : "+message.getId()+" Sender name : "+message.getSenderName()+" Subject : "+message.getSubject()+" Body : "+message.getBody());
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void lireNewMailAUtilisateur(IMailBoxManager iMailBoxManager) {
		String nomUtilisateur;
				
				System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez voir les mails : ");
				nomUtilisateur = sc.nextLine();
				Collection<Message> list = iMailBoxManager.readAUserNewMessages(nomUtilisateur);
				Iterator<Message> it = list.iterator();
				while(it.hasNext()) {
					Message message = it.next();
					System.out.println("Id : "+message.getId()+" Sender name : "+message.getSenderName()+" Subject : "+message.getSubject()+" Body : "+message.getBody());
					System.out.println();
				}
				
				System.out.println();
			}
	
	public static void supprimerUnMail(IMailBoxManager iMailBoxManager) {
		String nomUtilisateur;
		int id;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez supprimer un mail : ");
		nomUtilisateur = sc.nextLine();
		System.out.print("Veuillez saisir l'id du message à supprimer : ");
		id = sc.nextInt();
		sc.nextLine();
		iMailBoxManager.deleteAUserMessage(nomUtilisateur, id);
	}
	
	public static void supprimerAllMail(IMailBoxManager iMailBoxManager) {
		String nomUtilisateur;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez supprimer les mails lus : ");
		nomUtilisateur = sc.nextLine();
		iMailBoxManager.deleteAUserReadMessages(nomUtilisateur);
	}
	
	public static void envoyerUnMail(IMailBoxManager iMailBoxManager) {
		String senderName;
		String receiverName;
		String body;
		String subject;
		Message message;
		
		System.out.print("Veuillez saisir le nom de l'envoyeur : ");
		senderName = sc.nextLine();
		System.out.println();
		System.out.print("Veuillez saisir le nom du receveur : ");
		receiverName = sc.nextLine();
		System.out.println();
		System.out.print("Veuillez saisir le sujet : ");
		subject = sc.nextLine();
		System.out.println();
		System.out.print("Veuillez saisir le body : ");
		body = sc.nextLine();
		System.out.println();
		
		message = new Message(senderName, receiverName, subject, body);
		message.setDate(new java.util.Date());
		
		iMailBoxManager.sendAMessageToABox(message);
	}
	
}
