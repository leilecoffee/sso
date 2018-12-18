package cn.mcsj.sso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.entity.InfoPermission;
import cn.mcsj.sso.service.IInfoPermissionService;

@RestController
@RequestMapping("/infoPerm")
public class InfoPermController {

	@Autowired
	private IInfoPermissionService infoPermissionService;
	
	@PostMapping("/list")
	public ResultVO list() {
		Map whereMap = new HashMap();
		List<InfoPermission> list = infoPermissionService.list(whereMap);
		return new ResultVO(list);
	}
}
