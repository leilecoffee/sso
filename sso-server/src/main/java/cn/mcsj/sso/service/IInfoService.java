package cn.mcsj.sso.service;

import java.util.List;

import cn.mcsj.sso.dto.base.ResultVO;
import cn.mcsj.sso.dto.req.ReqInfoDetailBean;
import cn.mcsj.sso.dto.req.ReqInfoQueryBean;
import cn.mcsj.sso.dto.res.ResInfoQueryBean;

public interface IInfoService {

	List<ResInfoQueryBean> readerQuery(ReqInfoQueryBean queryBean);
	
	ResultVO detail(ReqInfoDetailBean detailBean);
}
