package models;

import java.util.Date;

public class CartDetailBean {
	private int id, cartId, productId, riceId, quantity;
	private Date createdAt;
	
	public CartDetailBean(int id, int cartId, int productId, int riceId, int quantity, Date createdAt) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.riceId = riceId;
		this.quantity = quantity;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getRiceId() {
		return riceId;
	}

	public void setRiceId(int riceId) {
		this.riceId = riceId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}


