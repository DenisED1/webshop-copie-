package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/educ20";
	private static final String DB_USER = "cursist";
	private static final String DB_PASS = "cursist6791";
	private static Connection c;

	protected Connection getConnection() {
		try {
			Class.forName(DB_DRIV).newInstance();
			c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
