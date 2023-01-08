package be.Jadoulle_Declercq.DAO;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.Gift;
import be.Jadoulle_Declercq.JavaBeans.GiftList;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 1;

	public static AbstractDAOFactory getFactory(int type) {
		switch (type) {
		case DAO_FACTORY : return new DAOFactory();
		default: return null;
		}
	}
	
	//abstract methods
	public abstract DAO<Customer> getCustomerDao();
	public abstract DAO<GiftList> getGiftListDao();
	public abstract DAO<Gift> getGiftDao();
	public abstract DAO<NotificationMessage> getNotificationMessageDao();
}
