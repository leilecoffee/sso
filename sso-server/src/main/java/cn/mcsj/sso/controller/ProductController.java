package cn.mcsj.sso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.entity.Product;
import cn.mcsj.sso.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	/**
	 * 查询产品信息列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	public ResultVO list(@RequestBody Map<String, String> params) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(params.get("filter"))) {
			whereMap.put("likeName", params.get("filter"));
		}
		List<Product> list = productService.list(whereMap);
		return new ResultVO(list);
	}
}
