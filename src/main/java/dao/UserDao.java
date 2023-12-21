package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.UserBean;

public class UserDao extends CommonDao {
	
    public ArrayList<UserBean> findAll() {
        ArrayList<UserBean> users = new ArrayList<UserBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserBean userbean = new UserBean(0, sql, sql, null);
				userbean.setId(rs.getInt("id"));
				userbean.setEmail(rs.getString("email"));
				userbean.setName(rs.getString("name"));
				userbean.setIsAdmin(rs.getBoolean("isAdmin"));
				users.add(userbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
    }
    public int insert (String email,String name,Boolean isAdmin) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO users(email,name) " +
                "VALUES(" +email + "," + name +")";
    	
    	PreparedStatement statement = conn.prepareStatement(sql);
    	ResultSet rs = statement.executeQuery();
    	rs.next();
    	
    	rs.close();
    	statement.close();
    	
    	statement = conn.prepareStatement(sql);
    	
    	int updateCount = statement.executeUpdate();
    	
    	statement.close();
    	
    	conn.commit();
    	conn.close();
    	System.out.print(email);
    	return updateCount;
    	}
    	
    }
    
    public int update(String email,String name,boolean isAdmin) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET users(id,email,name,isAdmin) " +
                    "VALUES("  + email + "," + name + "," + isAdmin + ")";
        	PreparedStatement statement = conn.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery();
        	rs.next();
        	
        	rs.close();
        	statement.close();
        	
        	statement = conn.prepareStatement(sql);
        	
        	int updateCount = statement.executeUpdate();
        	
        	statement.close();
        	
        	conn.commit();
        	conn.close();
        	
        	return updateCount;
    	}
    }
    
    public int delete(int id,String email,String name,boolean isAdmin) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM users WHERE id = " + id ;
            PreparedStatement statement = conn.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery();
        	rs.next();
        	
        	rs.close();
        	statement.close();
        	
        	statement = conn.prepareStatement(sql);
        	
        	int updateCount = statement.executeUpdate();
        	
        	statement.close();
        	
        	conn.commit();
        	conn.close();
        	
        	return updateCount;
    	}
    }
}
