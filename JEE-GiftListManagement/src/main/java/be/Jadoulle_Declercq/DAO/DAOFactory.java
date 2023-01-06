package be.Jadoulle_Declercq.DAO;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

public class DAOFactory extends AbstractDAOFactory {

	@Override
	public DAO<Customer> getCustomerDao() {
		return new CustomerDAO();
	}

	@Override
	public DAO<GiftList> getGiftListDao() {
		return new GiftListDAO();
	}
	
}
