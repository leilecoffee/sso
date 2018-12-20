package cn.mcsj.sso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoDetailBean;
import cn.mcsj.sso.dto.req.ReqInfoQueryBean;
import cn.mcsj.sso.service.IInfoService;

@RestController
@RequestMapping("/info")
public class InfoController {
	@Autowired
	private IInfoService infoService;

	/**
	 * 阅读者获取授权信息
	 * 
	 * @param queryBean
	 * @return
	 */
	@PostMapping("/query")
	public ResultVO query(@RequestBody ReqInfoQueryBean queryBean) {
		return infoService.readerQuery(queryBean);
	}

	/**
	 * 阅读者获取授权信息详情
	 * 
	 * @param detailBean
	 * @return
	 */
	@PostMapping("/detail")
	public ResultVO detail(@Valid @RequestBody ReqInfoDetailBean detailBean) {
		return infoService.detail(detailBean);
	}
}
