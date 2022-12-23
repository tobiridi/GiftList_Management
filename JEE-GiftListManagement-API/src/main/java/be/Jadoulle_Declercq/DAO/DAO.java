package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connection;
	
	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	public abstract T find(int id);
	public abstract ArrayList<T> findAll();
	public abstract boolean create(T obj);
	public abstract boolean update(T obj);
	public abstract boolean delete(T obj);
}
