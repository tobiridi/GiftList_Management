package be.Jadoulle_Declercq.DAO;

import be.Jadoulle_Declercq.JavaBeans.Customer;

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
}
