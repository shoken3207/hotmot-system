package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.UserBean;

public class UserDao extends CommonDao {
	
	/**
	 * このUserDaoクラスは、データベース操作用のメソッドを提供します。
	 * 主な機能は、ユーザーの検索、挿入、更新、削除です。
	 * @author hayashi
	 * @param　arg_email
	 * @param　arg_password
	 * @return user
	 * @version 1.0.0
	 */
	
	public UserBean findUser(String arg_email,String arg_password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?"; 
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
            statement.close();
			con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
        return null;
    }
	
	/**
	 * 指定されたarg_emailに一致するユーザーをデータベースから検索し、
	 その情報を含むUserBeanオブジェクトを返します。
	 * @author hayashi
	 * @param　arg_email
	 * @return なし
	 * @version 1.0.0
	 */
	public UserBean findUserByEmail(String arg_email) {
        String query = "SELECT * FROM users WHERE email = ?"; 
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, arg_email);
            ResultSet rs = statement.executeQuery();
           
            while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				boolean isAdmin = rs.getBoolean("isAdmin");

				UserBean user  = new UserBean(id, email, name, pass, isAdmin);
				statement.close();
				con.close();
				return user;
			}

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
        return null;
    }
	
	/**
	 * 指定されたIDに一致するユーザーをデータベースから検索し、
	 その情報を含むUserBeanオブジェクトを返す。
	 * @author hayashi
	 * @param　arg_id
	 * @return なし
	 * @version 1.0.0
	 */
	public UserBean findUserById(int arg_id) {
        String query = "SELECT * FROM users WHERE id = ?"; 
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, arg_id);
            ResultSet rs = statement.executeQuery();
           
            while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				boolean isAdmin = rs.getBoolean("isAdmin");

				UserBean user  = new UserBean(id, email, name, pass, isAdmin);
				statement.close();
				con.close();
				return user;
			}

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを適切に行う
        }
        return null;
    }
	
	/**
	 * 指定されたarg_name、arg_email、password、および管理者権限
	 を持つユーザーをデータベースに挿入する。
	 * @author hayashi
	 * @param　arg_name
	 * @param　arg_email
	 * @param　password
	 * @return なし
	 * @version 1.0.0
	 */
	public void insertUser (String arg_name, String arg_email, String arg_password, Boolean arg_isAdmin) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    	String sql = "INSERT INTO Users(name, email, password, isAdmin) VALUES(?, ?, ?, ?)";
	
	    	PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, arg_name);
			ps.setString(2, arg_email);
			ps.setString(3, arg_password);
			ps.setBoolean(4, arg_isAdmin);
			ps.executeUpdate();
			ps.close();
            conn.close();
    	}catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	return;
    }
    

	/**
	 * 指定されたUserBeanオブジェクトの情報を使用してデータベースに新しい
	 *ユーザーを挿入し、挿入が成功した場合は1を返します。
	 * @author hayashi
	 * @param　users
	 * @return １だったらEmailを返し、２だったらNameを返す。
	 * @version 1.0.0
	 */
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
    
    /**
	 * 指定されたUserBeanオブジェクトの情報を使用してデータベース内の
	 ユーザー情報を更新し、更新が成功した場合に影響を受けた行の数を示す整数を返します。
	 * @author hayashi
	 * @param　users
	 * @return １だったらEmailを返し、２だったらNameを返す。
	 * @version 1.0.0
	 */
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
    
    /**
   	 * 指定されたUserBeanオブジェクトの情報を使用してデータベースから
   	 ユーザーを削除し、削除が成功した場合に影響を受けた行の数を示す整数
   	 を返します。
   	 * @author hayashi
   	 * @param　users
   	 * @return １：Email、２：Email、3：Name、４：IsAdminを返す。
   	 * @version 1.0.0
   	 */
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

	
}
