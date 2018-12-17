package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dao.ProductTypePermissionDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ProductTypePermission;
import cn.mcsj.sso.service.IProductTypePermissionService;

@Service
public class ProductTypePermissionService implements IProductTypePermissionService {
	
	@Autowired
	private ProductTypePermissionDao productTypePermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ProductTypePermission getOne(Long primaryKey) {
		return productTypePermissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ProductTypePermission> list(Map whereMap) {
		return productTypePermissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = productTypePermissionDao.total(whereMap);
		List<ProductTypePermission> rows = new ArrayList<ProductTypePermission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(ProductTypePermission productTypePermission) {
		Long id = productTypePermission.getId();
		if (id == null) {
			return productTypePermissionDao.insert(productTypePermission);
		} else {
			return productTypePermissionDao.update(productTypePermission);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return productTypePermissionDao.delete(primaryKey);
	}
}
