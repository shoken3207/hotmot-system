package models;

/**
 * カートの詳細情報を追加するためのリクエストを表すBean
 * @author hira
 * @param　cartId:カートのId,productid:商品のId,riceId:ライスのオプションId,quantity:商品の数量
 * @return　なし
 * @version 1.0.0
 */

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
