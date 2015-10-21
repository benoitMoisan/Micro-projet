package mailBox;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

@Entity
@Table(name="MESSAGE_TABLE")
public class Message implements Serializable{
	
	
	private int id;
	private String senderName;
	private String receiverName;
	private Date sendingDate;
	private String subject;
	private String body;
	private boolean alreadyRead;
	private MailBox mailBox;
	
	public Message () {}
	
	public Message (String senderName, String receiverName, String subject, String body) {
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.subject = subject;
		this.body = body;
		this.alreadyRead = false;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MESSAGE_ID")
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne()
	@JoinColumn(name="MAILBOX_USERNAME")
    public MailBox getMailBox() {
        return this.mailBox;
    }
	
	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}
	
	@Temporal(TemporalType.TIME)
	@Column(name="MESSAGE_DATE")
	public Date getDate() {
		return this.sendingDate;
	}
	
	public void setDate(Date date) {
		this.sendingDate = date;
	}
	
	@Column(name="MESSAGE_READ")
	public boolean isRead() {
		return this.alreadyRead;
	}
	
	public void setIsRead(boolean read) {
		this.alreadyRead = read;
	}
	
	public void setIsRead() {
		this.alreadyRead = true;
	}
	
	@Column(name="MESSAGE_SENDERNAME")
	public String getSenderName () {
		return this.senderName;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	@Column(name="MESSAGE_RECEIVERNAME")
	public String getReceiverName () {
		return this.receiverName;
	}
	
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Column(name="MESSAGE_SUBJECT")
	public String getSubject() {
		return this.subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name="MESSAGE_BODY")
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
