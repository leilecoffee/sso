package cn.mcsj.sso.constant;

public enum ResultCode {
	SUCCESS(200, "success"),
	FAIL(500, "系统开小差，请稍后重试!"),
	PARAM_ERROR(4001, "参数错误"),
	LOGIN_FAIL(4002, "账号或密码错误"),
	UNAUTHORIZED(4003, "您没有当前操作的权限！"),
	SESSION_EXPIRED(4004, "会话过期，请重新登录"),
	USERNAME_EXIST(4005, "用户名重复"),
	UNLOGIN(4006, "未登录"),
	LOGOUT(4007, "注销成功"),
	ROLE_OR_PERMISSION_CHANGED(4008, "用户角色或权限发生改变,请重新登录!"),
	USER_INFO_PERM_EXIST(4009, "公司权限信息已存在!"),
	PASSWORD_ERROR(4009, "原始密码错误");
	private int code;

	private String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
