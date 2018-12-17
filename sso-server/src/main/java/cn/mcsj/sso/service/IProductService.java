package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Product;

public interface IProductService{
	
	Product getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Product> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(Product product);

    int delete(Long primaryKey);
}
