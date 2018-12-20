package cn.mcsj.sso.dto.req;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ReqQuotedSaveBean {
	@NotNull(message="{product.notNull}")
	private Long productId;
	@NotNull(message="{product.price.notNull}")
	private Double price;
	@NotNull(message="{product.priceDate.notNull}")
	private Date priceDate;
	private Date visitStartTime;

	private Date visitEndTime;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPriceDate() {
		return priceDate;
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

}
