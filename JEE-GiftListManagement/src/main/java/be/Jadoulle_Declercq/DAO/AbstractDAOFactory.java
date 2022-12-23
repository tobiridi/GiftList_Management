package be.Jadoulle_Declercq.DAO;

public class AbstractDAOFactory {
	public static final int DAO_FACTORY = 1;

	public static AbstractDAOFactory getFactory(int type) {
		switch (type) {
		case DAO_FACTORY : return new DAOFactory();
		default: return null;
		}
	}
	
	//abstract methods
}
