package models;

/**
 * カートの詳細情報を更新するためのリクエストを表すBean
 * @author hira
 * @param　id カート詳細のId,quantity 更新後の数量
 * @return　なし
 * @version 1.0.0
 */

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
