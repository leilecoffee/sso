package cn.mcsj.sso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoPermissionPageQueryBean;
import cn.mcsj.sso.dto.req.ReqInfoPermissionSaveBean;
import cn.mcsj.sso.dto.res.ResUserInfoPermBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.InfoPermission;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.entity.UserInfoPermission;
import cn.mcsj.sso.service.ICompanyService;
import cn.mcsj.sso.service.IInfoPermissionService;
import cn.mcsj.sso.service.IUserInfoPermissionService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/userInfoPerm")
public class UserInfoPermController {

	@Autowired
	private IUserInfoPermissionService userInfoPermissionService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IInfoPermissionService infoPermissionService;

	/**
	 * 获取登录人授权的信息
	 * 
	 * @param pageQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/query")
	public ResultVO query(@Valid @RequestBody ReqInfoPermissionPageQueryBean pageQueryBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("fromCompanyId", user.getCompanyId());
		whereMap.put("infoPermId", pageQueryBean.getInfoPermId());
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		whereMap.put("orderByClause", "create_time DESC");
		PageBean pageBean = new PageBean(pageQueryBean.getPageNum(), pageQueryBean.getPageSize());
		userInfoPermissionService.page(pageBean, whereMap);
		List<ResUserInfoPermBean> data = new ArrayList<ResUserInfoPermBean>();
		List<UserInfoPermission> rows = (List<UserInfoPermission>) pageBean.getRows();
		for (UserInfoPermission userInfoPerm : rows) {
			ResUserInfoPermBean bean = new ResUserInfoPermBean();
			Company company = companyService.getOne(userInfoPerm.getToCompanyId());
			bean.setId(userInfoPerm.getId());
			bean.setCreateTime(userInfoPerm.getCreateTime());
			bean.setCompanyName(company.getName());
			bean.setVisitColumn(userInfoPerm.getPermColumnDesc());
			InfoPermission infoPerm = infoPermissionService.getOne(userInfoPerm.getInfoPermId());
			bean.setVisitTable(infoPerm.getName());
			data.add(bean);
		}
		pageBean.setRows(data);
		return new ResultVO(pageBean);
	}
	@PostMapping("/save")
	public ResultVO save(@Valid @RequestBody ReqInfoPermissionSaveBean saveBean) {
		userInfoPermissionService.save(saveBean);
		return new ResultVO();
	}

}
