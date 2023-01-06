package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;
import java.time.LocalDate;

public class NotificationMessage implements Serializable {
	private static final long serialVersionUID = 8947431798761959935L;
	
	private int id;
	private String title;
	private String message;
	private LocalDate notificationDate;
	private boolean isRead;
	private Customer recipient;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public LocalDate getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(LocalDate notificationDate) {
		this.notificationDate = notificationDate;
	}
	
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	public Customer getRecipient() {
		return recipient;
	}
	public void setRecipient(Customer recipient) {
		this.recipient = recipient;
	}
	
	//CONSTRUCTOR
	public NotificationMessage(int id, String title, String message, LocalDate notificationDate, boolean isRead, Customer recipient) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.notificationDate = notificationDate;
		this.isRead = isRead;
		this.recipient = recipient;
	}
	
	public NotificationMessage() {
		
	}
	
	//methods
	
}
