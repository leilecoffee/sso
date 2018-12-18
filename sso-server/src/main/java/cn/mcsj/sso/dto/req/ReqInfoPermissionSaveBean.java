package cn.mcsj.sso.dto.req;

public class ReqInfoPermissionSaveBean {

	private Long companyId;

	private Long infoPermId;

	private String permColumn;
	private String permColumnDesc;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getInfoPermId() {
		return infoPermId;
	}

	public void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}

	public String getPermColumn() {
		return permColumn;
	}

	public void setPermColumn(String permColumn) {
		this.permColumn = permColumn;
	}

	public String getPermColumnDesc() {
		return permColumnDesc;
	}

	public void setPermColumnDesc(String permColumnDesc) {
		this.permColumnDesc = permColumnDesc;
	}

}
