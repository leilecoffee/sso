package cn.mcsj.sso.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.entity.ProductType;
import cn.mcsj.sso.service.IProductTypeService;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {

	@Autowired
	private IProductTypeService productTypeService;

	@PostMapping("/get")
	public ResultVO get(@RequestBody Map<String, Long> params) {
		Long productTypeId = params.get("productTypeId");
		ProductType productType = productTypeService.getOne(productTypeId);
		return new ResultVO(productType);
	}
}
