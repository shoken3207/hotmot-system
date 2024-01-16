package models;

public class AddCartDetailRequestBean {
	int cartId,productId,riceId,quantity;

	public AddCartDetailRequestBean(int cartId, int productId, int riceId, int quantity) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.riceId = riceId;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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
