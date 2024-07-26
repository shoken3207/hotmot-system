package models;

/**
 * 注文の詳細情報を表すBean
 * @author ezaki
 * @param id 注文詳細のID, orderId 注文のID, productId 商品のID, riceId ライスのID, quantity 数量, status ステータス, createdAt 作成日時
 * @version 1.0.0
 */

import java.util.Date;

public class OrderDetailBean {
	private int id, orderId, productId, riceId, quantity, status;
	private Date createdAt;

	public OrderDetailBean(int id, int orderId, int productId, int riceId, int quantity, int status, Date createdAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.riceId = riceId;
		this.quantity = quantity;
		this.status = status;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
