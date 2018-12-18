package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.InfoPermission;

public interface IInfoPermissionService{
	
	InfoPermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<InfoPermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(InfoPermission infoPermission);

    int delete(Long primaryKey);
}
