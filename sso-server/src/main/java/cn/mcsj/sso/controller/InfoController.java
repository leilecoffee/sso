package cn.mcsj.sso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoDetailBean;
import cn.mcsj.sso.dto.req.ReqInfoQueryBean;
import cn.mcsj.sso.dto.res.ResInfoQueryBean;
import cn.mcsj.sso.entity.Quoted;
import cn.mcsj.sso.service.IInfoService;
import cn.mcsj.sso.util.DocumentHandlerUtil;

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
		return new ResultVO(infoService.readerQuery(queryBean));
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

	@SuppressWarnings("unchecked")
	@PostMapping("/export")
	public void exportQuotedInfo(@RequestBody ReqInfoQueryBean queryBean,HttpServletRequest request, HttpServletResponse response) {
		List<ResInfoQueryBean> data = infoService.readerQuery(queryBean);
		for(ResInfoQueryBean resInfoQueryBean:data) {
			if(resInfoQueryBean.getInfoType().equals(1L)) {
				List<Quoted> resultQuotedList  = (List<Quoted>)resInfoQueryBean.getData();
				Map<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("dataList", resultQuotedList);
				DocumentHandlerUtil.createFile(response, dataMap, "quoted.ftl", "123.xls", "/template");
			}
		}
		
	}
}
