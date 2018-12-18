package cn.mcsj.sso.dto.req;

import javax.validation.constraints.NotNull;

import cn.mcsj.sso.dto.base.BaseReqPageBean;

public class ReqQuotedPageQueryBean extends BaseReqPageBean {
	@NotNull(message = "{info.state.notNull}")
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
