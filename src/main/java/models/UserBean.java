package models;

public class UserBean {
	private int id;
	private String email, name;
	private Boolean isAdmin;
	
	public UserBean(int id, String email, String name,String pass, Boolean isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
