package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqUserInfoPermSaveBean;
import cn.mcsj.sso.entity.UserInfoPermission;

public interface IUserInfoPermissionService{
	
	UserInfoPermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<UserInfoPermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	ResultVO save(ReqUserInfoPermSaveBean saveBean);

	ResultVO delete(Long primaryKey);
}
