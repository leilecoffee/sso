package cn.mcsj.sso.service;

import java.util.List;
import java.util.Map;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.req.ReqCompanyPageQueryBean;
import cn.mcsj.sso.entity.Company;

public interface ICompanyService{
	
	Company getOne(Long primaryKey);

	@SuppressWarnings("rawtypes")
	List<Company> list(Map whereMap);
	
	@SuppressWarnings("rawtypes")
	PageBean page(PageBean page, Map whereMap);

	int save(Company company);

    int delete(Long primaryKey);
    
    PageBean getPublishCompany(ReqCompanyPageQueryBean pageQueryBean);
    
    PageBean getReaderCompany(ReqCompanyPageQueryBean pageQueryBean);
}
