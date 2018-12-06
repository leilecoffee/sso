package cn.mcsj.sso.dto.base;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BaseReqPageBean {

	// 当前页码
	@NotNull(message = "{page.pagenum.notNull}")
	@Min(value = 1, message = "{page.pagenum.min}")
	private int pageNum;
	// 每页记录数
	@NotNull(message = "{page.pagesize.notNull}")
	@Min(value = 10, message = "{page.pagesize.min}")
	private int pageSize = 10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
