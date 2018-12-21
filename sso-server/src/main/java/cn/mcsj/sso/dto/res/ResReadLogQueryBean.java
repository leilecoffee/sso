package cn.mcsj.sso.dto.res;

import java.util.Date;

public class ResReadLogQueryBean {
	
	private Long logId;
	
	private String publishCompanyName;
	
	private String readCompanyName;
	
	private String infoType;
	
	private Long infoPermId;
	
	private Long infoId;
	
	private String reader;
	
	private String publisher;
	
	private Date readTime;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getPublishCompanyName() {
		return publishCompanyName;
	}

	public void setPublishCompanyName(String publishCompanyName) {
		this.publishCompanyName = publishCompanyName;
	}

	public String getReadCompanyName() {
		return readCompanyName;
	}

	public void setReadCompanyName(String readCompanyName) {
		this.readCompanyName = readCompanyName;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public Long getInfoPermId() {
		return infoPermId;
	}

	public void setInfoPermId(Long infoPermId) {
		this.infoPermId = infoPermId;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
}
