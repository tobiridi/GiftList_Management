package be.Jadoulle_Declercq.DAO;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
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
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call insert_Customer(?,?,?,?,?)}");
			
			//IN parameters
			cstmt.setString(2, obj.getFirstname());
			cstmt.setString(3, obj.getLastname());
			cstmt.setString(4, obj.getEmail());
			cstmt.setString(5, obj.getPassword());
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.INTEGER);
			
			//execute
			cstmt.executeUpdate();
			int res = cstmt.getInt(1);
			cstmt.close();
			
			if(res > 0) {
				obj.setId(res);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
		Customer customerLog = null;
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call authenticate_Customer(?,?,?)}");
			
			//IN parameters
			cstmt.setString(2, email);
			cstmt.setString(3, password);
			
			//define a specify structure
//			Struct customer_object = this.connection.createStruct("CUSTOMER_OBJECT",
//					new Object[] {"id_Customer_object", "email_object", "password_object"});
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.STRUCT, "CUSTOMER_OBJECT");
			
			//execute
			cstmt.executeQuery();
			
			//get OUT parameters result
			Struct customer_object = (Struct) cstmt.getObject(1);
			
			if(customer_object != null) {
				Object[] data = customer_object.getAttributes();
				
				BigDecimal id = (BigDecimal) data[0];
				String firstname = (String) data[1];
				String lastname = (String) data[2];
				String emailCustomer = (String) data[3];
				
				customerLog = new Customer();
				customerLog.setId(id.intValue());
				customerLog.setFirstname(firstname);
				customerLog.setLastname(lastname);
				customerLog.setEmail(emailCustomer);
				customerLog.setPassword(null);
			}
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customerLog;
	}

}
