package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.BookMarkBean;

public class BookMarkDao extends CommonDao{
		
	    public ArrayList<BookMarkBean> findBookMarksByUserId(int arg_userId) {
	        ArrayList<BookMarkBean> BookMarks = new ArrayList<BookMarkBean>();
			try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
				String sql = "SELECT * FROM bookmarks WHERE userId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, arg_userId);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					int userId = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int categoryId = rs.getInt("categoryId");
					Date createdAt = rs.getDate("createdAt");

					System.out.println(id);
					System.out.println(userId);
					System.out.println(productId);
					System.out.println(categoryId);
					System.out.println(createdAt);
					BookMarkBean BookMark  = new BookMarkBean(id, userId, productId,categoryId,createdAt);
					BookMarks.add(BookMark);
				}
				
				 ps.close();
	            conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return BookMarks;
	    }
	    
	    public ArrayList<BookMarkBean> findCategoryBookMarksByUserId(int arg_userId, int arg_categoryId) {
	        ArrayList<BookMarkBean> BookMarks = new ArrayList<BookMarkBean>();
			try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
				String sql = "SELECT * FROM bookmarks WHERE userId = ? AND categoryId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, arg_userId);
				ps.setInt(1, arg_categoryId);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					int id = rs.getInt("id");
					int userId = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int categoryId = rs.getInt("categoryId");
					Date createdAt = rs.getDate("createdAt");

					BookMarkBean BookMark  = new BookMarkBean(id, userId, productId,categoryId,createdAt);
					BookMarks.add(BookMark);
				}
				
				ps.close();
	            conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return BookMarks;
	    }
	    
	    public BookMarkBean findBookMarkById(int arg_id) {
//	    	BookMarkBean bookMark;
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
				String sql = "SELECT * FROM bookmarks WHERE id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, arg_id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					int userId = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int categoryId = rs.getInt("categoryId");
					Date createdAt = rs.getDate("createdAt");
					
					BookMarkBean bookMark  = new BookMarkBean(id, userId, productId,categoryId,createdAt);
					
					ps.close();
		            conn.close();
					return bookMark;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
	    public BookMarkBean findBookMark(int arg_productId, int arg_userId) {
//	    	BookMarkBean bookMark;
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
				String sql = "SELECT * FROM bookmarks WHERE productId = ? AND userId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, arg_productId);
				ps.setInt(2, arg_userId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					int userId = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int categoryId = rs.getInt("categoryId");
					Date createdAt = rs.getDate("createdAt");
					
					BookMarkBean bookMark  = new BookMarkBean(id, userId, productId,categoryId,createdAt);
					ps.close();
		            conn.close();
					return bookMark;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	    
	    public void insert (int userId, int productId, int categoryId) throws SQLException {
	    	
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
		    	String sql = "INSERT INTO bookmarks(userId,productId,categoryId) " +
		                "VALUES(?, ?, ?)";
		    	PreparedStatement statement = conn.prepareStatement(sql);
		    	statement.setInt(1, userId);
		    	statement.setInt(2, productId);
		    	statement.setInt(3, categoryId);
		    	
		    	statement.executeUpdate();
		    	
		    	statement.close();
	            conn.close();
	    	} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void delete (int id) throws SQLException {
	    	
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
		    	String sql = "DELETE FROM bookmarks WHERE id = ?";
		    	PreparedStatement statement = conn.prepareStatement(sql);
		    	statement.setInt(1, id);
		    	
		    	statement.executeUpdate();
		    	
		    	statement.close();
	            conn.close();
	    	} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
}