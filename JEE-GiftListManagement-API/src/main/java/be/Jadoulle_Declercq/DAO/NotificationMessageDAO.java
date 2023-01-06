package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.Jadoulle_Declercq.JavaBeans.NotificationMessage;

public class NotificationMessageDAO extends DAO<NotificationMessage> {

	public NotificationMessageDAO(Connection connection) {
		super(connection);
	}

	@Override
	public NotificationMessage find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NotificationMessage> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(NotificationMessage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(NotificationMessage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(NotificationMessage obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
