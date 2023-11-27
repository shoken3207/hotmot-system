package models.views;

public class ItemsBean {
	private int cartDetailId;
	private String userName ;
	private String status;
	
	public ItemsBean(){}
	public ItemsBean(int cartDetailId,String userName,String status) {
		this.cartDetailId = cartDetailId;
		this.userName = userName;
		this.status = status;
	}
	public int getcartDetailId() {
		return cartDetailId;
		}
	public void setcartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
		}
	public String getuserName() {
		return userName;
		}
	public void setuserName(String userName) {
		this.userName = userName;
		}
	public String getstatus() {
		return status;
		}
	public void setstatus(String status) {
		this.status = status;
		}
}
