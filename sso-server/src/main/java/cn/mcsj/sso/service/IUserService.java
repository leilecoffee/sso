package cn.mcsj.sso.service;

import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqLoginBean;
import cn.mcsj.sso.dto.req.ReqUserSaveBean;
import cn.mcsj.sso.dto.req.ReqUserUpdateBean;
import cn.mcsj.sso.entity.User;

public interface IUserService {

	User getUserByUsername(String username);

	ResultVO login(ReqLoginBean loginBean);

	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	ResultVO insert(ReqUserSaveBean userSaveBean);
	
	ResultVO update(ReqUserUpdateBean userUpdateBean);

	int delete(Long id);
}
