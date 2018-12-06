package cn.mcsj.sso.config.support.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSONObject;

import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dto.base.ResultVO;


/**
 * 未认证拦截器
 * @author admin
 *
 */
public class UnAuthFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setContentType("application/json");
			httpServletResponse.getWriter().write(JSONObject.toJSONString(new ResultVO(ResultCode.UNLOGIN)));
			return false;
		}
		return true;
	}

}
