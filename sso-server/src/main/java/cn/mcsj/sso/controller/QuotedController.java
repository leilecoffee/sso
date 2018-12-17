package cn.mcsj.sso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqQuotedSaveBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.IQuotedService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/quoted")
public class QuotedController {

	@Autowired
	private IQuotedService quotedService;

	@PostMapping("/save")
	public ResultVO save(@Valid @RequestBody ReqQuotedSaveBean quotedSaveBean) {
		User user = ApplicationUtil.getCurrentUser();
		Quoted quoted = new Quoted();
		quoted.setCompanyId(user.getCompanyId());
		quoted.setProductId(quotedSaveBean.getProductId());
		quoted.setPrice(quotedSaveBean.getPrice());
		quoted.setPriceDate(quotedSaveBean.getPriceDate());
		quotedService.save(quoted);
		return new ResultVO();
	}
	
}
