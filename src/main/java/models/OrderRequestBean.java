package models;

/**
 * 注文リクエストを表すBean
 * @author ezaki
 * @param cartId カートのID
 * @version 1.0.0
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestBean {
	String cartId;
	
	@JsonCreator
	public OrderRequestBean(
			@JsonProperty("cartId") String cartId) {
		super();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
}
