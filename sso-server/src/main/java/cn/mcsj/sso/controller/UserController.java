package cn.mcsj.sso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqChangePwdBean;
import cn.mcsj.sso.dto.req.ReqUserPageBean;
import cn.mcsj.sso.dto.req.ReqUserSaveBean;
import cn.mcsj.sso.dto.req.ReqUserUpdateBean;
import cn.mcsj.sso.dto.res.ResLoginBean;
import cn.mcsj.sso.dto.res.ResUserBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.ICompanyService;
import cn.mcsj.sso.service.IUserService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ICompanyService companyService;

	@SuppressWarnings("unchecked")
	@RequiresPermissions("sys:user:list")
	@PostMapping("/page")
	public ResultVO page(@Valid @RequestBody ReqUserPageBean userPageBean) {
		int pageNum = userPageBean.getPageNum();
		int pageSize = userPageBean.getPageSize();
		PageBean page = new PageBean(pageNum, pageSize);
		userService.page(page, new HashMap<>());
		List<User> users = (List<User>) page.getRows();
		List<ResUserBean> data = new ArrayList<ResUserBean>();
		for (User user : users) {
			ResUserBean bean = new ResUserBean();
			BeanUtils.copyProperties(user, bean);
			data.add(bean);
		}
		page.setRows(data);
		return new ResultVO(page);
	}

	@RequiresPermissions("sys:user:insert")
	@PostMapping("/insert")
	public ResultVO insert(@Valid @RequestBody ReqUserSaveBean userSaveBean) {
		return userService.insert(userSaveBean);
	}

	@RequiresPermissions("sys:user:update")
	@PostMapping("/update")
	public ResultVO update(@Valid @RequestBody ReqUserUpdateBean userUpdateBean) {
		userService.update(userUpdateBean);
		return new ResultVO();
	}

	@RequiresPermissions("sys:user:delete")
	@PostMapping("/delete/{id}")
	public ResultVO delete(@Validated @PathParam("id") Long id) {
		return new ResultVO(ResultCode.SUCCESS);
	}

	@PostMapping("/info")
	public ResultVO userInfo() {
		User user = ApplicationUtil.getCurrentUser();
		User auser = userService.getOne(user.getUserId());
		ResUserBean bean = new ResUserBean();
		BeanUtils.copyProperties(auser, bean);
		if (auser.getCompanyId() != null) {
			Company company = companyService.getOne(auser.getCompanyId());
			bean.setCompanyName(company.getName());
		}
		return new ResultVO(bean);
	}

	@PostMapping("/modify")
	public ResultVO modify(@Valid @RequestBody ReqUserUpdateBean userUpdateBean) {
		User user = ApplicationUtil.getCurrentUser();
		userUpdateBean.setUserId(user.getUserId());
		userService.update(userUpdateBean);
		ResLoginBean data = new ResLoginBean();
		data.setName(userUpdateBean.getName());
		return new ResultVO(data);
	}

	@PostMapping("/changepwd")
	public ResultVO changePwd(@Valid @RequestBody ReqChangePwdBean changePwdBean) {
		return userService.changePwd(changePwdBean);
	}
}
