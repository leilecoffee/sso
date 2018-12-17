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
import cn.mcsj.sso.dao.ProductTypeDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ProductType;
import cn.mcsj.sso.service.IProductTypeService;

@Service
public class ProductTypeService implements IProductTypeService {
	
	@Autowired
	private ProductTypeDao productTypeDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ProductType getOne(Long primaryKey) {
		return productTypeDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ProductType> list(Map whereMap) {
		return productTypeDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = productTypeDao.total(whereMap);
		List<ProductType> rows = new ArrayList<ProductType>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(ProductType productType) {
		Long productTypeId = productType.getProductTypeId();
		if (productTypeId == null) {
			return productTypeDao.insert(productType);
		} else {
			return productTypeDao.update(productType);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return productTypeDao.delete(primaryKey);
	}
}
