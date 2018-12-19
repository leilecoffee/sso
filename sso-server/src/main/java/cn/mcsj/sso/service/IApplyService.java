package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqApplyAuthorizationSaveBean;
import cn.mcsj.sso.dto.req.ReqApplyRejectSaveBean;
import cn.mcsj.sso.dto.req.ReqApplySaveBean;
import cn.mcsj.sso.entity.Apply;

public interface IApplyService{
	
	Apply getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Apply> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ReqApplySaveBean saveBean);

    int delete(Long primaryKey);
    
    ResultVO authorization(ReqApplyAuthorizationSaveBean saveBean);
    
    ResultVO reject(ReqApplyRejectSaveBean saveBean);
}
