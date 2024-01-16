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
    	//System.out.println(email+","+name);
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO users(email,name,isAdmin) " +
                "VALUES('" +email + "','" + name +"','" + 0 + "')";
    	
    	PreparedStatement statement = conn.prepareStatement(sql);
    	ResultSet rs = statement.executeQuery();
    	System.out.println(rs);
    	
    	rs.close();
    	statement.close();
    	
    	conn.close();
    	System.out.print(email);
    	return 0;
    	}
    	
    }
    public int select(String email) throws SQLException{
    	System.out.println(email);
    	int userId=0;
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "SELECT id FROM users WHERE email = ?";
    		PreparedStatement statement = conn.prepareStatement(sql);
    		statement.setString(1, email);
    		ResultSet rs = statement.executeQuery();
    	
    		if (rs.next()) {
    			userId = rs.getInt("id");
    			System.out.print(userId);
    	    	
    	    	rs.close();
    	    	statement.close();
    	    	
    	    	conn.close();
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return userId;
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
