package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dao.CompanyDao;
import cn.mcsj.sso.dao.UserDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqChangePwdBean;
import cn.mcsj.sso.dto.req.ReqLoginBean;
import cn.mcsj.sso.dto.req.ReqUserSaveBean;
import cn.mcsj.sso.dto.req.ReqUserUpdateBean;
import cn.mcsj.sso.dto.res.ResLoginBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IUserService;
import cn.mcsj.sso.util.ApplicationUtil;
import cn.mcsj.sso.util.SerializeUtil;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private SessionDAO sessionDAO;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User getUserByUsername(String username) {
		Map whereMap = new HashMap();
		whereMap.put("username", username);
		List<User> userList = userDao.list(whereMap);
		if (userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public ResultVO login(ReqLoginBean loginBean) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String email = loginBean.getUsername();
			String password = loginBean.getPassword();
			if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
				return new ResultVO(ResultCode.PARAM_ERROR);
			}
			Session session = subject.getSession();
			ResLoginBean userInfo = new ResLoginBean();
			// 未认证
			if (!subject.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(email, password);
				subject.login(token);
				// 存放用户信息到session
				User user = getUserByUsername(email);
				// 剔除其他此账号在其它地方登录
				List<Session> loginedList = getLoginedSession(user.getUserId());
				for (Session loginedSession : loginedList) {
					sessionDAO.delete(loginedSession);
				}
				logger.info("写入当前用户到session中!{}", JSON.toJSONString(user));
				session.setAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USER, SerializeUtil.serialize(user));
				userInfo.setName(user.getName());
				Long companyId = user.getCompanyId();
				Company company = companyDao.getOne(companyId);
				userInfo.setCompanyName(company.getName());
			} else {
				User user = ApplicationUtil.getCurrentUser();
				userInfo.setName(user.getName());
				Long companyId = user.getCompanyId();
				Company company = companyDao.getOne(companyId);
				userInfo.setCompanyName(company.getName());
			}
			logger.info("登录成功,返回用户信息!{}", JSON.toJSONString(userInfo));
			return new ResultVO(userInfo);
		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			return new ResultVO(ResultCode.LOGIN_FAIL);
		}
	}

	// 遍历同一个账户的session
	private List<Session> getLoginedSession(Long userId) {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<Session> loginedList = new ArrayList<Session>();
		for (Session session : sessions) {
			Object obj = session.getAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USER);
			User user = (User) SerializeUtil.unserialize(((byte[]) obj));
			if (user != null) {
				if (userId.equals(user.getUserId())) {
					sessionDAO.delete(session);
					loginedList.add(session);
				}
			}
		}
		return loginedList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = userDao.total(whereMap);
		List<User> rows = new ArrayList<User>();
		if (total > 0) {
			rows = userDao.list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	@Override
	public ResultVO insert(ReqUserSaveBean userSaveBean) {
		String username = userSaveBean.getUsername();
		User user = getUserByUsername(username);
		if (user == null) {
			return new ResultVO(ResultCode.USERNAME_EXIST);
		}
		User auser = new User();
		BeanUtils.copyProperties(userSaveBean, auser);
		auser.setSalt((UUID.randomUUID().toString()).replaceAll("-", ""));
		SimpleHash hash = new SimpleHash(GlobalConstant.HASH_ALGORITHM_NAME, auser.getPassword(),
				user.getCredentialsSalt(), GlobalConstant.HASH_ITERATIONS);
		auser.setPassword(hash.toHex());
		auser.setIsDelete(DeleteEnum.N.getCode());
		auser.setCreateTime(new Date());
		userDao.insert(auser);
		return new ResultVO();
	}

	@Override
	public int delete(Long id) {
		User user = userDao.getOne(id);
		user.setIsDelete(DeleteEnum.Y.getCode());
		return userDao.update(user);
	}

	@Override
	public int update(ReqUserUpdateBean userUpdateBean) {
		User user = userDao.getOne(userUpdateBean.getUserId());
		user.setName(userUpdateBean.getName());
		user.setPhone(userUpdateBean.getPhone());
		user.setIsDelete(userUpdateBean.getIsDelete());
		return userDao.update(user);
	}

	@Override
	public User getOne(Long userId) {
		return userDao.getOne(userId);
	}

	@Override
	public ResultVO changePwd(ReqChangePwdBean changePwdBean) {
		User user = ApplicationUtil.getCurrentUser();
		User auser = getOne(user.getUserId());
		SimpleHash hash = new SimpleHash(GlobalConstant.HASH_ALGORITHM_NAME, changePwdBean.getOldPwd(),
				auser.getCredentialsSalt(), GlobalConstant.HASH_ITERATIONS);
		if (!auser.getPassword().equals(hash.toHex())) {
			return new ResultVO(ResultCode.PASSWORD_ERROR);
		}
		auser.setSalt((UUID.randomUUID().toString()).replaceAll("-", ""));
		SimpleHash newHash = new SimpleHash(GlobalConstant.HASH_ALGORITHM_NAME, changePwdBean.getNewPwd(),
				auser.getCredentialsSalt(), GlobalConstant.HASH_ITERATIONS);
		auser.setPassword(newHash.toHex());
		userDao.update(auser);
		return new ResultVO();
	}
}
