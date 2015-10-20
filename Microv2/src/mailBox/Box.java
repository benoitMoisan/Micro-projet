package mailBox;

import java.util.ArrayList;
import java.util.List;

public class Box implements IBox{
	
	protected String BoxName;
	protected List<Message> list;
	
	public Box () {
		this.list = new ArrayList<Message>();
	}
	
	public Box (String BoxName) {
		this.BoxName = BoxName;
		this.list = new ArrayList<Message>();
	}
	
	public List<Message> readAllMessages() {
		return this.list;
	}
	
	public Message readAMessage() {
		return this.list.get(0);
	}
	
	public void addMessage(Message message) {
		this.list.add(message);
	}
	
}
