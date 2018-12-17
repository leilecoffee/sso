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
import cn.mcsj.sso.dao.QuotedDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.service.IQuotedService;

@Service
public class QuotedService implements IQuotedService {
	
	@Autowired
	private QuotedDao quotedDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Quoted getOne(Long primaryKey) {
		return quotedDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Quoted> list(Map whereMap) {
		return quotedDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = quotedDao.total(whereMap);
		List<Quoted> rows = new ArrayList<Quoted>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(Quoted quoted) {
		Long id = quoted.getId();
		if (id == null) {
			return quotedDao.insert(quoted);
		} else {
			return quotedDao.update(quoted);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return quotedDao.delete(primaryKey);
	}
}
