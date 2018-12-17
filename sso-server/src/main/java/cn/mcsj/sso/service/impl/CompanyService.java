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
import cn.mcsj.sso.dao.CompanyDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.service.ICompanyService;

@Service
public class CompanyService implements ICompanyService {
	
	@Autowired
	private CompanyDao companyDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Company getOne(Long primaryKey) {
		return companyDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Company> list(Map whereMap) {
		return companyDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = companyDao.total(whereMap);
		List<Company> rows = new ArrayList<Company>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(Company company) {
		Long companyId = company.getCompanyId();
		if (companyId == null) {
			return companyDao.insert(company);
		} else {
			return companyDao.update(company);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return companyDao.delete(primaryKey);
	}
}
