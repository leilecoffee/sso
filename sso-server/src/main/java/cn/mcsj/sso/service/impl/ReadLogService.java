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
import cn.mcsj.sso.dao.ReadLogDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ReadLog;
import cn.mcsj.sso.service.IReadLogService;

@Service
public class ReadLogService implements IReadLogService {
	
	@Autowired
	private ReadLogDao readLogDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ReadLog getOne(Long primaryKey) {
		return readLogDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ReadLog> list(Map whereMap) {
		return readLogDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = readLogDao.total(whereMap);
		List<ReadLog> rows = new ArrayList<ReadLog>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(ReadLog readLog) {
		Long id = readLog.getId();
		if (id == null) {
			return readLogDao.insert(readLog);
		} else {
			return readLogDao.update(readLog);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return readLogDao.delete(primaryKey);
	}
}
