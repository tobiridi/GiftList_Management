package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import be.Jadoulle_Declercq.DAO.AbstractDAOFactory;

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
	public boolean create() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getNotificationMessageDao().create(this);
	}
	
	public boolean delete() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getNotificationMessageDao().delete(this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, isRead, message, notificationDate, recipient, title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		NotificationMessage other = (NotificationMessage) obj;
		return this.id == other.id;
	}
}
