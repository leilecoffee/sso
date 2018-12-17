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
import cn.mcsj.sso.dao.UserProductTypePermissionDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.UserProductTypePermission;
import cn.mcsj.sso.service.IUserProductTypePermissionService;

@Service
public class UserProductTypePermissionService implements IUserProductTypePermissionService {
	
	@Autowired
	private UserProductTypePermissionDao userProductTypePermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public UserProductTypePermission getOne(Long primaryKey) {
		return userProductTypePermissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<UserProductTypePermission> list(Map whereMap) {
		return userProductTypePermissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = userProductTypePermissionDao.total(whereMap);
		List<UserProductTypePermission> rows = new ArrayList<UserProductTypePermission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(UserProductTypePermission userProductTypePermission) {
		Long id = userProductTypePermission.getId();
		if (id == null) {
			return userProductTypePermissionDao.insert(userProductTypePermission);
		} else {
			return userProductTypePermissionDao.update(userProductTypePermission);
		}
	}

	@Override
	public int delete(Long primaryKey) {
		return userProductTypePermissionDao.delete(primaryKey);
	}
}
