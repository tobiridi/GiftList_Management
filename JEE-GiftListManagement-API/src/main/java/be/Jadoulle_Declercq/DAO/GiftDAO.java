package be.Jadoulle_Declercq.DAO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;

import be.Jadoulle_Declercq.JavaBeans.Gift;

public class GiftDAO extends DAO<Gift> {

	public GiftDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Gift find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Gift> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Gift obj) {
		try {
			//call procedure
			CallableStatement cstmt = this.connection.prepareCall("{call insert_Gift(?,?,?,?,?,?,?,?)}");
			
			//IN parameters
			cstmt.setString(2, obj.getName());
			cstmt.setInt(3, obj.getPriority());
			cstmt.setString(4, obj.getDescription());
			cstmt.setDouble(5, obj.getAveragePrice());
			cstmt.setString(6, obj.getLink());
			
			if(obj.getPicture() != null) {
				try {
					//convert Base64 image to stream
					byte[] bytes = Base64.getDecoder().decode(obj.getPicture().getBytes());
					InputStream imageStream = new ByteArrayInputStream(bytes);
					//add blob as a procedure parameters
					cstmt.setBlob(7, imageStream);
					imageStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				cstmt.setBinaryStream(7, null);
			}
			
			cstmt.setInt(8, obj.getGiftList().getId());
			
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
	public boolean update(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Gift obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
