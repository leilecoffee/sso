package cn.mcsj.sso.dto.res;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ResMenuBean {
	@JSONField(serialize = false)
	private Long id;
	@JSONField(ordinal = 1)
	private String title;
	@JSONField(ordinal = 2)
	private String url;
	@JSONField(ordinal = 4)
	private List<ResMenuBean> child;

	public ResMenuBean() {

	}

	public ResMenuBean(Long id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResMenuBean> getChild() {
		return child;
	}

	public void setChild(List<ResMenuBean> child) {
		this.child = child;
	}
}
