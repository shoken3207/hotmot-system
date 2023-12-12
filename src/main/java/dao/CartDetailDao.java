package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CartDetailBean;

public class CartDetailDao extends CommonDao{

	public ArrayList<CartDetailBean> findAll() {
        ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM cartdetail";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail  = new CartDetailBean(id, cartId, productId,riceId,quantity,createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
    }
    public int insert (int id, int cartId, int productId, int riceId, int quantity, Date createdAt) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO cartdetails(id, cartId, productId,riceId,quantity,createdAt) " +
                "VALUES(" + id + "," + cartId + "," + productId + "," + riceId + "," + quantity + ","  + createdAt + ")";

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
    
    public int update(int id, int cartId, int productId, int riceId, int quantity, Date createdAt) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET cartdetails(id,cartId,productId,riceId,quantity,createdAt) " +
                    "VALUES(" + id + "," + cartId + "," + productId + "," + riceId + "," + quantity + "," + createdAt + ")";
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
    
    public int delete(int id, int cartId, int productId, int riceId, int quantity, Date createdAt) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM cartdetails WHERE id = " + id;
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
