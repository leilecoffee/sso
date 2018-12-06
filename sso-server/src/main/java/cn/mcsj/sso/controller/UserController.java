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
import cn.mcsj.sso.dto.req.ReqUserPageBean;
import cn.mcsj.sso.dto.req.ReqUserSaveBean;
import cn.mcsj.sso.dto.req.ReqUserUpdateBean;
import cn.mcsj.sso.dto.res.ResUserPageBean;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@SuppressWarnings("unchecked")
	@RequiresPermissions("sys:user:list")
	@PostMapping("/page")
	public ResultVO page(@Valid @RequestBody ReqUserPageBean userPageBean) {
		int pageNum = userPageBean.getPageNum();
		int pageSize = userPageBean.getPageSize();
		PageBean page = new PageBean(pageNum, pageSize);
		userService.page(page, new HashMap<>());
		List<User> users = (List<User>) page.getRows();
		List<ResUserPageBean> data = new ArrayList<ResUserPageBean>();
		for (User user : users) {
			ResUserPageBean bean = new ResUserPageBean();
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
		return userService.update(userUpdateBean);
	}

	@RequiresPermissions("sys:user:delete")
	@PostMapping("/delete/{id}")
	public ResultVO delete(@Validated @PathParam("id") Long id) {
		return new ResultVO(ResultCode.SUCCESS);
	}
}
