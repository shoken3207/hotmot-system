package models;

public class AddBookMarkRequest {
	String productId, userId, categoryId;

	public AddBookMarkRequest(String productId, String userId, String categoryId) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
