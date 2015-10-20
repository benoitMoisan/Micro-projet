package mailBox;

import java.util.List;

public interface IMailBoxManager {

	public List<Message> readAUserNewMessages(String UserName);
	public List<Message> readAUserAllMessages(String UserName);
	public void deleteAUserMessage(String userName, Message message);
	public void deleteAUserReadMessages(String userName);
	public void sendAMessageToABox(String senderName, String ReceiverName, Message message);
	public void addUser(String userName);
	public void removeUser(String userName);
	public void sendNews(Message message);
	
	
}
