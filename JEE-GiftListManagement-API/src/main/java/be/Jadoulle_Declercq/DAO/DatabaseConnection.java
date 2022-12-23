package be.Jadoulle_Declercq.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	private static final String ip = "193.190.64.10";
	private static final String port = "1522";
	private static final String serviceName = "XEPDB1";
	private static final String connectionString = "jdbc:oracle:thin:@//" + ip + ":" + port + "/" + serviceName;
	private static final String username = "student03_01";
	private static final String password = "564271839";
	private static Connection instance = null;
	
	private DatabaseConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			instance = DriverManager.getConnection(connectionString, username, password);
			
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Classe de driver introuvable " + ex.getMessage());
			//System.exit(0);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
			//System.exit(0);
		}
		if (instance == null) {
			JOptionPane.showMessageDialog(null, "La base de données est inaccessible, fermeture du programme.");
			//System.exit(0);
		}
	}
	
	public static Connection getInstance() {
		if(instance == null)
			new DatabaseConnection();
		return instance;
	}
	
}
