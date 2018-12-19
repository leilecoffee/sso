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

import cn.mcsj.sso.constant.DeleteEnum;
import cn.mcsj.sso.dto.base.PageBean;
import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqApplyAuthorizationSaveBean;
import cn.mcsj.sso.dto.req.ReqApplyPageQueryBean;
import cn.mcsj.sso.dto.req.ReqApplyRejectSaveBean;
import cn.mcsj.sso.dto.req.ReqApplySaveBean;
import cn.mcsj.sso.dto.res.ResApplyPageQueryBean;
import cn.mcsj.sso.entity.Apply;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IApplyService;
import cn.mcsj.sso.service.ICompanyService;
import cn.mcsj.sso.service.IUserService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/apply")
public class ApplyController {

	@Autowired
	private IApplyService applyService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IUserService userService;

	/**
	 * 授权申请 新增
	 * 
	 * @param saveBean
	 * @return
	 */
	@PostMapping("/save")
	public ResultVO save(@Valid @RequestBody ReqApplySaveBean saveBean) {
		applyService.save(saveBean);
		return new ResultVO();
	}

	/**
	 * 登录人授权申请查询
	 * 
	 * @param pageQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/query")
	public ResultVO query(@Valid @RequestBody ReqApplyPageQueryBean pageQueryBean) {
		User user = ApplicationUtil.getCurrentUser();
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("state", pageQueryBean.getState());
		whereMap.put("isDelete", DeleteEnum.N.getCode());
		whereMap.put("toCompanyId", user.getCompanyId());
		PageBean pageBean = new PageBean(pageQueryBean.getPageNum(), pageQueryBean.getPageSize());
		applyService.page(pageBean, whereMap);
		List<ResApplyPageQueryBean> data = new ArrayList<ResApplyPageQueryBean>();
		List<Apply> rows = (List<Apply>) pageBean.getRows();
		for (Apply apply : rows) {
			ResApplyPageQueryBean bean = new ResApplyPageQueryBean();
			bean.setApplyId(apply.getApplyId());
			User auser = userService.getOne(apply.getUserId());
			bean.setApplyPerson(auser.getName());
			bean.setApplyPhone(auser.getPhone());
			Company company = companyService.getOne(auser.getCompanyId());
			bean.setCompanyName(company.getName());
			bean.setCompanyCode(company.getCode());
			bean.setState(apply.getState());
			bean.setReason(apply.getReason());
			bean.setCreateTime(apply.getCreateTime());
			data.add(bean);
		}
		pageBean.setRows(data);
		return new ResultVO(pageBean);
	}

	/**
	 * 授权申请  授权操作
	 * 
	 * @param saveBean
	 * @return
	 */
	@PostMapping("/authorization")
	public ResultVO authorization(@Valid @RequestBody ReqApplyAuthorizationSaveBean saveBean) {
		return applyService.authorization(saveBean);
	}

	/**
	 * 申请驳回操作
	 * 
	 * @param saveBean
	 * @return
	 */
	@PostMapping("/reject")
	public ResultVO reject(@Valid @RequestBody ReqApplyRejectSaveBean saveBean) {
		return applyService.reject(saveBean);
	}
}
