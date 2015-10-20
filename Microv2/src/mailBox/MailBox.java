package mailBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class MailBox extends Box implements IMailBox, Serializable  {
	
	@Id
	private String userName;
	
	public MailBox () {}
	
	public MailBox (String userName) {
		super(userName);
		this.userName = userName;
	}
	
	
	public void deleteAMessage(Message message) {
		if(this.list.contains(message)) {
			this.list.remove(message);
		}
	}

	
	public void deleteReadMessages() {
		Iterator<Message> it = this.list.iterator();
		while(it.hasNext()) {
			Message message = (Message)it.next();
			if(message.isRead() == true) {
				this.list.remove(message);
			}
		}
	}
	
	
	public void deleteAllMessages() {
		this.list.removeAll(list);
	}
	
	
	public List<Message> readNewMessages() {
		
		List<Message> listNewMessages = new ArrayList<Message>();
		Iterator<Message> it = this.list.iterator();
		
		while(it.hasNext()) {
			Message message = (Message)it.next();
			if(message.isRead() == false) {
				listNewMessages.add(message);
				this.list.remove(message);
				message.setIsRead();
				this.list.add(message);
			}
		}
		return listNewMessages;
	}
	
}
