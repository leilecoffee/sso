package cn.mcsj.sso.service.impl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.dao.QuotedDao;
import cn.mcsj.sso.dao.ReadLogDao;
import cn.mcsj.sso.dao.UserInfoPermissionDao;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoDetailBean;
import cn.mcsj.sso.dto.req.ReqInfoQueryBean;
import cn.mcsj.sso.dto.res.ResInfoQueryBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.entity.ReadLog;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.entity.UserInfoPermission;
import cn.mcsj.sso.service.IInfoService;
import cn.mcsj.sso.util.ApplicationUtil;

@Service
public class InfoService implements IInfoService {

	@Autowired
	private UserInfoPermissionDao userInfoPermissionDao;
	@Autowired
	private QuotedDao quotedDao;
	@Autowired
	private ReadLogDao readLogDao;

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public List<ResInfoQueryBean> readerQuery(ReqInfoQueryBean queryBean) {
		User user = ApplicationUtil.getCurrentUser();
		List<Long> infoPermIds = queryBean.getInfoTypes();
		// 查询登录人公司的授权记录
		Map<String, Object> whereMap = new HashMap<String, Object>();
		if (queryBean.getCompanyId() != null) {
			whereMap.put("fromCompanyId", queryBean.getCompanyId());
		}
		whereMap.put("toCompanyId", user.getCompanyId());
		whereMap.put("infoPermIds", infoPermIds);
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		List<UserInfoPermission> list = userInfoPermissionDao.list(whereMap);
		Map<Long, List<UserInfoPermission>> data = new HashMap<>();
		Long infoPermId = null;
		for (UserInfoPermission userInfoPermission : list) {
			infoPermId = userInfoPermission.getInfoPermId();
			if (data.keySet().contains(infoPermId)) {
				data.get(infoPermId).add(userInfoPermission);
			} else {
				data.put(infoPermId, new ArrayList<UserInfoPermission>() {
					{
						add(userInfoPermission);
					}
				});
			}
		}
		List<ResInfoQueryBean> resultData = new ArrayList<ResInfoQueryBean>();
		// 根据不同信息类型获取不同数据
		Set<Long> sets = data.keySet();
		for (Long key : sets) {
			if (key.equals(1L)) {
				ResInfoQueryBean result = new ResInfoQueryBean();
				List<UserInfoPermission> userInfoPermissions = data.get(key);
				List<Long> companyIds = new ArrayList<Long>();
				for (UserInfoPermission userInfoPermission : userInfoPermissions) {
					companyIds.add(userInfoPermission.getFromCompanyId());
				}
				whereMap.clear();
				whereMap.put("companyIds", companyIds);
				whereMap.put("isDelete", DeleteEnum.N.getCode());
				whereMap.put("visitPerm", true);
				if (queryBean.getStartDate() != null && queryBean.getEndDate() != null) {
					whereMap.put("startDate", queryBean.getStartDate());
					whereMap.put("endDate", queryBean.getEndDate());
				}
				List<Quoted> queryQuotedList = quotedDao.list(whereMap);
				List<Quoted> resultQuotedList = new ArrayList<Quoted>();
				List<String> propertyList = new ArrayList<String>();
				PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(Quoted.class);
				for (PropertyDescriptor p : propertyDescriptors) {
					propertyList.add(p.getName());
				}
				for (Quoted quoted : queryQuotedList) {
					Quoted item = new Quoted();
					for (UserInfoPermission perm : userInfoPermissions) {
						if (perm.getFromCompanyId().equals(quoted.getCompanyId())) {
							String properties = perm.getPermColumn();
							List<String> ownPropertiesList = (properties != null ? Arrays.asList(properties.split(","))
									: null);
							List<String> ignoreList = ListUtils.subtract(propertyList, ownPropertiesList);
							String[] ignoreArray = new String[ignoreList.size()];
							ignoreList.toArray(ignoreArray);
							BeanUtils.copyProperties(quoted, item, ignoreArray);
							item.setId(quoted.getId());
							break;
						}
					}
					resultQuotedList.add(item);
				}
				result.setInfoType(key);
				result.setData(resultQuotedList);
				resultData.add(result);
			} else {
				ResInfoQueryBean result = new ResInfoQueryBean();
				result.setInfoType(key);
				resultData.add(result);
			}
		}

		return resultData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVO detail(ReqInfoDetailBean detailBean) {
		User user = ApplicationUtil.getCurrentUser();
		ResInfoQueryBean result = new ResInfoQueryBean();
		result.setInfoType(detailBean.getInfoType());
		// 查询授权信息
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("toCompanyId", user.getCompanyId());
		whereMap.put("infoPermId", detailBean.getInfoType());
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		List<UserInfoPermission> list = userInfoPermissionDao.list(whereMap);

		if (list.size() == 0) {
			return new ResultVO(result);
		}
		UserInfoPermission userInfoPermission = list.get(0);
		if (detailBean.getInfoType().equals(1L)) {
			whereMap.clear();
			whereMap.put("id", detailBean.getInfoId());
			whereMap.put("companyId", userInfoPermission.getFromCompanyId());
			whereMap.put("isDelete", DeleteEnum.N.getCode());
			whereMap.put("visitPerm", true);
			List<Quoted> queryQuotedList = quotedDao.list(whereMap);

			if (queryQuotedList.size() == 0) {
				return new ResultVO(result);
			}

			List<String> propertyList = new ArrayList<String>();
			PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(Quoted.class);
			for (PropertyDescriptor p : propertyDescriptors) {
				propertyList.add(p.getName());
			}

			String properties = userInfoPermission.getPermColumn();
			List<String> ownPropertiesList = (properties != null ? Arrays.asList(properties.split(",")) : null);
			List<String> ignoreList = ListUtils.subtract(propertyList, ownPropertiesList);
			String[] ignoreArray = new String[ignoreList.size()];
			ignoreList.toArray(ignoreArray);
			Quoted item = new Quoted();
			BeanUtils.copyProperties(queryQuotedList.get(0), item, ignoreArray);
			result.setData(item);
			ReadLog entity = new ReadLog();
			entity.setUserId(user.getUserId());
			entity.setReadCompanyId(user.getCompanyId());
			entity.setPublishCompanyId(userInfoPermission.getFromCompanyId());
			entity.setInfoPermId(detailBean.getInfoType());
			entity.setInfoId(detailBean.getInfoId());
			readLogDao.insert(entity);
		}
		return new ResultVO(result);
	}

}
