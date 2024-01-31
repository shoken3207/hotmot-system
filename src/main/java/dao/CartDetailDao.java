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

	// SELECT文 - cartIDに一致する複数のCartDetailを表示 
	public ArrayList<CartDetailBean> getCartDetails(int args_cartId) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetails WHERE cartId=? ORDER BY createdAt";
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
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetails WHERE id=?";
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
				return CartDetails;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return CartDetails;
	}

	// SELECT文 - cartId,ProductId,riceIdに一致するひとつのCartDetailを表示 
	public CartDetailBean getCartDetail(int args_cartId, int args_productId, int args_riceId)throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "SELECT * FROM CartDetails WHERE cartId=? and productId=? and riceId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_cartId);
			ps.setInt(2, args_productId);
			ps.setInt(3, args_riceId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				System.out.println("CartDetail");
				System.out.println(CartDetail);
				return CartDetail;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// INSERT文 
	public void insert(ArrayList<AddCartDetailRequestBean> addCartDetailsRequest) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "INSERT INTO CartDetails(cartId, productId,riceId,quantity) VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			for (AddCartDetailRequestBean addCartDetailRequest : addCartDetailsRequest) {

				ps.setInt(1, addCartDetailRequest.getCartId());
				ps.setInt(2, addCartDetailRequest.getProductId());
				ps.setInt(3, addCartDetailRequest.getRiceId());
				ps.setInt(4, addCartDetailRequest.getQuantity()); 

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createCartDetail(int cartId, int productId, int riceId, int quantity) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "INSERT INTO CartDetails(cartId, productId,riceId,quantity) VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cartId);
			ps.setInt(2, productId);
			ps.setInt(3, riceId);
			ps.setInt(4, quantity); 
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	    	

	// UPDATE文
	public void updateCartDetail(int id, int quantity) throws SQLException {
    	try{
    		String sql = "UPDATE cartdetails SET quantity = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			int rs = ps.executeUpdate();
        	
    	}catch (SQLException e) {
			e.printStackTrace();
		}

    }

	// DELETE文
	public int delete1(int id) throws SQLException{
    	try{
            String sql = "DELETE FROM cartdetails WHERE id = ?" ;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
        	
    	}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    	

	}

	// UPDATE文 
	public void update(ArrayList<UpdateCartDetailRequestBean> updateCartDetailsRequest) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "UPDATE CartDetails SET quantity = ? WHERE id = ?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			String sql2 = "DELETE FROM CartDetails WHERE id = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);

			for (UpdateCartDetailRequestBean updateCartDetailRequest : updateCartDetailsRequest) {
				if (updateCartDetailRequest.getQuantity() != 0) {
					ps1.setInt(1, updateCartDetailRequest.getQuantity());
					ps1.setInt(2, updateCartDetailRequest.getId());
					
					ps1.executeUpdate();
				} else {
					ps2.setInt(1, updateCartDetailRequest.getId());
					ps2.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE文 
	public void delete(int id) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "DELETE FROM cartdetails WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCartDetailsByCartId(int arg_cartId) throws SQLException {
		conn = DriverManager.getConnection(CommonDao.URL, CommonDao.USER, CommonDao.PASS);
		try {
			String sql = "DELETE FROM CartDetails WHERE cartId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, arg_cartId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
