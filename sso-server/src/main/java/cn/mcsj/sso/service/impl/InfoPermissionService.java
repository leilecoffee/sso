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
import cn.mcsj.sso.dao.InfoPermissionDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.InfoPermission;
import cn.mcsj.sso.service.IInfoPermissionService;

@Service
public class InfoPermissionService implements IInfoPermissionService {
	
	@Autowired
	private InfoPermissionDao infoPermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public InfoPermission getOne(Long primaryKey) {
		return infoPermissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<InfoPermission> list(Map whereMap) {
		return infoPermissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = infoPermissionDao.total(whereMap);
		List<InfoPermission> rows = new ArrayList<InfoPermission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(InfoPermission infoPermission) {
		Long infoPermId = infoPermission.getInfoPermId();
		if (infoPermId == null) {
			return infoPermissionDao.insert(infoPermission);
		} else {
			return infoPermissionDao.update(infoPermission);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return infoPermissionDao.delete(primaryKey);
	}
}
