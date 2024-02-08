package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.OrderDetailBean;
import models.OrderDetailHistoryByAdminBean;

public class OrderDetailHistoryDao {
	private Connection conn;

	//SELECT文
	public ArrayList<OrderDetailBean> getOrderDetailHistory(int args_orderId)throws SQLException{
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		ArrayList<OrderDetailBean> OrderDetailHistory = new ArrayList<OrderDetailBean>();
		try {
			String sql = "SELECT * FROM orderdetails WHERE orderId = ? ORDER BY createdAt";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_orderId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("orderId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				int status = rs.getInt("status");
				Date createdAt = rs.getDate("createdAt");
				OrderDetailBean ob = new OrderDetailBean(id, orderId, productId, riceId, quantity, status,createdAt);

				OrderDetailHistory.add(ob);
			}
			ps.close();
            conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return OrderDetailHistory;
	}
	
	public ArrayList<OrderDetailHistoryByAdminBean> getOrderDetailHistoryByAdmin(String fromDate, String toDate)throws SQLException{
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		ArrayList<OrderDetailHistoryByAdminBean> histories = new ArrayList<OrderDetailHistoryByAdminBean>();
		try {
			String sql = "SELECT productId, riceId, SUM(quantity) as amount FROM orderdetails WHERE createdAt between ? AND ? GROUP BY productId, riceId;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("amount");
				OrderDetailHistoryByAdminBean history = new OrderDetailHistoryByAdminBean( productId, riceId, quantity);

				histories.add(history);
			}
			ps.close();
            conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return histories;
	}
}