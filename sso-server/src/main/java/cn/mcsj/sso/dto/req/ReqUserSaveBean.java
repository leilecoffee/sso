package cn.mcsj.sso.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqUserSaveBean {

	@NotBlank(message = "{user.username.notBlank}")
	private String username;
	@NotBlank(message = "{user.password.notBlank}")
	private String password;
	@NotBlank(message = "{user.name.notBlank}")
	private String name;
	@NotBlank(message = "{user.phone.notBlank}")
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
