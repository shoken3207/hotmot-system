package models;

/**
 * カートの詳細情報を編集するためのリクエストを表すBean
 * @author hira
 * @param　cartDetailId:カート詳細のId,quantity;商品の数量
 * @return　
 * @version 1.0.0
 */

public class EditCartDetailRequestBean {
	int cartDetailId,quantity;

	public EditCartDetailRequestBean(int cartDetailId, int quantity) {
		super();
		this.cartDetailId = cartDetailId;
		this.quantity = quantity;
	}

	public int getCartDetailId() {
		return cartDetailId;
	}

	public void setCartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
