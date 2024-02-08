package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.OrderBean;

public class OrderDao extends CommonDao{
	
	public OrderBean findOrderById(int arg_id) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM Orders WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");

				OrderBean Order  = new OrderBean(id, userId, shopId,createdAt);
				ps.close();
	            conn.close();
				return Order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
    }

	public ArrayList<OrderBean> findOrdersByUserId(int arg_userId) {
        ArrayList<OrderBean> Orders = new ArrayList<OrderBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM Orders WHERE userId = ? ORDER BY createdAt DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");

				OrderBean Order  = new OrderBean(id, userId, shopId,createdAt);
				Orders.add(Order);
			}
			
			ps.close();
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Orders;
    }
	
	public ArrayList<OrderBean> findOrdersByDate(String fromDate, String toDate) {
        ArrayList<OrderBean> Orders = new ArrayList<OrderBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM Orders WHERE createdAt BETWEEN ? and ? ORDER BY createdAt DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");

				OrderBean Order  = new OrderBean(id, userId, shopId,createdAt);
				Orders.add(Order);
			}
			
			ps.close();
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Orders;
    }
	
    public int insertOrder (int arg_userId, int arg_shopId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    	String sql = "INSERT INTO Orders(userId, shopId) VALUES(?, ?)";
	
	    	PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, arg_userId);
			ps.setInt(2, arg_shopId);
//			ps.executeUpdate();
			
			int rowsAffected = ps.executeUpdate();

            // 最後に挿入された主キーの取得
            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int lastInsertedId = generatedKeys.getInt(1);
                    ps.close();
    	            conn.close();
                    return lastInsertedId;
                }
            }
    	} catch (Exception e) {
            System.out.println("エラー: " + e.getMessage());
        }
    	return 0;
    }
    
    
    public void deleteOrder(int arg_id) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM Orders WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,  arg_id);
        	statement.executeUpdate();
        	
        	statement.close();
            conn.close();
    	}
    	return;
   }
    
    
}
