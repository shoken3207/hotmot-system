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
			String sql = "SELECT * FROM Orders WHERE userId = ?";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Orders;
    }
	
    public void insertOrder (int arg_userId, int arg_shopId) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
	    	String sql = "INSERT INTO Orders(userId, shopId) VALUES(?, ?)";
	
	    	PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_userId);
			ps.setInt(2, arg_shopId);
			ps.executeUpdate();
    	
    	}
    	return;
    }
    
    
    public void deleteOrder(int arg_id) throws SQLException{
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM Orders WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,  arg_id);
        	statement.executeUpdate();
    	}
    	return;
   }
    
    public boolean confirmOrder(String userId, String cartId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false); // トランザクション開始

            // カートの所有者を確認
            String checkOwnershipQuery = "SELECT userId FROM carts WHERE cartId = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkOwnershipQuery)) {
                checkStmt.setString(1, cartId);
                ResultSet resultSet = checkStmt.executeQuery();

                if (!resultSet.next() || !resultSet.getString("userId").equals(userId)) {
                    // カートが存在しないか、ユーザーに紐づいていない場合
                    conn.rollback(); // ロールバック
                    return false;
                }
            }

            // 注文を作成する
            String insertOrderQuery = "INSERT INTO orders (userId, cartId) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertOrderQuery)) {
                insertStmt.setString(1, userId);
                insertStmt.setString(2, cartId);
                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected == 0) {
                    conn.rollback(); // ロールバック
                    return false;
                }
            }

            // 他の処理が必要ならばここに追加する

            conn.commit(); // トランザクションのコミット
            return true; // 成功を示すフラグ
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // 例外を呼び出し元に投げる
        }
    }
}
