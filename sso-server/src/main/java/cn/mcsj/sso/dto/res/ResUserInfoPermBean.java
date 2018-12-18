package cn.mcsj.sso.dto.res;

import java.util.Date;

public class ResUserInfoPermBean {

	private Long id;

	private String companyName;

	private String visitTable;

	private String visitColumn;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVisitTable() {
		return visitTable;
	}

	public void setVisitTable(String visitTable) {
		this.visitTable = visitTable;
	}

	public String getVisitColumn() {
		return visitColumn;
	}

	public void setVisitColumn(String visitColumn) {
		this.visitColumn = visitColumn;
	}

	public String getCreateTime() {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
