package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;

public class DAOFactory extends AbstractDAOFactory {
	private static final Connection connection = DatabaseConnection.getInstance();

}
