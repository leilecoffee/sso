package cn.mcsj.sso.service;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoDetailBean;
import cn.mcsj.sso.dto.req.ReqInfoQueryBean;

public interface IInfoService {

	ResultVO readerQuery(ReqInfoQueryBean queryBean);
	
	ResultVO detail(ReqInfoDetailBean detailBean);
}
