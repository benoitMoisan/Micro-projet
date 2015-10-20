package mailBox;

import java.util.Date;

public class Message {
	
	private String senderName;
	private String receiverName;
	private Date sendingDate;
	private String subject;
	private String body;
	private boolean alreadyRead;
	
	public Message () {}
	
	public Message (String senderName, String receiverName, String subject, String body) {
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.subject = subject;
		this.body = body;
		this.alreadyRead = false;
	}
	
	
	public void setIsRead() {
		this.alreadyRead = true;
	}
	
	public boolean isRead() {
		return this.alreadyRead;
	}
	
	public String getSenderName () {
		return this.senderName;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	
}
