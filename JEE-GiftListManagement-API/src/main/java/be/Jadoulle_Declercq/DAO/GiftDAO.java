package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;
import java.util.ArrayList;

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
		// TODO Auto-generated method stub
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