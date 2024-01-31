package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.User;

import models.UserBean;

public class UserDao extends CommonDao {
	
	public UserBean findUserById(String arg_email,String arg_password) {
        String query = "SELECT * FROM users WHERE email = ? AND pass = ?"; 
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, arg_email);
            statement.setString(2, arg_password);
            ResultSet rs = statement.executeQuery();
           
            while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				boolean isAdmin = rs.getBoolean("isAdmin");

				UserBean user  = new UserBean(id, email, name, pass, isAdmin);
				return user;
			}

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
        return null;
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
            String sql = "DELETE FROM users WHERE id = " ;
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

	public int getCartId(int userId) {
		// TODO 自動生成されたメソッド・スタブ
		String query = "SELECT * FROM carts WHERE userId = ?"; 
		int cartId = 0;
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
            	System.out.print("true");
            	cartId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }return cartId; 
	}

	public User findByName(String name1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getUser(String email1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}