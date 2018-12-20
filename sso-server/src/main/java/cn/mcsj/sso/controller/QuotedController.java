package cn.mcsj.sso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqQuotedPageQueryBean;
import cn.mcsj.sso.dto.req.ReqQuotedSaveBean;
import cn.mcsj.sso.dto.res.ResQuotedBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IQuotedService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/quoted")
public class QuotedController {

	@Autowired
	private IQuotedService quotedService;

	/**
	 * 新增报价信息
	 * 
	 * @param quotedSaveBean
	 * @return
	 */
	@PostMapping("/save")
	public ResultVO save(@Valid @RequestBody ReqQuotedSaveBean quotedSaveBean) {
		quotedService.save(quotedSaveBean);
		return new ResultVO();
	}

	/**
	 * 获取登录人发布的报价信息
	 * 
	 * @param pageQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/query")
	public ResultVO query(@Valid @RequestBody ReqQuotedPageQueryBean pageQueryBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("companyId", user.getCompanyId());
		whereMap.put("state", pageQueryBean.getState());
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		whereMap.put("orderByClause", "create_time DESC");
		PageBean pageBean = new PageBean(pageQueryBean.getPageNum(), pageQueryBean.getPageSize());
		quotedService.page(pageBean, whereMap);
		List<Quoted> rows = (List<Quoted>) pageBean.getRows();
		List<ResQuotedBean> data = new ArrayList<ResQuotedBean>();
		for (Quoted quoted : rows) {
			ResQuotedBean bean = new ResQuotedBean();
			BeanUtils.copyProperties(quoted, bean);
			bean.setCompanyName(quoted.getCompanyName());
			bean.setProductName(quoted.getProductName());
			data.add(bean);
		}
		pageBean.setRows(data);
		return new ResultVO(pageBean);
	}

	@PostMapping("/delete")
	public ResultVO delete(@RequestBody Map<String, Long> param) {
		Long id = param.get("id");
		return quotedService.delete(id);
	}
}
