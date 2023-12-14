package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CartBean;

public class CartDao extends CommonDao{

	public ArrayList<CartBean> findAll() {
        ArrayList<CartBean> Carts = new ArrayList<CartBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM cart";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("orderId");
				int shopId = rs.getInt("productId");
				Date createdAt = rs.getDate("createdAt");

				CartBean Cart  = new CartBean(id, userId, shopId,createdAt);
				Carts.add(Cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Carts;
    }
    public int insert (int id, int userId, int shopId,Date createdAt) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO cart(id, userId, shopId,createdAt) " +
                "VALUES(" + id + "," + userId + "," + shopId + ","  + createdAt + ")";

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
    
    public int update(int id, int userId, int shopId,Date createdAt) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET cart(id,userId,shopId,createdAt) " +
                    "VALUES(" + id + "," + userId + "," + shopId + "," + createdAt + ")";
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
    
    public int delete(int id, int orderId, int productId, int riceId, int quantity,Date createdAt) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM cart WHERE id = " + id;
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
