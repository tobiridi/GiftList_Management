package be.Jadoulle_Declercq.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
		// TODO not finished, wrong procedure
		Customer customerLog = null;
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call authenticate_customer(?,?)}");
			
			//IN parameters
			cstmt.setString(1, email);
			cstmt.setString(2, password);
			
			//OUT parameters
//			cstmt.registerOutParameter(1, Types.INTEGER);
//			cstmt.registerOutParameter(2, Types.VARCHAR);
//			cstmt.registerOutParameter(3, Types.VARCHAR);
			
			//execute
			ResultSet res = cstmt.executeQuery();
			
			//get OUT parameters result
			int id = res.getInt(1);
			String emailCustomer = res.getString(2);
			String passwordCustomer = res.getString(3);
			
			customerLog = new Customer();
			customerLog.setId(id);
			customerLog.setEmail(emailCustomer);
			customerLog.setPassword(passwordCustomer);
			
			cstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerLog;
	}

}
