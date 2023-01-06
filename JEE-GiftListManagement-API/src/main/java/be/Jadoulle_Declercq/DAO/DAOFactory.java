package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.Gift;
import be.Jadoulle_Declercq.JavaBeans.GiftList;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class DAOFactory extends AbstractDAOFactory {
	private static final Connection connection = DatabaseConnection.getInstance();

	@Override
	public DAO<Customer> getCustomerDao() {
		return new CustomerDAO(connection);
	}

	@Override
	public DAO<GiftList> getGiftListDao() {
		return new GiftListDAO(connection);
	}

	@Override
	public DAO<Gift> getGiftDao() {
		return new GiftDAO(connection);
	}
	
	@Override
	public DAO<NotificationMessage> getNotificationMessageDao() {
		return new NotificationMessageDAO(connection);
	}
}
