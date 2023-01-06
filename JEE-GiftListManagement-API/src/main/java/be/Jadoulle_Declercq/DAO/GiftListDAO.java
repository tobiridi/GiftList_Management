package be.Jadoulle_Declercq.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.GiftList;

public class GiftListDAO extends DAO<GiftList> {

	public GiftListDAO(Connection connection) {
		super(connection);
	}
	
	@Override
	public GiftList find(int id) {
		// TODO Auto-generated method stub
		return null;
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
