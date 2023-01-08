package be.Jadoulle_Declercq.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class NotificationMessageDAO extends DAO<NotificationMessage> {

	public NotificationMessageDAO(Connection connection) {
		super(connection);
	}

	@Override
	public NotificationMessage find(int id) {
		return null;
	}

	@Override
	public ArrayList<NotificationMessage> findAll() {
		return null;
	}

	@Override
	public boolean create(NotificationMessage obj) {
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call insert_NotificationMessage(?,?,?,?,?,?)}");
			
			//IN parameters
			cstmt.setString(2, obj.getTitle());
			cstmt.setString(3, obj.getMessage());
			cstmt.setDate(4, Date.valueOf(obj.getNotificationDate()));
			cstmt.setInt(5, obj.isRead() ? 1 : 0);
			cstmt.setInt(6, obj.getRecipient().getId());
			
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
	public boolean update(NotificationMessage obj) {
		return false;
	}

	@Override
	public boolean delete(NotificationMessage obj) {
		try {
			CallableStatement cstmt = this.connection.prepareCall("{call delete_NotificationMessage(?)}");
			
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
