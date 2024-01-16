package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.OrderDetailBean;

public class OrderDetailHistoryDao {
	private Connection conn;

	public OrderDetailHistoryDao(Connection connection) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
		this.conn = connection;
	}

	//SELECT文
	public ArrayList<OrderDetailBean> getOrderDetailHistory(int args_orderId)throws SQLException{
		ArrayList<OrderDetailBean> OrderDetailHistory = new ArrayList<OrderDetailBean>();
		try {
			String sql = "SELECT * FROM OrderDetail WHERE orderId = ? ORDER_BY createdAt";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_orderId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("orderId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quanitity = rs.getInt("quanitity");
				int status = rs.getInt("status");
				Date createdAt = rs.getDate("createdAt");
				OrderDetailBean ob = new OrderDetailBean(id, orderId, productId, riceId, quanitity, status,createdAt);

				OrderDetailHistory.add(ob);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return OrderDetailHistory;
	}
}