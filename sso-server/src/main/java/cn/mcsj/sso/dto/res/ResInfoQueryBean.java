package cn.mcsj.sso.dto.res;

import java.util.ArrayList;

public class ResInfoQueryBean {

	private Long infoType;

	private Object data = new ArrayList<>();

	public Long getInfoType() {
		return infoType;
	}

	public void setInfoType(Long infoType) {
		this.infoType = infoType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
