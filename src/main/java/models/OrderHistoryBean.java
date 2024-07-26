package models;

/**
 * 注文履歴を表すBean
 * @author ezaki
 * @param id 注文のID, shopId ショップのID, userId ユーザーのID, createdAt 作成日時, details 注文詳細のリスト
 * @version 1.0.0
 */

import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryBean {
	int id, shopId, userId;
	Date createdAt;
	ArrayList<OrderDetailBean> details;
	public OrderHistoryBean(int id, int shopId, int userId, Date createdAt, ArrayList<OrderDetailBean> details) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public ArrayList<OrderDetailBean> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<OrderDetailBean> details) {
		this.details = details;
	}
	
	
}
