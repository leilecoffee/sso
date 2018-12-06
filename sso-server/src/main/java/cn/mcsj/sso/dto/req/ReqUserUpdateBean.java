package cn.mcsj.sso.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqUserUpdateBean {

	@NotNull(message = "{user.userid.notNull}")
	private Long userId;
	@NotBlank(message = "{user.name.notBlank}")
	private String name;
	@NotBlank(message = "{user.phone.notBlank}")
	private String phone;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
