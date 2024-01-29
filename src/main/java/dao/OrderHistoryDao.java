package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.OrderBean;

public class OrderHistoryDao extends CommonDao{
	private Connection conn;
	
//	//SELECT文
//	public ArrayList<OrderBean> getOrderHistory(int args_userid)throws SQLException{
//		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
//		ArrayList<OrderBean> OrderHistory = new ArrayList<OrderBean>();
//		try {
//			String sql = "SELECT * FROM Order WHERE userId = ? ORDER_BY createdAt";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, args_userid);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				int id = rs.getInt("id");
//				int userId = rs.getInt("userId");
//				int shopId = rs.getInt("shopId");
//				Date createdAt = rs.getDate("createdAt");
//				OrderBean ob = new OrderBean(id, userId, shopId, createdAt);
//
//				OrderHistory.add(ob);
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return OrderHistory;
//	}
	
	//orderIdの取得のみ
	public int getOrderId(int args_userid)throws SQLException{
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		ArrayList<OrderBean> OrderHistory = new ArrayList<OrderBean>();
		int id=0;
		System.out.println(args_userid);
		try {
			String sql = "SELECT id FROM Orders WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_userid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				System.out.println(rs.getInt("id"));
				id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int shopId = rs.getInt("shopId");
				Date createdAt = rs.getDate("createdAt");
				OrderBean ob = new OrderBean(id, userId, shopId, createdAt);
				
				OrderBean order = new OrderBean(id, userId, shopId, createdAt);
				OrderHistory.add(order);
			}
			//ps.setInt(orderId, "id");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}