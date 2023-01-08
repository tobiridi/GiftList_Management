package be.Jadoulle_Declercq.JavaBeans;

import java.io.Serializable;

public class GiftOffered implements Serializable {
	private static final long serialVersionUID = 8838310808929775840L;
	
	private Customer customer;
	private Gift gift;
	private double amount;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	//CONSTRUCTOR
	public GiftOffered(Customer customer, Gift gift, double amount) {
		this.customer = customer;
		this.gift = gift;
		this.amount = amount;
	}
	
	public GiftOffered() {
		
	}

	//methods
	
}
