package servlet;

import java.io.Serializable;

public class UserServ implements Serializable{
	private String id;
	private String name;
	private String pass;
       
    public UserServ() {}
    public UserServ(String id,String name,String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
    public String getId() {return id;}
    public String getPass() {return pass;}
    public String getName() {return name;}
}
