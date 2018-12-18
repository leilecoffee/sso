package cn.mcsj.sso.constant;

public enum DeleteEnum {
	
	N(0),Y(1);
	
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
