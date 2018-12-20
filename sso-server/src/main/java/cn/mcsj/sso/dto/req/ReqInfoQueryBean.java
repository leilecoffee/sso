package cn.mcsj.sso.dto.req;

import java.util.List;

public class ReqInfoQueryBean {

	private String startDate;

	private String endDate;

	private Long companyId;

	private List<Long> infoTypes;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<Long> getInfoTypes() {
		return infoTypes;
	}

	public void setInfoTypes(List<Long> infoTypes) {
		this.infoTypes = infoTypes;
	}

}
