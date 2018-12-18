package cn.mcsj.sso.dto.req;

import cn.mcsj.sso.dto.base.BaseReqPageBean;

public class ReqInfoPermissionPageQueryBean extends BaseReqPageBean {

	private Long infoPermId;

	private String companyName;

	public Long getInfoPermId() {
		return infoPermId;
	}

	public void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
