package mailBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.*;

@Entity
public class MailBox implements IMailBox, IBox, Serializable  {
	
	private String userName;
	private Collection<Message> list = new ArrayList<Message>();
	
	
	public MailBox () {
		this.list = new ArrayList<Message>();
	}
	
	public MailBox (String userName) {
		this.list = new ArrayList<Message>();
		this.userName = userName;
	}
	
	@Id
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@OneToMany(cascade=ALL, fetch=FetchType.EAGER, mappedBy="mailBox")
	public Collection<Message> getListMessages() {
		return this.list;
	}
	
	public void setListMessages(Collection<Message> list) {
		this.list = list;
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
		
		List<Message> list = new ArrayList<Message>();
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
			list.add(message);
		}
		this.setListMessages(list);
		return listNewMessages;
	}
	
	public Collection<Message> readAllMessages() {
		return this.list;
	}
	
	public Message readAMessage() {
		Message message = this.list.iterator().next();
		return message;
	}
	
	public void addMessage(Message message) {
		this.list.add(message);
	}
	
}
