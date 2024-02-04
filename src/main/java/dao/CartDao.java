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
	
	public CartBean findCartById(int arg_id) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM Carts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");

				CartBean Cart  = new CartBean(id, userId, shopId,createdAt);
				
				ps.close();
	            conn.close();
				return Cart;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
    }
	
	public CartBean findCartByUserId(int arg_userId) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM Carts WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");

				CartBean Cart  = new CartBean(id, userId, shopId,createdAt);
				
				ps.close();
	            conn.close();
				return Cart;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
    }

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
			
			ps.close();
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Carts;
    }
    public int insert (int userId, int shopId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO carts(userId, shopId) " +
                "VALUES(" + userId + "," + shopId + ")";
    	System.out.print(userId+","+shopId);
    	System.out.print(sql);

    	PreparedStatement statement = conn.prepareStatement(sql);
    	
    	int updateCount = statement.executeUpdate();
    	statement.close();
    	conn.close();
    	
    	return updateCount;
    	}
    }
    
    public int update(int id, int userId, int shopId) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET cart(id,userId,shopId,createdAt) " +
                    "VALUES(" + id + "," + userId + "," + shopId + "," + ")";
        	PreparedStatement statement = conn.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery();
        	rs.next();
        	
        	rs.close();
        	statement.close();
        	
        	statement = conn.prepareStatement(sql);
        	
        	int updateCount = statement.executeUpdate();
        	
        	statement.close();
        	conn.close();
        	
        	return updateCount;
    	}
    }
    
    public int delete(int id, int orderId, int productId, int riceId, int quantity) throws SQLException{
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
        	conn.close();
        	
        	return updateCount;
    	}
    	}
   }