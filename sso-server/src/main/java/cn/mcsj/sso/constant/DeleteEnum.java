package cn.mcsj.sso.constant;

public enum DeleteEnum {
	
	Y(0),N(1);
	
	private int code;

	DeleteEnum(int code){
		this.code = code;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
