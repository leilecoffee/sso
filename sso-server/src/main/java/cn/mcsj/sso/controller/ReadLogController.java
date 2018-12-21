package cn.mcsj.sso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqReadLogPageQueryBean;
import cn.mcsj.sso.dto.res.ResReadLogQueryBean;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.InfoPermission;
import cn.mcsj.sso.entity.ReadLog;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.ICompanyService;
import cn.mcsj.sso.service.IInfoPermissionService;
import cn.mcsj.sso.service.IReadLogService;
import cn.mcsj.sso.service.IUserService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/readlog")
public class ReadLogController {
	
	@Autowired
	private IReadLogService readLogService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IInfoPermissionService infoPermissionService;
	/**
	 * 发布者-阅读日志
	 * @param pageQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/publish")
	public ResultVO publisherQuery(@Valid @RequestBody ReqReadLogPageQueryBean pageQueryBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String,Object> whereMap = new HashMap<String,Object>();
		PageBean pageBean = new PageBean(pageQueryBean.getPageNum(), pageQueryBean.getPageSize());
		whereMap.put("publishCompanyId", user.getCompanyId());
		readLogService.page(pageBean, whereMap);
		List<ResReadLogQueryBean> data = new ArrayList<ResReadLogQueryBean>();
		List<ReadLog> rows = (List<ReadLog>) pageBean.getRows();
		for (ReadLog readLog : rows) {
			ResReadLogQueryBean bean = new ResReadLogQueryBean();
			Company company = companyService.getOne(readLog.getReadCompanyId());
			bean.setLogId(readLog.getId());
			bean.setReadCompanyName(company.getName());
			User auser = userService.getOne(readLog.getUserId());
			bean.setReader(auser.getName());
			bean.setReadTime(readLog.getCreateTime());
			InfoPermission infoPerm = infoPermissionService.getOne(readLog.getInfoPermId());
			bean.setInfoType(infoPerm.getName());
			bean.setInfoPermId(readLog.getInfoPermId());
			bean.setInfoId(readLog.getInfoId());
			data.add(bean);
		}
		pageBean.setRows(data);
		return new ResultVO(pageBean);
	}
	/**
	 * 阅读者-阅读日志
	 * @param pageQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/read")
	public ResultVO ReaderQuery(@Valid @RequestBody ReqReadLogPageQueryBean pageQueryBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String,Object> whereMap = new HashMap<String,Object>();
		PageBean pageBean = new PageBean(pageQueryBean.getPageNum(), pageQueryBean.getPageSize());
		whereMap.put("readCompanyId", user.getCompanyId());
		readLogService.page(pageBean, whereMap);
		List<ResReadLogQueryBean> data = new ArrayList<ResReadLogQueryBean>();
		List<ReadLog> rows = (List<ReadLog>) pageBean.getRows();
		for (ReadLog readLog : rows) {
			ResReadLogQueryBean bean = new ResReadLogQueryBean();
			Company company = companyService.getOne(readLog.getPublishCompanyId());
			bean.setLogId(readLog.getId());
			bean.setPublishCompanyName(company.getName());
			User auser = userService.getOne(readLog.getUserId());
			bean.setReader(auser.getName());
			bean.setReadTime(readLog.getCreateTime());
			InfoPermission infoPerm = infoPermissionService.getOne(readLog.getInfoPermId());
			bean.setInfoType(infoPerm.getName());
			bean.setInfoPermId(readLog.getInfoPermId());
			bean.setInfoId(readLog.getInfoId());
			data.add(bean);
		}
		pageBean.setRows(data);
		return new ResultVO(pageBean);
	}
}
