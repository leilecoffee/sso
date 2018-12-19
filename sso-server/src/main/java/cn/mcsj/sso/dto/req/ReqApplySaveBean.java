package cn.mcsj.sso.dto.req;

import javax.validation.constraints.NotNull;

public class ReqApplySaveBean {
	
	@NotNull(message="{companyName.notNull}")
	private Long companyId;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
}
