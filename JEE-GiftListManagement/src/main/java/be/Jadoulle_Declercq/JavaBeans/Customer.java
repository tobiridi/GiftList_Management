package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;

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
	private ArrayList<GiftList> giftList;
	private ArrayList<GiftList> otherCustomerList;
	private ArrayList<NotificationMessage> messageBox;
	private ArrayList<GiftOffered> giftOffereds;
	
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
	
	public ArrayList<GiftList> getGiftList() {
		return giftList;
	}
	public void setGiftList(ArrayList<GiftList> giftList) {
		this.giftList = giftList;
	}
	
	public ArrayList<GiftList> getOtherCustomerList() {
		return otherCustomerList;
	}
	public void setOtherCustomerList(ArrayList<GiftList> otherCustomerList) {
		this.otherCustomerList = otherCustomerList;
	}
	
	public ArrayList<NotificationMessage> getMessageBox() {
		return messageBox;
	}
	public void setMessageBox(ArrayList<NotificationMessage> messageBox) {
		this.messageBox = messageBox;
	}
	
	public ArrayList<GiftOffered> getGiftOffereds() {
		return giftOffereds;
	}
	public void setGiftOffereds(ArrayList<GiftOffered> giftOffereds) {
		this.giftOffereds = giftOffereds;
	}

	//CONSTRUCTOR
	public Customer(int id, String email, String password, String firstname, String lastname) {
		this();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Customer() {
		this.giftList = new ArrayList<>();
		this.otherCustomerList = new ArrayList<>();
		this.messageBox = new ArrayList<>();
		this.giftOffereds = new ArrayList<>();
	}
	
	//methods
	public static Customer login(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Customer> customerDao = adf.getCustomerDao();
		return ((CustomerDAO) customerDao).authenticate(email, password);
	}
	
	public static Customer get(int id) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getCustomerDao().find(id);
	}
	
	public static ArrayList<Customer> getAll() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getCustomerDao().findAll();
	}
	
	public boolean create() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getCustomerDao().create(this);
	}
	
	public boolean addGiftList(GiftList giftlist) {
		return this.giftList.add(giftlist);
	}
	
	public boolean addOtherCustomerGiftList(GiftList giftlist) {
		return this.otherCustomerList.add(giftlist);
	}
	
	public boolean addNotificationMessage(NotificationMessage notif) {
		return this.messageBox.add(notif);
	}
	
	public boolean addGiftOffered(GiftOffered giftOffered) {
		return this.giftOffereds.add(giftOffered);
	}
	
	public boolean hasUnreadMessage() {
		return this.messageBox.stream().filter(m -> !m.isRead()).count() > 0;
	}
}
