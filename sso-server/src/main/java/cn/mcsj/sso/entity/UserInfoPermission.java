package cn.mcsj.sso.entity;

import java.util.Date;

public class UserInfoPermission {

	private Long id;
	private Long userId;
	private Long fromCompanyId;
	private Long toCompanyId;
	private Long infoPermId;
	private String permColumn;
	private String permColumnDesc;
	private Integer isDelete;
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFromCompanyId() {
		return fromCompanyId;
	}

	public void setFromCompanyId(Long fromCompanyId) {
		this.fromCompanyId = fromCompanyId;
	}

	public Long getToCompanyId() {
		return toCompanyId;
	}

	public void setToCompanyId(Long toCompanyId) {
		this.toCompanyId = toCompanyId;
	}

	public Long getInfoPermId() {
		return infoPermId;
	}

	public void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}

	public String getPermColumn() {
		return permColumn;
	}

	public void setPermColumn(String permColumn) {
		this.permColumn = permColumn;
	}

	public String getPermColumnDesc() {
		return permColumnDesc;
	}

	public void setPermColumnDesc(String permColumnDesc) {
		this.permColumnDesc = permColumnDesc;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}