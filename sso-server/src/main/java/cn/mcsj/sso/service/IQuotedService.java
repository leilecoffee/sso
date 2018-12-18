package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqQuotedSaveBean;
import cn.mcsj.sso.entity.Quoted;

public interface IQuotedService {

	Quoted getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Quoted> list(Map whereMap);

	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ReqQuotedSaveBean quotedSaveBean);

	ResultVO delete(Long primaryKey);
}
