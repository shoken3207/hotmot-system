package models;

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
