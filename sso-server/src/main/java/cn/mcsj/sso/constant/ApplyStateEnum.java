package cn.mcsj.sso.constant;

public enum ApplyStateEnum {

	peeding(0), checked(1), reject(2);
	private int code;

	ApplyStateEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
