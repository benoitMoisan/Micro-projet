package mailBox;

import java.util.Collection;
import java.util.List;

public interface IBox {

	public Collection<Message> readAllMessages();
	public Message readAMessage();
	public void addMessage(Message message);
	
}
