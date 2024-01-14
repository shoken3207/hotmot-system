package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.OrderBean;

public class OrderHistoryDao {
	private Connection conn;

	public OrderHistoryDao(Connection connection) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
		this.conn = connection;
	}

	//SELECT文
	public ArrayList<OrderBean> getOrderHistory(int args_userid)throws SQLException{
		ArrayList<OrderBean> OrderHistory = new ArrayList<OrderBean>();
		try {
			String sql = "SELECT * FROM Order WHERE userId = ? ORDER_BY createdAt";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_userid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");
				OrderBean ob = new OrderBean(id, userId, shopId, createdAt);

				OrderHistory.add(ob);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return OrderHistory;
	}
}