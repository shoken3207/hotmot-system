package models;

public class UpdateCartDetailRequestBean {
	private int id, quantity;

	public UpdateCartDetailRequestBean(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
