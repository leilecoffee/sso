package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.req.ReqInfoPermissionSaveBean;
import cn.mcsj.sso.entity.UserInfoPermission;

public interface IUserInfoPermissionService{
	
	UserInfoPermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<UserInfoPermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ReqInfoPermissionSaveBean saveBean);

    int delete(Long primaryKey);
}
