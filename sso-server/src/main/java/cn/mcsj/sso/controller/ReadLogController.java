package cn.mcsj.sso.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;

@RestController
@RequestMapping("/readlog")
public class ReadLogController {

	
	@PostMapping("/publish")
	public ResultVO publisherQuery() {
		return new ResultVO();
	}
	
	@PostMapping("/read")
	public ResultVO ReaderQuery() {
		return new ResultVO();
	}
}
