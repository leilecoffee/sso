package cn.mcsj.sso.dto.base;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

import cn.mcsj.sso.constant.ResultCode;

public class ResultVO {
	@JSONField(ordinal = 1)
	private int code = 200;
	@JSONField(ordinal = 2)
	private String msg = "success";
	@JSONField(ordinal = 3)
	@SuppressWarnings("rawtypes")
	private Object data = new ArrayList();

	public ResultVO() {
		code = ResultCode.SUCCESS.getCode();
		msg = ResultCode.SUCCESS.getMessage();
	}

	public ResultVO(ResultCode messageEnum) {
		this.code = messageEnum.getCode();
		this.msg = messageEnum.getMessage();
	}

	public ResultVO(ResultCode messageEnum, Object data) {
		this.code = messageEnum.getCode();
		this.msg = messageEnum.getMessage();
		this.data = data;
	}

	public ResultVO(Object data) {
		if (data != null)
			this.data = data;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
