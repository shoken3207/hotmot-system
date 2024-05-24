package models;

/**
 * 管理者による注文詳細履歴を表すBean
 * @author ezaki
 * @param productId 商品のID, riceId ライスのID, quantity 数量
 * @version 1.0.0
 */

public class OrderDetailHistoryByAdminBean {
	int productId, riceId, quantity;

	public OrderDetailHistoryByAdminBean(int productId, int riceId, int quantity) {
		super();
		this.productId = productId;
		this.riceId = riceId;
		this.quantity = quantity;
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
	
}
