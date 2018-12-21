package cn.mcsj.sso.dto.res;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ResMenuBean {
	@JSONField(serialize = false)
	private Long id;
	@JSONField(ordinal = 1)
	private String title;
	@JSONField(ordinal = 2)
	private String path;
	@JSONField(ordinal = 2)
	private String icon;
	@JSONField(ordinal = 4)
	private List<ResMenuBean> child;

	public ResMenuBean() {

	}

	public ResMenuBean(Long id, String title,String icon, String path) {
		this.id = id;
		this.title = title;
		this.icon = icon;
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<ResMenuBean> getChild() {
		return child;
	}

	public void setChild(List<ResMenuBean> child) {
		this.child = child;
	}
}
