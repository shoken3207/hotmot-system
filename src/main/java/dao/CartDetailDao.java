package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.AddCartDetailRequestBean;
import models.CartDetailBean;
import models.UpdateCartDetailRequestBean;

public class CartDetailDao extends CommonDao {

	private Connection conn;

	public CartDetailDao(Connection connection) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
		this.conn = connection;
	}

	// SELECT文 - cartIDに一致する複数のCartDetailを表示
	public ArrayList<CartDetailBean> getCartDetails(int args_cartId) throws SQLException {
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetail WHERE cartId=? ORDER_BY productId";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_cartId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");
				CartDetailBean cd = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				
				CartDetails.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CartDetails;
	}

	// SELECT文 - idに一致するひとつのCartDetailを表示
	public ArrayList<CartDetailBean> getCratDetailById(int args_id) throws SQLException {
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetail WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				CartDetails.add(CartDetail);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
	}

	// SELECT文 - cartId,ProductId,riceIdに一致するひとつのCartDetailを表示
	public ArrayList<CartDetailBean> getCratDetail(int args_cartId, int args_productId, int args_riceId)
			throws SQLException {
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetail WHERE cartid=? and productId=? and riceId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_cartId);
			ps.setInt(1, args_productId);
			ps.setInt(1, args_riceId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				CartDetails.add(CartDetail);
			} else {
				return null;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
	}

	// INSERT文
	public void insert(ArrayList<AddCartDetailRequestBean> addCartDetailsRequest) throws SQLException {

		try {
			String sql = "INSERT INTO cartdetails(cartId, productId,riceId,quantity) VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			for (AddCartDetailRequestBean addCartDetailRequest : addCartDetailsRequest) {
				ps.setInt(1, addCartDetailRequest.getCartId());
				ps.setInt(2, addCartDetailRequest.getProductId());
				ps.setInt(3, addCartDetailRequest.getRiceId());
				ps.setInt(4, addCartDetailRequest.getQuantity());
				ps.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// UPDATE文
	public void update(ArrayList<UpdateCartDetailRequestBean> updateCartDetailsRequest, ArrayList<CartDetailBean> cartDetails) throws SQLException {
		
		
		try {
			String sql = "UPDATE CartDetails SET quantity = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			String sql2 = "DELETE FROM CartDetails WHERE id = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);

			for (UpdateCartDetailRequestBean updateCartDetailRequest : updateCartDetailsRequest) {

				if( updateCartDetailRequest.getQuantity() != 0 ) {
					ps.setInt(1, updateCartDetailRequest.getQuantity());
					ps.setInt(2, updateCartDetailRequest.getQuantity());
					ps.executeQuery();

				}else {
					ps2.setInt(1, updateCartDetailRequest.getId());
					ps2.executeQuery();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	// DELETE文
	public void delete(int id) throws SQLException {
		try {
			String sql = "DELETE FROM cartdetails WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
