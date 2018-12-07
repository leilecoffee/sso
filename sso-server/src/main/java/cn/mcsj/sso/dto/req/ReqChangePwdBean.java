package cn.mcsj.sso.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReqChangePwdBean {
	
	@NotBlank(message="")
	private String oldPwd;
	@NotBlank(message="")
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	
}
