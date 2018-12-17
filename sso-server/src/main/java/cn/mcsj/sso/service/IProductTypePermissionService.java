package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ProductTypePermission;

public interface IProductTypePermissionService{
	
	ProductTypePermission getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<ProductTypePermission> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ProductTypePermission productTypePermission);

    int delete(Long primaryKey);
}
