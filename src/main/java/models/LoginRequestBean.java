package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ログイン情報を格納するモデル
 * @author hayashi
 * @param　ログインで入力された、EmailとPassword
 * @return　Email、Passwordを返す
 * @version 1.0.0
 */

public class LoginRequestBean {
	String email, password;

	@JsonCreator
	public LoginRequestBean(
			@JsonProperty("email") String email,
            @JsonProperty("password") String password) {
		super();
		this.email = email;
		this.password = password;
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
