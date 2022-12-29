package be.Jadoulle_Declercq.DAO;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class DAOFactory extends AbstractDAOFactory {

	@Override
	public DAO<Customer> getCustomerDao() {
		return new CustomerDAO();
	}
	
}
