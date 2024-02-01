package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		//ArrayList<OrderBean> OrderHistory = new ArrayList<OrderBean>();
		int id=0;
		try {
			String sql = "SELECT id FROM orders WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_userid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getInt("id");
//				int userId = rs.getInt("userId");
//				int shopId = rs.getInt("shopId");
//				Date createdAt = rs.getDate("createdAt");
//				OrderBean order = new OrderBean(id, userId, shopId, createdAt);
//				OrderHistory.add(order);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}