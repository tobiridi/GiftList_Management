package be.Jadoulle_Declercq.DAO;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;
import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class CustomerDAO extends DAO<Customer> {

	public CustomerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Customer find(int id) {
		Customer customer = null;
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call get_Customer(?,?,?,?)}");
			
			//IN parameters
			cstmt.setInt(4, id);
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.STRUCT, "CUSTOMER_OBJECT");
			cstmt.registerOutParameter(2, Types.ARRAY, "T_GIFTLIST_OBJECT");
			cstmt.registerOutParameter(3, Types.ARRAY, "T_NOTIFICATIONMESSAGE_OBJECT");
			
			//execute
			cstmt.executeQuery();
			
			//get OUT parameters result
			Struct customerObject = (Struct) cstmt.getObject(1);
			Array tabGiftListObject = cstmt.getArray(2);
			Array tabMessageObject = cstmt.getArray(3);
			
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
				//get customer Notification Message
				if(tabMessageObject != null) {
					Object[] dataTabMessageObject = (Object[]) tabMessageObject.getArray();
					for(Object messageObject : dataTabMessageObject) {
						Object[] messageData = ((Struct) messageObject).getAttributes();
						
						int idMessage = ((BigDecimal) messageData[0]).intValue();
						String title = (String) messageData[1];
						String message = (String) messageData[2];
						LocalDate notifDate = ((Timestamp) messageData[3]).toLocalDateTime().toLocalDate();
						boolean isRead = ((BigDecimal) messageData[4]).intValue() == 1 ? true : false;
						
						Customer recipient = new Customer();
						recipient.setId(id);
						NotificationMessage notif = new NotificationMessage(idMessage, title, message, notifDate, isRead, recipient);
						customer.addNotificationMessage(notif);
					}
				}
			}
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public ArrayList<Customer> findAll() {
		ArrayList<Customer> allCustomers = null;
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call get_all_Customer(?)}");
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.ARRAY, "T_CUSTOMER_OBJECT");
			
			//execute
			cstmt.executeQuery();
			
			//get OUT parameters result
			Array tabCustomerObject = cstmt.getArray(1);
			
			if(tabCustomerObject != null ) {
				allCustomers = new ArrayList<>();
				Object[] dataTabCustomerObject = (Object[]) tabCustomerObject.getArray();
				for(Object customerObject : dataTabCustomerObject) {
					Object[] customerData = ((Struct) customerObject).getAttributes();
					
					int id = ((BigDecimal) customerData[0]).intValue();
					String firstname = (String) customerData[1];
					String lastname = (String) customerData[2];
					String email = (String) customerData[3];
					
					Customer customer = new Customer(id, email, null, firstname, lastname);
					allCustomers.add(customer);
				}
			}
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allCustomers;
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
		return false;
	}

	@Override
	public boolean delete(Customer obj) {
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
