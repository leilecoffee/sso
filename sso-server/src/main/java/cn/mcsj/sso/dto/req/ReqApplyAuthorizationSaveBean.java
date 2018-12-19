package cn.mcsj.sso.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqApplyAuthorizationSaveBean {

	@NotNull(message = "{applyId.notNull}")
	private Long applyId;
	@NotNull(message="{permTable.notNull}")
	private Long infoPermId;
	@NotBlank(message="{permColumn.notBlank}")
	private String permColumn;
	@NotBlank(message="{permColumnDesc.notBlan}")
	private String permColumnDesc;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
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
