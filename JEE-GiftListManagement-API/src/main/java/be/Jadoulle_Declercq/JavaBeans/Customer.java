package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 6189751661049434353L;
	
	private int id;
	private String email;
	private String password;
	//TODO : "GiftList, GiftList, GiftsOffered, NotificationMessage" references
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	//CONSTRUCTOR
	public Customer(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Customer() {
		
	}

}
