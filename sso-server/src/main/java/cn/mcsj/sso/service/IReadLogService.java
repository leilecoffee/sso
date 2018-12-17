package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.entity.ReadLog;

public interface IReadLogService{
	
	ReadLog getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<ReadLog> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(ReadLog readLog);

    int delete(Long primaryKey);
}
