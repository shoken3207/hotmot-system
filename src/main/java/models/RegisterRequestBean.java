package models;

/**
 * 登録リクエストを表すBean
 * @author ezaki
 * @param name ユーザーの名前, email メールアドレス, password パスワード
 * @version 1.0.0
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequestBean {
	String name, email, password;

	@JsonCreator
	public RegisterRequestBean(@JsonProperty("name") String name,
			@JsonProperty("email") String email,
            @JsonProperty("password") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
