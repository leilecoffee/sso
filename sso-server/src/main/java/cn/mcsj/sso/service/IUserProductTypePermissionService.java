package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.UserProductTypePermission;

public interface IUserProductTypePermissionService{
	
	UserProductTypePermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<UserProductTypePermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(UserProductTypePermission userProductTypePermission);

    int delete(Long primaryKey);
}
