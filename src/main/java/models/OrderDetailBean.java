package models;

import java.util.Date;

public class OrderDetailBean {
	private int id, orderId, productId, riceId, quantity;
	private Date createdAt;

	public OrderDetailBean(int id, int orderId, int productId, int riceId, int quantity, Date createdAt) {
		super();
		this.id = id;
		this.orderId = orderId;
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
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
