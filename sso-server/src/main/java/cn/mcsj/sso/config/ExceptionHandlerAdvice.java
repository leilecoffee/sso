package cn.mcsj.sso.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dto.base.ResultVO;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO methodArgumentNotValidException(MethodArgumentNotValidException  e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "校验失败:";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        return new ResultVO(ResultCode.PARAM_ERROR.getCode(),errorMesssage);
    }
	
	
	@ExceptionHandler(AuthorizationException.class)
    public ResultVO authorizationException(Exception e) {
        e.printStackTrace();
        return new ResultVO(ResultCode.UNAUTHORIZED);
    }
}
