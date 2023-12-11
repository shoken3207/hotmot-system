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
			String sql = "SELECT * FROM user";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String name= rs.getString("name");
				Boolean isAdmin = rs.getBoolean("isAdmin");

				UserBean User = new UserBean(id, email, name, isAdmin);
				users.add(User);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
    }
    public int insert (int id,String email,String name,boolean isAdmin) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO user(id,email,name,isAdmin) " +
                "VALUES('" + id + "'," + email + "," + name + "," + isAdmin + ")";

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
    
    public int update(int id,String email,String name,boolean isAdmin) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET user(id,email,name,isAdmin) " +
                    "VALUES('" + id + "'," + email + "," + name + "," + isAdmin + ")";
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
            String sql = "DELETE FROM usert WHERE id = '" + id + "'";
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
