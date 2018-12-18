package cn.mcsj.sso.dto.res;

import java.util.Date;

public class ResQuotedBean {

	private Long id;
	private String companyName;
	private String productName;
	private Double price;
	private Date priceDate;
	private Date visitStartTime;
	private Date visitEndTime;
	private Integer state;
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceDate() {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.priceDate);
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public Date getVisitStartTime() {
		return visitStartTime;
	}

	public void setVisitStartTime(Date visitStartTime) {
		this.visitStartTime = visitStartTime;
	}

	public Date getVisitEndTime() {
		return visitEndTime;
	}

	public void setVisitEndTime(Date visitEndTime) {
		this.visitEndTime = visitEndTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreateTime() {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
