package be.Jadoulle_Declercq.DAO;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.Gift;
import be.Jadoulle_Declercq.JavaBeans.GiftList;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class DAOFactory extends AbstractDAOFactory {

	@Override
	public DAO<Customer> getCustomerDao() {
		return new CustomerDAO();
	}

	@Override
	public DAO<GiftList> getGiftListDao() {
		return new GiftListDAO();
	}

	@Override
	public DAO<Gift> getGiftDao() {
		return new GiftDAO();
	}

	@Override
	public DAO<NotificationMessage> getNotificationMessageDao() {
		return new NotificationMessageDAO();
	}
	
}
