package models.json;

public class AllergyBean {
	private int Id;
	
	public AllergyBean() {
	}
	
	private String name;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
