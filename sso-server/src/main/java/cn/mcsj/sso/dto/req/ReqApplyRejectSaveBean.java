package cn.mcsj.sso.dto.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqApplyRejectSaveBean {

	@NotNull(message = "{applyId.notNull}")
	private Long applyId;
	@NotBlank(message = "{apply.reason.notBlank}")
	private String reason;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
