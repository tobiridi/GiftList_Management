package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;

import be.Jadoulle_Declercq.DAO.AbstractDAOFactory;
import be.Jadoulle_Declercq.DAO.CustomerDAO;
import be.Jadoulle_Declercq.DAO.DAO;

public class Customer implements Serializable {
	private static final long serialVersionUID = 6189751661049434353L;
	
	private int id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
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

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	//CONSTRUCTOR
	public Customer(int id, String email, String password, String firstname, String lastname) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Customer() {
		
	}

	//methods
	public static Customer login(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Customer> customerDao = adf.getCustomerDao();
		return ((CustomerDAO) customerDao).authenticate(email, password);
	}
	
	public boolean create() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getCustomerDao().create(this);
	}
}
