package models;

/**
 * ブックマークを追加するためのリクエストを表すBean
 * @author hira
 * @param　userId:ユーザーのId,productId:商品のId,categoryId:商品カテゴリーのId
 * @return　なし
 * @version 1.0.0
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookMarkRequestBean {
	String productId, userId, categoryId;

	@JsonCreator
	public AddBookMarkRequestBean(
			@JsonProperty("userId") String userId,
			@JsonProperty("productId") String productId,
            @JsonProperty("categoryId") String categoryId) {
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
