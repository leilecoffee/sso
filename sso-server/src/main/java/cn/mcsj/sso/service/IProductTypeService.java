package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ProductType;

public interface IProductTypeService{
	
	ProductType getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<ProductType> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ProductType productType);

    int delete(Long primaryKey);
}
