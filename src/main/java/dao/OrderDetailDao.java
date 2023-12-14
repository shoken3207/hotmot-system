package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.OrderDetailBean;

public class OrderDetailDao extends CommonDao{

	public ArrayList<OrderDetailBean> findAll() {
        ArrayList<OrderDetailBean> OrderDetails = new ArrayList<OrderDetailBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM orderdetail";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("orderId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				OrderDetailBean OrderDetail  = new OrderDetailBean(id, orderId, productId,riceId,quantity,createdAt);
				OrderDetails.add(OrderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return OrderDetails;
    }
    public int insert (int id, int orderId, int productId, int riceId, int quantity,Date createdAt) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO orderdetail(id, orderId, productId,riceId,quantity,createdAt) " +
                "VALUES(" + id + "," + orderId + "," + productId + "," + riceId + "," + quantity + "," + createdAt + ")";

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
    
    public int update(int id, int orderId, int productId, int riceId, int quantity,Date createdAt) throws SQLException {
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE SET orderdetail(id,orderId,productId,riceId,quantity,createdAt) " +
                    "VALUES(" + id + "," + orderId + "," + productId + "," + riceId + "," + quantity + "," + createdAt + ")";
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
            String sql = "DELETE FROM orderdetail WHERE id = " + id;
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
