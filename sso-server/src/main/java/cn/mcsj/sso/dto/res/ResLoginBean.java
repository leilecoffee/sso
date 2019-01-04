package cn.mcsj.sso.dto.res;

import java.util.Date;

public class ResLoginBean {

	private String name;
	
	private String companyName;
	
	private Date loginTime = new Date();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized String getCompanyName() {
		return companyName;
	}

	public synchronized void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public  String getLoginTime() {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.loginTime);
	}

	public  void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	
}
