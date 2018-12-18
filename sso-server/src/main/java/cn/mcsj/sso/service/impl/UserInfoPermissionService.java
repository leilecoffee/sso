package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dao.UserInfoPermissionDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqUserInfoPermSaveBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.entity.UserInfoPermission;
import cn.mcsj.sso.service.IUserInfoPermissionService;
import cn.mcsj.sso.util.ApplicationUtil;

@Service
public class UserInfoPermissionService implements IUserInfoPermissionService {

	@Autowired
	private UserInfoPermissionDao userInfoPermissionDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserInfoPermission getOne(Long primaryKey) {
		return userInfoPermissionDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<UserInfoPermission> list(Map whereMap) {
		return userInfoPermissionDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = userInfoPermissionDao.total(whereMap);
		List<UserInfoPermission> rows = new ArrayList<UserInfoPermission>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public ResultVO save(ReqUserInfoPermSaveBean saveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("fromCompanyId", user.getCompanyId());
		whereMap.put("toCompanyId", saveBean.getCompanyId());
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		List<UserInfoPermission> list = userInfoPermissionDao.list(whereMap);
		if (list.size() > 0) {
			return new ResultVO(ResultCode.USER_INFO_PERM_EXIST);
		}
		UserInfoPermission entity = new UserInfoPermission();
		entity.setUserId(user.getUserId());
		entity.setFromCompanyId(user.getCompanyId());
		entity.setToCompanyId(saveBean.getCompanyId());
		entity.setInfoPermId(saveBean.getInfoPermId());
		entity.setPermColumn(saveBean.getPermColumn());
		entity.setPermColumnDesc(saveBean.getPermColumnDesc());
		userInfoPermissionDao.insert(entity);
		return new ResultVO();
	}

	/**
	 * 软删除
	 */
	@Override
	public ResultVO delete(Long primaryKey) {
		User user = ApplicationUtil.getCurrentUser();
		UserInfoPermission userInfoPermission = userInfoPermissionDao.getOne(primaryKey);
		if (userInfoPermission.getFromCompanyId().equals(user.getCompanyId())) {
			UserInfoPermission entity = new UserInfoPermission();
			entity.setId(primaryKey);
			entity.setIsDelete(DeleteEnum.Y.getCode());
			userInfoPermissionDao.update(entity);
			return new ResultVO();
		}
		return new ResultVO(ResultCode.PARAM_ERROR);
	}
}
