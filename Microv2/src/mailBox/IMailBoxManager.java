package mailBox;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IMailBoxManager {

	public List<Message> readAUserNewMessages(String UserName);
	public Collection<Message> readAUserAllMessages(String UserName);
	public void deleteAUserMessage(String userName, int id);
	public void deleteAUserReadMessages(String userName);
	public void sendAMessageToABox(Message message);
	public void addUser(String userName);
	public void removeUser(String userName);
	public void sendNews(Message message);
	
	
}
