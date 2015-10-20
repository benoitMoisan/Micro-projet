package directory;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;

import directory.FinalUser;
import directory.IUserDirectory;
import directory.NewsGroupRights;

public class AdministrationClient {
	
	private final static Scanner sc = new Scanner(System.in); 
	private static int menu;
	
	public static void main(String args[]) {
		
	
		try {
			InitialContext ic = new InitialContext();
			IUserDirectory iuserDirectory = (IUserDirectory) ic.lookup("directoryServerPart.IUserDirectory");
			
			boolean again = true;
			
			
			while(again) {
				
				afficherMenu();
				
				switch(menu) {
					case 1 : 
						ajouterUtilisateur(iuserDirectory);
						break;
					case 2 :
						supprimerUtilisateur(iuserDirectory);
						break;
					case 3 : 
						afficherUtilisateurs(iuserDirectory);
						break;
					case 4 : 
						afficherDroitsUtilisateur(iuserDirectory);
						break;
					case 5 :
						modifierDroitsUtilisateur(iuserDirectory);
						break;
					case 6 :
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
		System.out.println("3 - Afficher tous les utilisateurs");
		System.out.println("4 - Afficher les droits d'un utilisateur");
		System.out.println("5 - Modifier les droits d'un utilisateur");
		System.out.println("6 - Quitter l'application");
		System.out.println();
		System.out.print("Veuillez saisir le numéro de l'action à executer : ");
		menu = sc.nextInt();
		sc.nextLine();
		System.out.println();	
	}

	public static void ajouterUtilisateur(IUserDirectory iuserDirectory) {
		String nomUtilisateur;
		FinalUser user;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur à ajouer : ");
		nomUtilisateur = sc.nextLine();
		user = new FinalUser(nomUtilisateur);
		iuserDirectory.addUser(user);
		System.out.println();
	}
	
	public static void supprimerUtilisateur(IUserDirectory iuserDirectory) {
		String nomUtilisateur;
		FinalUser user;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur à supprimer : ");
		nomUtilisateur = sc.nextLine();
		user = new FinalUser(nomUtilisateur);
		iuserDirectory.removeUser(user);
		System.out.println();
	}
	
	public static void afficherUtilisateurs(IUserDirectory iuserDirectory) {
		List<FinalUser> users;
		
		users = iuserDirectory.lookUpAllUsers();
		Iterator<FinalUser> it = users.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().getUserName());
		}
		
		System.out.println();
	}
	
	public static void afficherDroitsUtilisateur(IUserDirectory iuserDirectory) {
		String nomUtilisateur;
		FinalUser user;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez afficher les droits : ");
		nomUtilisateur = sc.nextLine();
		user = new FinalUser(nomUtilisateur);
		user.setUserRights(iuserDirectory.lookUpAUserRights(user));
		System.out.println("Nom utilisateur : "+user.getUserName());
		System.out.println("Read : "+user.getUserRights().getReadAccess());
		System.out.println("Write : "+user.getUserRights().getWriteAccess());
		System.out.println();
	}
	
	public static void modifierDroitsUtilisateur(IUserDirectory iuserDirectory) {
		String nomUtilisateur;
		String droit;
		FinalUser user;
		NewsGroupRights newsGroupRights;
		boolean again = false;
		
		System.out.print("Veuillez saisir le nom de l'utilisateur dont vous souhaitez modifier les droits : ");
		nomUtilisateur = sc.nextLine();
		user = new FinalUser(nomUtilisateur);
		newsGroupRights = new NewsGroupRights();
		
		do {
			System.out.print("Veuillez saisir le nouveau droit en lecture (true/false) : ");
			droit = sc.nextLine();
			if(!droit.equals("true") && !droit.equals("false")) {
				again = true;
			} else {
				again = false;
				switch (droit) {
					case "true" :
						newsGroupRights.setReadAccess();
						break;
					case "false" :
						newsGroupRights.unsetReadAccess();
						break;
				}
			}
		} while(again);
		
		do {
			System.out.print("Veuillez saisir le nouveau droit en écriture (true/false) : ");
			droit = sc.nextLine();
			if(!droit.equals("true") && !droit.equals("false")) {
				again = true;
			} else {
				again = false;
				switch (droit) {
				case "true" :
					newsGroupRights.setWriteAccess();
					break;
				case "false" :
					newsGroupRights.unsetWriteAccess();
					break;
			}
			}
		} while(again);
		
		System.out.println(newsGroupRights.getReadAccess());
		
		iuserDirectory.updateAUserRights(user, newsGroupRights);
		
	}
	
}
