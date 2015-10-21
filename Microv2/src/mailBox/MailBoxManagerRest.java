package mailBox;

/*
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/micro")
@Stateless

*/
public class MailBoxManagerRest {
/*
	@PersistenceContext
	private EntityManager em;
	
	
	public MailBoxManagerRest() {}
	
	@GET
	@Path("/readAUserNewMessages")
	@Produces(MediaType.TEXT_XML)
	public List<Message> readAUserNewMessages(@QueryParam("userName") String userName) {
		
		MailBox mail;
		List<Message> listMessages;
		
		mail = getMailBoxByUserName(userName);
		listMessages = mail.readNewMessages();
		return listMessages;
	}
	
	@GET
	@Path("/readAUserAllMessages")
	@Produces(MediaType.TEXT_XML)
	public List<Message> readAUserAllMessages(@QueryParam("userName") String userName) {
		
		MailBox mail;
		List<Message> listMessages;
		
		mail = getMailBoxByUserName(userName);
		
		return listMessages;
	}
	
	@DELETE
	@Path("/deleteAUserMessage")
	public void deleteAUserMessage(@QueryParam("userName") String userName, Message message) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		mail.deleteAMessage(message);
	}
	
	@DELETE
	@Path("/deleteAUserReadMessages")
	public void deleteAUserReadMessages(String userName) {
		MailBox mail;
		mail = getMailBoxByUserName(userName);
		mail.deleteReadMessages();
	}
	
	@POST
	@Path("/sendAMessageToABox")
    @Consumes({MediaType.TEXT_PLAIN})
	public void sendAMessageToABox(String senderName, String ReceiverName, Message message) {
		MailBox mail;
		mail = getMailBoxByUserName(ReceiverName);
		mail.addMessage(message);
	}
	
	@POST
	@Path("/addUser")
    @Consumes({MediaType.TEXT_PLAIN})
	public void addUser(String userName) {
		MailBox mail = new MailBox(userName);
		em.persist(mail);
	}
	
	@DELETE
	@Path("/removeUser")
	public void removeUser(@QueryParam("userName") String userName) {
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
	*/
}
