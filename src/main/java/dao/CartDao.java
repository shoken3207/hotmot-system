package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CartDetailBean;

public class CartDao extends CommonDao {
	public ArrayList<CartDetailBean> findAll() {
		ArrayList<CartDetailBean> cartDetails = new ArrayList<CartDetailBean>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			String sql = "SELECT * FROM cartDetail";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId= rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean cartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				cartDetails.add(cartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartDetails;
	}
}
