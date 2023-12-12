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
		
	    public ArrayList<BookMarkBean> findAll() {
	        ArrayList<BookMarkBean> BookMarks = new ArrayList<BookMarkBean>();
			try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
				String sql = "SELECT * FROM bookmark";
				PreparedStatement ps = conn.prepareStatement(sql);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return BookMarks;
	    }
	    public int insert (int id, int userId, int productId, int categoryId, Date createdAt) throws SQLException {
	    	
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    	String sql = "INSERT INTO bookmark(id,userId,productId,categoryId,createdAt) " +
	                "VALUES(" + id + "," + userId + "," + productId + "," + categoryId + "," + createdAt + ")";

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
	    
	    public int update(int id, int userId, int productId, int categoryId, Date createdAt) throws SQLException {
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    		String sql = "UPDATE SET bookmark(id,userId,productId,categoryId,createdAt) " +
	                    "VALUES(" + id + "," + userId + "," + productId + "," + categoryId + "," + createdAt + ")";
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
	    
	    public int delete(int id, int userId, int productId, int categoryId, Date createdAt) throws SQLException{
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	            String sql = "DELETE FROM bookmark WHERE id = " + id;
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