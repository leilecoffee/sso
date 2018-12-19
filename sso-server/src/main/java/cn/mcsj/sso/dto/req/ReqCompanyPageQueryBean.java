package cn.mcsj.sso.dto.req;

import cn.mcsj.sso.dto.base.BaseReqPageBean;

public class ReqCompanyPageQueryBean extends BaseReqPageBean{

	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
