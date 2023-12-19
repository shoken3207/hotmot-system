package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.UserBean;

public class UserDao extends CommonDao {
	
    public ArrayList<UserBean> findAll(){
    	ArrayList<UserBean> Users = new ArrayList<UserBean>();
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "SELECT * from users";
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    	ResultSet rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String email = rs.getString("email");
    			String name = rs.getString("name");
    			boolean isAdmin = rs.getBoolean("isAdmin");
    			
    			UserBean User = new UserBean(id,email,name,isAdmin);
    			Users.add(User);
    		 }
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
    	
    	return Users;
    }
    
    public int insert(UserBean users) throws ClassNotFoundException, SQLException{
		try (Connection con = DriverManager.getConnection(URL, USER, PASS)){
		String sql = "INSERT INTO users(email,name) value(?,?);";
				PreparedStatement pstmt = con.prepareStatement(sql);
					
				pstmt.setString(1, users.getEmail());
				pstmt.setString(2, users.getName());
					
				return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
    public int update(UserBean users) throws SQLException {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS)){
		String sql = "UPDATE users SET (email,name) " +
				"VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, users.getEmail());
			pstmt.setString(2, users.getName());
				
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
    public int delete(UserBean users) throws SQLException{
    	try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM user WHERE id = " ;
            PreparedStatement pstmt = con.prepareStatement(sql);
        	
            pstmt.setInt(1,users.getId());
            pstmt.setString(2, users.getEmail());
            pstmt.setString(3,users.getName());
            pstmt.setBoolean(4,users.getIsAdmin());
            
            return pstmt.executeUpdate();
    	}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}