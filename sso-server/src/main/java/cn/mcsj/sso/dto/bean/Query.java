package cn.mcsj.sso.dto.bean;

import java.util.List;

import cn.mcsj.sso.entity.UserInfoPermission;

public class Query {
	
	private Long infoPermId;
	
	private List<UserInfoPermission> userInfoPermission;

	public Long getInfoPermId() {
		return infoPermId;
	}

	public void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}

	public List<UserInfoPermission> getUserInfoPermission() {
		return userInfoPermission;
	}

	public void setUserInfoPermission(List<UserInfoPermission> userInfoPermission) {
		this.userInfoPermission = userInfoPermission;
	}

	
	
	
}
