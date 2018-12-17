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
import cn.mcsj.sso.dao.ProductDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Product;
import cn.mcsj.sso.service.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductDao productDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Product getOne(Long primaryKey) {
		return productDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Product> list(Map whereMap) {
		return productDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = productDao.total(whereMap);
		List<Product> rows = new ArrayList<Product>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(Product product) {
		Long productId = product.getProductId();
		if (productId == null) {
			return productDao.insert(product);
		} else {
			return productDao.update(product);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return productDao.delete(primaryKey);
	}
}
