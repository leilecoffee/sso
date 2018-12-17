package cn.mcsj.sso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.entity.Company;
import cn.mcsj.sso.entity.User;
import cn.mcsj.sso.service.ICompanyService;
import cn.mcsj.sso.util.ApplicationUtil;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;

	@PostMapping("/list")
	public ResultVO list() {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		List<Company> list = companyService.list(whereMap);
		return new ResultVO(list);
	}
	/**
	 * 获取登录用户的公司信息
	 * @return
	 */
	@PostMapping("/user/get")
	public ResultVO getCompany() {
		User user = ApplicationUtil.getCurrentUser();
		if (user.getCompanyId() != null) {
			Company company = companyService.getOne(user.getCompanyId());
			return new ResultVO(company);
		}
		return new ResultVO();
	}
}
