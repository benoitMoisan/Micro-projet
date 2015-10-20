package mailBox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MailBoxManager implements IMailBoxManager {

	@PersistenceContext
	private EntityManager em;
	
	
	public MailBoxManager() {}
	
	
	public List<Message> readAUserNewMessages(String userName) {
		
		MailBox mail;
		List<Message> listMessages;
		
		mail = getMailBoxByUserName(userName);
		listMessages = mail.readNewMessages();
		return listMessages;
	}
	
	
	public List<Message> readAUserAllMessages(String userName) {
		
		MailBox mail;
		List<Message> listMessages;
		
		mail = getMailBoxByUserName(userName);
		listMessages = mail.readAllMessages();
		return listMessages;
	}
	
	
	public void deleteAUserMessage(String userName, Message message) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		mail.deleteAMessage(message);
	}
	
	
	public void deleteAUserReadMessages(String userName) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		mail.deleteReadMessages();
	}
	
	
	public void sendAMessageToABox(String senderName, String ReceiverName, Message message) {
		MailBox mail;
		mail = getMailBoxByUserName(ReceiverName);
		mail.addMessage(message);
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
		
	}
	
	
	public MailBox getMailBoxByUserName(String userName) {
		Query q = em.createQuery("select b from MailBox b where b.userName = :userName");
		q.setParameter("userName", userName);
		return (MailBox)q.getSingleResult();
	}
	
}
