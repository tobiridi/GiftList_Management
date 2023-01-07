package be.Jadoulle_Declercq.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import be.Jadoulle_Declercq.JavaBeans.Customer;
import be.Jadoulle_Declercq.JavaBeans.GiftList;
import be.Jadoulle_Declercq.JavaBeans.Gift;

public class GiftListDAO extends DAO<GiftList> {

	public GiftListDAO(Connection connection) {
		super(connection);
	}
	
	@Override
	public GiftList find(int id) {
		GiftList giftList = null;
		try {
			CallableStatement cstmt = this.connection.prepareCall("{call get_GiftList(?,?,?)}");
			
			//IN parameters
			cstmt.setInt(3, id);
			
			//OUT parameters
			cstmt.registerOutParameter(1, Types.STRUCT, "GIFTLIST_OBJECT");
			cstmt.registerOutParameter(2, Types.ARRAY, "T_GIFT_OBJECT");
			
			//execute
			cstmt.executeQuery();
			
			//get OUT parameters result
			Struct giftListObject = (Struct) cstmt.getObject(1);
			Array tabGiftObject = cstmt.getArray(2);
			
			//get giftList
			if(giftListObject != null) {
				Object[] dataGiftList = giftListObject.getAttributes();
				
				String type = (String) dataGiftList[1];
				LocalDate deadLine = dataGiftList[2] != null ? ((Timestamp) dataGiftList[2]).toLocalDateTime().toLocalDate() : null;
				boolean isActive = ((BigDecimal) dataGiftList[3]).intValue() == 1 ? true : false;
				int idCustomerGiftList = ((BigDecimal) dataGiftList[4]).intValue();
				
				Customer ownerList = new Customer();
				ownerList.setId(idCustomerGiftList);
				
				giftList = new GiftList(id, type, isActive, deadLine, ownerList);
				
				//get giftList gifts
				if(tabGiftObject != null) {
					Object[] dataTabGiftObject = (Object[]) tabGiftObject.getArray();
					for(Object giftObject : dataTabGiftObject) {
						Object[] giftData = ((Struct) giftObject).getAttributes();
						
						int idGift = ((BigDecimal) giftData[0]).intValue();
						String name = (String) giftData[1];
						double averagePrice = ((BigDecimal) giftData[2]).doubleValue();
						int priority = ((BigDecimal) giftData[3]).intValue();
						String description = (String) giftData[4];
						String link = (String) giftData[5];
						String picture = null;
						//cast in Base64 if not null
						if(giftData[6] != null) {
							try {
								InputStream streamPicture = ((Blob) giftData[6]).getBinaryStream();
								picture = Base64.getEncoder().encodeToString(streamPicture.readAllBytes());
								streamPicture.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
						GiftList listOwner = new GiftList();
						listOwner.setId(id);
						
						Gift gift = new Gift(idGift, priority, name, averagePrice, description, link, picture, listOwner);
						giftList.addGift(gift);
					}
				}
			}
			
			cstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return giftList;
	}

	@Override
	public ArrayList<GiftList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(GiftList obj) {
		try {
			CallableStatement cstmt = this.connection.prepareCall("{call insert_GiftList(?,?,?,?,?)}");
			
			//IN parameters
			cstmt.setString(2, obj.getType());
			if(obj.getDeadLine() == null)
				cstmt.setDate(3, null);
			else
				cstmt.setDate(3, Date.valueOf(obj.getDeadLine()));
			
			cstmt.setInt(4, obj.isActive() ? 1 : 0);
			cstmt.setInt(5, obj.getOwnerList().getId());
			
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
	public boolean update(GiftList obj) {
		try {
			CallableStatement cstmt = this.connection.prepareCall("{call update_GiftList(?,?,?,?)}");
			
			//IN parameters
			cstmt.setInt(1, obj.getId());
			cstmt.setString(2, obj.getType());
			
			if(obj.getDeadLine() == null)
				cstmt.setDate(3, null);
			else
				cstmt.setDate(3, Date.valueOf(obj.getDeadLine()));
			
			cstmt.setInt(4, obj.isActive() ? 1 : 0);
			
			//execute
			int res = cstmt.executeUpdate();
			cstmt.close();
			
			//update only one shared GiftList
			if(!obj.getCustomerShared().isEmpty()) {
				CallableStatement cstmt2 = this.connection.prepareCall("{call insert_Friend_GiftList(?,?)}");
				
				//IN parameters
				cstmt2.setInt(1, obj.getCustomerShared().get(0).getId());
				cstmt2.setInt(2, obj.getId());
				
				//execute
				res = cstmt2.executeUpdate();
				cstmt2.close();
			}
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
	}

	@Override
	public boolean delete(GiftList obj) {
		try {
			CallableStatement cstmt = this.connection.prepareCall("{call delete_GiftList(?)}");
			
			//IN parameters
			cstmt.setInt(1, obj.getId());
			
			//execute
			int res = cstmt.executeUpdate();
			cstmt.close();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
