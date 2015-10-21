package mailBox;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import directory.FinalUser;
import directory.IUserDirectory;
import directory.NewsGroupRights;

import java.util.Collection;

@Stateless
public class MailBoxManager implements IMailBoxManager {

	@PersistenceContext
	private EntityManager em;
	
	
	public MailBoxManager() {}
	
	
	public List<Message> readAUserNewMessages(String userName) {
		
		List<Message> newMessages = new ArrayList();
/*
		Query q = em.createQuery("select m from Message m where m.receiverName = :user and m.alreadyRead = 0");
		q.setParameter("user", userName);

		newMessages = (List<Message>) q.getResultList();

		for (int i=0;i<newMessages.size();i++)
		{					
			newMessages.get(i).setIsRead();
			em.persist(newMessages.get(i));
		}
		*/
		
		MailBox mail;
		List<Message> listMessages = new ArrayList<Message>();
		
		mail = getMailBoxByUserName(userName);
		Iterator<Message> it = mail.getListMessages().iterator();
		while(it.hasNext()) {
			Message message = it.next();
			if(message.isRead() == false) {
				message.setIsRead(true);
				em.persist(message);
				listMessages.add(message);
			}
		} 
		
		
		return listMessages;
	}
	
	
	public Collection<Message> readAUserAllMessages(String userName) {
		
		MailBox mail;
		Collection<Message> listMessages = new ArrayList<Message>();
		
		mail = getMailBoxByUserName(userName);
		listMessages = getMailBoxByUserName(userName).getListMessages();
		Iterator<Message> it = listMessages.iterator();
		while(it.hasNext()) {
			it.next().setIsRead(true);
		}
		return listMessages;
	}
	
	
	public void deleteAUserMessage(String userName, int id) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		Iterator<Message> it = mail.getListMessages().iterator();
		while(it.hasNext()) {
			Message message = it.next();
			if(message.getId() == id) {
				em.remove(message);
			}
		}
	}
	
	
	public void deleteAUserReadMessages(String userName) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		Iterator<Message> it = mail.getListMessages().iterator();
		while(it.hasNext()) {
			Message message = it.next();
			if(message.isRead() == true) {
				em.remove(message);
			}
			
		}
	}
	
	
	public void sendAMessageToABox(Message message) {
		MailBox mail;
		mail = getMailBoxByUserName(message.getReceiverName());
		mail.getListMessages().add(message);
		message.setMailBox(mail);
	}
	
	
	public void addUser(String userName) {
		MailBox mail = new MailBox(userName);
		em.persist(mail);
	}
	
	public void removeUser(String userName) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		em.remove(mail);
	}
	
	
	public void sendNews(Message message) {
		try {
			InitialContext ic = new InitialContext();
			IUserDirectory iuserDirectory = (IUserDirectory) ic.lookup("directory.IUserDirectory");
			
			NewsGroupRights newsGroupRights = iuserDirectory.lookUpAUserRights(new FinalUser(message.getSenderName()));
			if(newsGroupRights.getWriteAccess()) {
				List<FinalUser> list = iuserDirectory.lookUpAllUsers();
				Iterator<FinalUser> it = list.iterator();
				while(it.hasNext()) {
					FinalUser user = it.next();
					if(user.getUserRights().getReadAccess()) {
						Message messageTemp = new Message(message.getSenderName(), null, message.getSubject(), message.getBody());
						messageTemp.setDate(message.getDate());
						messageTemp.setReceiverName(user.getUserName());
						sendAMessageToABox(messageTemp);
					}
				}
			}
			
		} catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public MailBox getMailBoxByUserName(String userName) {
		Query q = em.createQuery("select b from MailBox b where b.userName = :userName");
		q.setParameter("userName", userName);
		return (MailBox)q.getSingleResult();
	}
	
}
