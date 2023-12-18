package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CartDetailBean;

public class CartDetailDao extends CommonDao {

	private Connection conn;

	public CartDetailDao() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
	}

	// SELECT文 - cartIDに一致する複数のCartDetailを表示
	public ArrayList<CartDetailBean> findAll(int args_cartId) throws SQLException {
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

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				CartDetails.add(CartDetail);
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

			while (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
	}

	// SELECT文 - cartId,ProductId,riceIdに一致するひとつのCartDetailを表示
	public ArrayList<CartDetailBean> getCratDetail(int args_cartId, int args_productId, int args_riceId)throws SQLException {
		ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try {
			String sql = "SELECT * FROM cartdetail WHERE cartid=? and productId=? and riceId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, args_cartId);
			ps.setInt(1, args_productId);
			ps.setInt(1, args_riceId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int cartId = rs.getInt("cartId");
				int productId = rs.getInt("productId");
				int riceId = rs.getInt("riceId");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("createdAt");

				CartDetailBean CartDetail = new CartDetailBean(id, cartId, productId, riceId, quantity, createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CartDetails;
	}

	// INSERT文
	public void insert(ArrayList<Integer> CartDetailRequest) throws SQLException {
    	
    	try{
    		String sql = "INSERT INTO cartdetails(cartId, productId,riceId,quantity) VALUES(?,?,?,?)";
    		PreparedStatement ps = conn.prepareStatement(sql);

			for (int i = 0; i < CartDetailRequest.length; i++) {
				ps.setInt(1, CartDetailRequest[i].getCartId());
				ps.setInt(2, CartDetailRequest[i].getProductId());
				ps.setInt(3, CartDetailRequest[i].getRiceId());
				ps.setInt(4, CartDetailRequest[i].getQuantity());
		    	ResultSet rs = ps.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	    	

	// UPDATE文
	public void update(ArrayList<Integer> CartDetailRequest) throws SQLException {
    	try{
        	PreparedStatement ps = conn.prepareStatement(sql);
        	
        	if(quantity != 1) {
        		String sql = "UPDATE CartDetails SET quantity =  ? WHERE id=?";
        		for(int i = 0; i < CartDetailReques.length; i++) {
        			ps.setInt(1, CartDetailReques[i].getQuantity());
            		ps.setInt(2, CartDetailReques[i].getId());
            		ResultSet rs = ps.executeQuery();
        		}
        	}else {
        		String sql = "DELETE FROM cartdetails WHERE id = ?";
        		ps.setInt(1, CartDetailReques[i].getId());
        	}
    	}catch (SQLException e) {
			e.printStackTrace();
		}

    }

	// DELETE文
	public int delete(int id) throws SQLException{
    	try{
            String sql = "DELETE FROM cartdetails WHERE id = ?" ;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	
    	}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    	
	}

}
