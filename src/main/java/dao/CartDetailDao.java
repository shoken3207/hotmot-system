package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CartDetailBean;

public class CartDetailDao extends CommonDao{
		
	private Connection conn;
	public CartDetailDao() throws SQLException {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
	        }

	
	
	// SELECT文 - cartIDに一致する複数のCartDetailを表示
	public ArrayList<CartDetailBean> findAll(int args_cartId)throws SQLException  {
        ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try (conn = DriverManager.getConnection(URL, USER, PASS)) {
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

				CartDetailBean CartDetail  = new CartDetailBean(id, cartId, productId,riceId,quantity,createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
    }
	
	// SELECT文 - idに一致するひとつのCartDetailを表示
	public ArrayList<CartDetailBean> getCratDetailById(int args_id)throws SQLException  {
        ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try (conn = DriverManager.getConnection(URL, USER, PASS)) {
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

				CartDetailBean CartDetail  = new CartDetailBean(id, cartId, productId,riceId,quantity,createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
    }
	
	// SELECT文 - cartId,ProductId,riceIdに一致するひとつのCartDetailを表示
	public ArrayList<CartDetailBean> getCratDetail(int args_cartId,int args_productId, int args_riceId)throws SQLException  {
        ArrayList<CartDetailBean> CartDetails = new ArrayList<CartDetailBean>();
		try (conn = DriverManager.getConnection(URL, USER, PASS)) {
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

				CartDetailBean CartDetail  = new CartDetailBean(id, cartId, productId,riceId,quantity,createdAt);
				CartDetails.add(CartDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return CartDetails;
    }
	
	
	// INSERT文
    public int insert(int cartId, int productId, int riceId, int quantity) throws SQLException {
    	
    	try (conn = DriverManager.getConnection(URL, USER, PASS)) {
    	String sql = "INSERT INTO cartdetails(id, cartId, productId,riceId,quantity,createdAt) " +
                "VALUES(" + cartId + "," + productId + "," + riceId + "," + quantity + ")";

    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	rs.next();
    	
    	rs.close();
    	ps.close();
    	
    	ps= conn.prepareStatement(sql);
    	
    	int updateCount = ps.executeUpdate();
    	
    	ps.close();
    	
    	conn.commit();
    	conn.close();
    	
    	return updateCount;
    	}
    }
    
    // UPDATE文
    public int update(int id, int quantity) throws SQLException {
    	try (conn = DriverManager.getConnection(URL, USER, PASS)) {
    		String sql = "UPDATE CartDetails SET quantity =  ? WHERE id=?";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, quantity);
        	ps.setInt(2, id);
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	
        	rs.close();
        	ps.close();
        	
        	ps = conn.prepareStatement(sql);
        	
        	int updateCount = ps.executeUpdate();
        	
        	ps.close();
        	
        	conn.commit();
        	conn.close();
        	
        	return updateCount;
    	}
    }
    
    // DELETE文
    public int delete(int id) throws SQLException{
    	try (conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM cartdetails WHERE id = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	
        	rs.close();
        	ps.close();
        	
        	ps = conn.prepareStatement(sql);
        	
        	int updateCount = ps.executeUpdate();
        	
        	ps.close();
        	
        	conn.commit();
        	conn.close();
        	
        	return updateCount;
    	}
   }
}
