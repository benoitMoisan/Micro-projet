package mailBox;

import java.util.Date;

public class Message {
	
	private String senderName;
	private String receiverName;
	private Date sendingate;
	private String subject;
	private String body;
	private boolean alreadyRead;
	
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
