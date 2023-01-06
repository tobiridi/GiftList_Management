package be.Jadoulle_Declercq.DAO;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Customer find(int id) {
		Customer customer = null;
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call get_Customer(?,?,?)}");
			
			//IN parameters
			cstmt.setInt(3, id);
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.STRUCT, "CUSTOMER_OBJECT");
			cstmt.registerOutParameter(2, Types.ARRAY, "T_GIFTLIST_OBJECT");
			
			//execute
			cstmt.executeQuery();
			
			//get OUT parameters result
			Struct customerObject = (Struct) cstmt.getObject(1);
			Array tabGiftListObject = cstmt.getArray(2);
			
			//get customer
			if(customerObject != null) {
				Object[] dataCustomer = customerObject.getAttributes();
				
				String firstname = (String) dataCustomer[1];
				String lastname = (String) dataCustomer[2];
				String emailCustomer = (String) dataCustomer[3];
				
				customer = new Customer(id, emailCustomer, null, firstname, lastname);
				
				//get customer giftLists
				if(tabGiftListObject != null) {
					Object[] dataTabGiftListObject = (Object[]) tabGiftListObject.getArray();
					for(Object giftListObject : dataTabGiftListObject) {
						Object[] giftListData = ((Struct) giftListObject).getAttributes();
						
						BigDecimal idGiftList = (BigDecimal) giftListData[0];
						String type = (String) giftListData[1];
						Timestamp deadLine = (Timestamp) giftListData[2];
						boolean isActive = ((BigDecimal) giftListData[3]).intValue() == 1 ? true : false;
						int idCustomerGiftList = ((BigDecimal) giftListData[4]).intValue();
						
						//add own list to customerLog
						if(id == idCustomerGiftList) {
							Customer owner = new Customer();
							owner.setId(id);
							GiftList giftList = new GiftList(idGiftList.intValue(), type, isActive, null, owner);
							if (deadLine != null) {
								giftList.setDeadLine(deadLine.toLocalDateTime().toLocalDate());
							}
							customer.addGiftList(giftList);
						}
						//add friends list to customerLog
						else {
							Customer friend = new Customer();
							friend.setId(idCustomerGiftList);
							GiftList giftList = new GiftList(idGiftList.intValue(), type, isActive, null, friend);
							if (deadLine != null) {
								giftList.setDeadLine(deadLine.toLocalDateTime().toLocalDate());
							}
							customer.addOtherCustomerGiftList(giftList);
						}
					}
				}
			}
			
			//get customer Notification Message
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
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
			Struct customerObject = (Struct) cstmt.getObject(1);
			
			//get customer
			if(customerObject != null) {
				Object[] dataCustomer = customerObject.getAttributes();
				
				int id = ((BigDecimal) dataCustomer[0]).intValue();
				String firstname = (String) dataCustomer[1];
				String lastname = (String) dataCustomer[2];
				String emailCustomer = (String) dataCustomer[3];
				
				customerLog = new Customer(id, emailCustomer, null, firstname, lastname);
			}
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customerLog;
	}

}
