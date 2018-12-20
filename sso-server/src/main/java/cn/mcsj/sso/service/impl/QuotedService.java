package cn.mcsj.sso.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.constant.GlobalConstant;
import cn.mcsj.sso.constant.ResultCode;
import cn.mcsj.sso.dao.CompanyDao;
import cn.mcsj.sso.dao.ProductDao;
import cn.mcsj.sso.dao.QuotedDao;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqQuotedSaveBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.Product;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IQuotedService;
import cn.mcsj.sso.util.ApplicationUtil;

@Service
public class QuotedService implements IQuotedService {

	@Autowired
	private QuotedDao quotedDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ProductDao productDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Quoted getOne(Long primaryKey) {
		return quotedDao.getOne(primaryKey);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Quoted> list(Map whereMap) {
		return quotedDao.list(whereMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean page(PageBean page, Map whereMap) {
		whereMap.put(GlobalConstant.PAGE_START, page.getStart());
		whereMap.put(GlobalConstant.PAGE_SIZE, page.getPageSize());
		int total = quotedDao.total(whereMap);
		List<Quoted> rows = new ArrayList<Quoted>();
		if (total > 0) {
			rows = list(whereMap);
		}
		page.setRows(rows);
		page.setTotal(total);
		return page;
	}

	/**
	 * 软删除
	 */
	@Override
	public ResultVO delete(Long primaryKey) {
		User user = ApplicationUtil.getCurrentUser();
		Quoted quoted = quotedDao.getOne(primaryKey);
		if (quoted.getCompanyId().equals(user.getCompanyId())) {
			Quoted entity = new Quoted();
			entity.setId(primaryKey);
			entity.setIsDelete(DeleteEnum.Y.getCode());
			quotedDao.update(entity);
			return new ResultVO();
		}
		return new ResultVO(ResultCode.PARAM_ERROR);
	}

	@Override
	public int save(ReqQuotedSaveBean quotedSaveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Quoted quoted = new Quoted();
		quoted.setCompanyId(user.getCompanyId());
		Company company = companyDao.getOne(user.getCompanyId());
		quoted.setCompanyCode(company.getCode());
		quoted.setCompanyName(company.getName());
		quoted.setProductId(quotedSaveBean.getProductId());
		Product product = productDao.getOne(quotedSaveBean.getProductId());
		quoted.setProductCode(product.getCode());
		quoted.setProductName(product.getName());
		quoted.setPrice(quotedSaveBean.getPrice());
		quoted.setPriceDate(quotedSaveBean.getPriceDate());
		quoted.setVisitStartTime(quotedSaveBean.getVisitStartTime());
		quoted.setVisitEndTime(quotedSaveBean.getVisitEndTime());
		return quotedDao.insert(quoted);
	}

}
