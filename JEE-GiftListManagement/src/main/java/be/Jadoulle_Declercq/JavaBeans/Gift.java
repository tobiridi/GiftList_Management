package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;

import be.Jadoulle_Declercq.DAO.AbstractDAOFactory;

public class Gift implements Serializable {
	private static final long serialVersionUID = 2765142817316875747L;
	
	private int id;
	private int priority;
	private String name;
	private double averagePrice;
	private String description;
	private String link;
	private String picture;
	private GiftList giftList;
	private GiftOffered offered;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public GiftList getGiftList() {
		return giftList;
	}
	public void setGiftList(GiftList giftList) {
		this.giftList = giftList;
	}
	
	//CONSTRUCTOR
	public Gift(int id, int priority, String name, double averagePrice, String description, String link, String picture, GiftList giftList) {
		this.id = id;
		this.priority = priority;
		this.name = name;
		this.averagePrice = averagePrice;
		this.description = description;
		this.link = link;
		this.picture = picture;
		this.giftList = giftList;
	}
	
	public Gift() {
		
	}
	
	//methods
	public boolean create() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		return adf.getGiftDao().create(this);
	}
}
