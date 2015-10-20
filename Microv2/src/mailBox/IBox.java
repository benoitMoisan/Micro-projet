package mailBox;

import java.util.List;

public interface IBox {

	public List<Message> readAllMessages();
	public Message readAMessage();
	public void addMessage(Message message);
	
}
