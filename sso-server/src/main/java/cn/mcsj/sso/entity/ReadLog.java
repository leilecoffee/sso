package cn.mcsj.sso.entity;

import java.util.Date;

public class ReadLog {

    private Long id;
    private Long userId;
    private Long infoPermId;
    private Long infoId;
    private Long publishCompanyId;
    private Long readCompanyId;
    private Date createTime;

    public Long getId(){
      return id;
    }
    public void setId(Long id){
      this.id = id;
    }
    public Long getUserId(){
      return userId;
    }
    public void setUserId(Long userId){
      this.userId = userId;
    }
    public Long getInfoPermId(){
      return infoPermId;
    }
    public void setInfoPermId(Long infoPermId){
      this.infoPermId = infoPermId;
    }
    public Long getInfoId(){
      return infoId;
    }
    public void setInfoId(Long infoId){
      this.infoId = infoId;
    }
    public Long getPublishCompanyId(){
      return publishCompanyId;
    }
    public void setPublishCompanyId(Long publishCompanyId){
      this.publishCompanyId = publishCompanyId;
    }
    public Long getReadCompanyId(){
      return readCompanyId;
    }
    public void setReadCompanyId(Long readCompanyId){
      this.readCompanyId = readCompanyId;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}