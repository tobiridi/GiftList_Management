package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.Jadoulle_Declercq.DAO.AbstractDAOFactory;

public class GiftList implements Serializable {
	private static final long serialVersionUID = -1504368255368514487L;
	
	private int id;
	private String type;
	private boolean isActive;
	private LocalDate deadLine;
	private Customer ownerList;
	private ArrayList<Customer> customerShared;
	private ArrayList<Gift> gifts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public LocalDate getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}
	
	public Customer getOwnerList() {
		return ownerList;
	}
	public void setOwnerList(Customer ownerList) {
		this.ownerList = ownerList;
	}
	
	public ArrayList<Customer> getCustomerShared() {
		return customerShared;
	}
	public void setCustomerShared(ArrayList<Customer> customerShared) {
		this.customerShared = customerShared;
	}
	
	public ArrayList<Gift> getGifts() {
		return gifts;
	}
	public void setGifts(ArrayList<Gift> gifts) {
		this.gifts = gifts;
	}

	//CONSTRUCTOR
	public GiftList() {
		this.customerShared = new ArrayList<>();
		this.gifts = new ArrayList<>();
	}
	
	public GiftList(int id, String type, boolean isActive, LocalDate deadLine, Customer ownerList) {
		this();
		this.id = id;
		this.type = type;
		this.isActive = isActive;
		this.deadLine = deadLine;
		this.ownerList = ownerList;
	}
	
	//methods
	public boolean addCustomerShared(Customer customer) {
		return this.customerShared.add(customer);
	}
	
	public boolean create() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getGiftListDao().create(this);
	}
	
	public boolean delete() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getGiftListDao().delete(this);
	}
	
	public boolean update() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getGiftListDao().update(this);
	}
	
	public static GiftList get(int id) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getGiftListDao().find(id);
	}
	
	public boolean isExpired() {
		return this.deadLine != null ? this.deadLine.isBefore(LocalDate.now()) : !this.isActive;
	}
}
