package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Customer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Customer obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Customer authenticate(String email, String password) {
		// TODO not implemented, call DB
		return null;
	}

}
