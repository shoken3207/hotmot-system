package models;

import java.util.Date;

public class CartBean {
	private int id, userId, shopId;
	private Date createdAt;
	public CartBean(int id, int userId, int shopId, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.shopId = shopId;
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}


