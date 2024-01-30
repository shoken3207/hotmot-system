package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.UserBean;

public class UserDao {
	
	private Connection createConnection() throws ClassNotFoundException,SQLException {
		 String URL  = "jdbc:mariadb://localhost/database?serverTimezone=JST";
		 String USER = "root";
		 String PASS = "mysql";
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,USER,PASS);
	}
	
    public List<UserBean> findAll(){
    	try (Connection conn = createConnection()) {
    	String sql = "SELECT * from UserBean";
    	PreparedStatement pstmt = conn.prepareStatement(sql);
    		
    		ResultSet rs = pstmt.executeQuery();
    		List<UserBean> list = new ArrayList<>();
    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String email = rs.getString("email");
    			String name = rs.getString("name");
    			boolean isAdmin = rs.getBoolean("isAdmin");
    			list.add(new UserBean(id,email,name, isAdmin));
    		}
    		return list;
    	}catch (ClassNotFoundException | SQLException e) {
    		throw new RuntimeException(e);
		}
    }
    
    public int insert(UserBean users) throws ClassNotFoundException, SQLException{
		try (Connection con = createConnection()){
		String sql = "INSERT INTO users(email,name) value(?,?);";
				PreparedStatement pstmt = con.prepareStatement(sql);
					
				pstmt.setString(1, users.getEmail());
				pstmt.setString(2, users.getName());
					
				return pstmt.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
    public int update(UserBean users) throws SQLException {
		try (Connection con = createConnection()){
		String sql = "UPDATE users SET (email,name) " +
				"VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, users.getEmail());
			pstmt.setString(2, users.getName());
				
			return pstmt.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
    public int delete(UserBean users) throws SQLException{
    	try (Connection con = createConnection()) {
            String sql = "DELETE FROM user WHERE id = " ;
            PreparedStatement pstmt = con.prepareStatement(sql);
        	
            pstmt.setInt(1,users.getId());
            pstmt.setString(2, users.getEmail());
            pstmt.setString(3,users.getName());
            pstmt.setBoolean(4,users.getIsAdmin());
            
            return pstmt.executeUpdate();
    	}catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}