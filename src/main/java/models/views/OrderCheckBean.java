package models.views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderCheckBean implements Serializable {
	private int img;
	private String productName;
	private String option;
	List<ItemsBean> nameList = new ArrayList<ItemsBean>();
	
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	} 

}
