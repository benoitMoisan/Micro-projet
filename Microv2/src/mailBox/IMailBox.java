package mailBox;

import java.util.List;

public interface IMailBox {
	
	public void deleteAMessage(Message message);
	public void deleteReadMessages();
	public void deleteAllMessages();
	public List<Message> readNewMessages();
	
}
