package models;

/**
 * ブックマーク情報を表すBean
 * @author hira
 * @param　id:ブックマークのId,userId:ユーザーのId,productId:商品のId,categoryId:商品カテゴリーのId
 * @return　なし
 * @version 1.0.0
 */

import java.util.Date;

public class BookMarkBean {
	private int id, userId, productId, categoryId;
	private Date createdAt;
	
	public BookMarkBean(int id, int userId, int productId, int categoryId, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.categoryId = categoryId;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
