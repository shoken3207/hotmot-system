package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDao {
	protected static final String URL  = "jdbc:mariadb://localhost:3306/lunch";
	protected static final String USER = "root";
	protected static final String PASS = "mysql";
	
	 protected Connection getConnection() throws SQLException {
	       return DriverManager.getConnection(URL, USER, PASS);
	 }
}
