package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class DAOFactory extends AbstractDAOFactory {
	private static final Connection connection = DatabaseConnection.getInstance();

	@Override
	public DAO<Customer> getCustomerDao() {
		return new CustomerDAO(connection);
	}

}
