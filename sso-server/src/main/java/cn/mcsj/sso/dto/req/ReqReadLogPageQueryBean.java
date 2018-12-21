package cn.mcsj.sso.dto.req;

import cn.mcsj.sso.dto.base.BaseReqPageBean;

public class ReqReadLogPageQueryBean extends BaseReqPageBean {
	
	private Long companyId;
	
	private Long infoPermId;

	public synchronized Long getCompanyId() {
		return companyId;
	}

	public synchronized void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public synchronized Long getInfoPermId() {
		return infoPermId;
	}

	public synchronized void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}
}
