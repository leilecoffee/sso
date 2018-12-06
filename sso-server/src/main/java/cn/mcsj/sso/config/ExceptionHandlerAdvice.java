package cn.mcsj.sso.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dto.base.ResultVO;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO methodArgumentNotValidException(Exception e) {
        e.printStackTrace();
        return new ResultVO(ResultCode.PARAM_ERROR);
    }
	
	
	@ExceptionHandler(AuthorizationException.class)
    public ResultVO authorizationException(Exception e) {
        e.printStackTrace();
        return new ResultVO(ResultCode.UNAUTHORIZED);
    }
}
