package cn.mcsj.sso.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.res.ResMenuBean;
import cn.mcsj.sso.entity.Menu;
import cn.mcsj.sso.entity.Permission;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IMenuService;
import cn.mcsj.sso.service.IPermissionService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/sys")
public class SystemController {
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IMenuService menuService;

	/**
	 * 获取当前登录用户的菜单
	 * 
	 * @return
	 */
	@PostMapping("/menu")
	public ResultVO getMenu() {
		User user = ApplicationUtil.getCurrentUser();
		List<Menu> menuList = menuService.getMenuByUserId(user.getUserId());
		ResMenuBean menuBean = new ResMenuBean(GlobalConstant.ROOT_NODE_ID, null, null);
		convert(menuBean, menuList);
		return new ResultVO(menuBean);
	}

	/**
	 * 菜单集合转换为树结构
	 * 
	 * @param menuList
	 * @return
	 */
	private void convert(ResMenuBean menuBean, List<Menu> menuList) {
		Long pId = menuBean.getId();
		List<ResMenuBean> childs = null;
		Iterator<Menu> it = menuList.iterator();
		while (it.hasNext()) {
			Menu m = it.next();
			if (m.getParentId() == pId) {
				childs = menuBean.getChild();
				if (childs == null) {
					childs = new ArrayList<ResMenuBean>();
				}
				childs.add(new ResMenuBean(m.getMenuId(), m.getName(), m.getUrl()));
				it.remove();
				menuBean.setChild(childs);
			}
		}
		if (childs != null && !childs.isEmpty() && menuList != null && !menuList.isEmpty()) {
			Iterator<ResMenuBean> iterator2 = childs.iterator();
			while (iterator2.hasNext()) {
				ResMenuBean next = iterator2.next();
				convert(next, menuList);
			}
		}
	}
	
	/**
	 * 获取当前用户权限编码
	 * @return
	 */
	@PostMapping("/permcode")
	public ResultVO getPermCode() {
		User user = ApplicationUtil.getCurrentUser();
		List<Permission> permissions = permissionService.getPermissionByUserId(user.getUserId());
		List<String> list = new ArrayList<String>();
		for (Permission p : permissions) {
			list.add(p.getCode());
		}
		return new ResultVO(list);
	}
}
