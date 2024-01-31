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
			
			
	public OrderDetailBean findOrderDetailById(int arg_id) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM OrderDetails WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("orderId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				OrderDetailBean OrderDetail  = new OrderDetailBean(id, orderId, productId,riceId,quantity,createdAt);
				return OrderDetail;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
    }

	public ArrayList<OrderDetailBean> findOrderDetailsByOrderId(int arg_orderId) {
        ArrayList<OrderDetailBean> OrderDetails = new ArrayList<OrderDetailBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM OrderDetails WHERE orderId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_orderId);
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
<<<<<<< HEAD
    public int insert (int userId, int shopId,int orderId,int productId,int riceId,int quantity) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO orderdetail(userId,shopId,orderId, productId,riceId,quantity) " +
                "VALUES(?,?,?,?,?,?)";

    	try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // プリペアードステートメントのパラメータに値をセット
            statement.setInt(1, userId);
            statement.setInt(2, shopId);
            statement.setInt(3, orderId);
            statement.setInt(4, productId);
            statement.setInt(5, riceId);
            statement.setInt(6, quantity);
=======
	
	
    public void insert (int orderId, int productId, int riceId, int quantity) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO OrderDetails(orderId, productId, riceId, quantity, status) " +
                "VALUES(?, ?, ?, ?, ?)";

    	try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // プリペアードステートメントのパラメータに値をセット
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, riceId);
            statement.setInt(4, quantity);
            statement.setInt(5, 1);
>>>>>>> main

            // アップデートを実行
            statement.executeUpdate();
            return;
        }
    }
}
    
    
    public void delete(int id) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM OrderDetails WHERE id = ?";
            
            try(PreparedStatement statement = conn.prepareStatement(sql)) {
            	statement.setInt(1, id);
            	
            	int updateCount = statement.executeUpdate();
            	return;
            	
            }
        	
        	
    	}
   }
}
