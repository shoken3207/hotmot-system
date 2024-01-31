package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.OrderDetailBean;

public class OrderListDao extends CommonDao {
	public ArrayList<OrderDetailBean> findAll(int userId) {
        ArrayList<OrderDetailBean> OrderDetails = new ArrayList<OrderDetailBean>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM orderdetail WHERE userId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userId);
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
    public int select (int userId, int shopId,int orderId,int productId,int riceId,int quantity) throws SQLException {
    	
    	try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "SELECT name from " +
                "VALUES(?,?,?,?,?,?)";

    	try (PreparedStatement statement = conn.prepareStatement(sql)) {
            // プリペアードステートメントのパラメータに値をセット
            statement.setInt(1, userId);
            statement.setInt(2, shopId);
            statement.setInt(3, orderId);
            statement.setInt(4, productId);
            statement.setInt(5, riceId);
            statement.setInt(6, quantity);

            // アップデートを実行
            int updateCount = statement.executeUpdate();

            // トランザクションをコミット
            conn.commit();

            return updateCount;
        }
    }
   }
}
