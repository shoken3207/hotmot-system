package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookMarkRequestBean {
	String userId, productId;

	@JsonCreator
	public DeleteBookMarkRequestBean(
			@JsonProperty("userId") String userId,
			@JsonProperty("productId") String productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
