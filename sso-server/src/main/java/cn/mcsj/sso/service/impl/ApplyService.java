package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mcsj.sso.constant.ApplyStateEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dao.ApplyDao;
import cn.mcsj.sso.dao.UserDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqApplyAuthorizationSaveBean;
import cn.mcsj.sso.dto.req.ReqApplyRejectSaveBean;
import cn.mcsj.sso.dto.req.ReqApplySaveBean;
import cn.mcsj.sso.dto.req.ReqUserInfoPermSaveBean;
import cn.mcsj.sso.entity.Apply;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IApplyService;
import cn.mcsj.sso.service.IUserInfoPermissionService;
import cn.mcsj.sso.util.ApplicationUtil;

@Service
public class ApplyService implements IApplyService {

	@Autowired
	private ApplyDao applyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IUserInfoPermissionService userInfoPermissionService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Apply getOne(Long primaryKey) {
		return applyDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Apply> list(Map whereMap) {
		return applyDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = applyDao.total(whereMap);
		List<Apply> rows = new ArrayList<Apply>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Transactional
	@Override
	public int save(ReqApplySaveBean saveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Apply entity = new Apply();
		entity.setToCompanyId(saveBean.getCompanyId());
		entity.setUserId(user.getUserId());
		return applyDao.insert(entity);
	}

	@Override
	public int delete(Long primaryKey) {
		return applyDao.delete(primaryKey);
	}

	@Override
	@Transactional
	public ResultVO authorization(ReqApplyAuthorizationSaveBean saveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Apply apply = applyDao.getOne(saveBean.getApplyId());
		if (apply.getToCompanyId().equals(user.getCompanyId())) {
			Apply entity = new Apply();
			entity.setApplyId(apply.getApplyId());
			entity.setState(ApplyStateEnum.checked.getCode());
			applyDao.update(entity);
		} else {
			return new ResultVO(ResultCode.PARAM_ERROR);
		}
		User auser = userDao.getOne(apply.getUserId());
		ReqUserInfoPermSaveBean bean = new ReqUserInfoPermSaveBean();
		bean.setCompanyId(auser.getCompanyId());
		bean.setInfoPermId(saveBean.getInfoPermId());
		bean.setPermColumn(saveBean.getPermColumn());
		bean.setPermColumnDesc(saveBean.getPermColumnDesc());
		userInfoPermissionService.save(bean);
		return new ResultVO();
	}

	@Override
	public ResultVO reject(ReqApplyRejectSaveBean saveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Apply apply = applyDao.getOne(saveBean.getApplyId());
		if (apply.getToCompanyId().equals(user.getCompanyId())) {
			Apply entity = new Apply();
			entity.setApplyId(apply.getApplyId());
			entity.setState(ApplyStateEnum.reject.getCode());
			entity.setReason(saveBean.getReason());
			applyDao.update(entity);
			return new ResultVO();
		} else {
			return new ResultVO(ResultCode.PARAM_ERROR);
		}
	}
}
