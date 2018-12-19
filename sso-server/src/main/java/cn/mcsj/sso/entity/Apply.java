package cn.mcsj.sso.entity;

import java.util.Date;

public class Apply {

    private Long applyId;
    private Long userId;
    private Long toCompanyId;
    private Integer state;
    private String reason;
    private Integer isDelete;
    private Date createTime;

    public Long getApplyId(){
      return applyId;
    }
    public void setApplyId(Long applyId){
      this.applyId = applyId;
    }
    public Long getUserId(){
      return userId;
    }
    public void setUserId(Long userId){
      this.userId = userId;
    }
    public Long getToCompanyId(){
      return toCompanyId;
    }
    public void setToCompanyId(Long toCompanyId){
      this.toCompanyId = toCompanyId;
    }
    public Integer getState(){
      return state;
    }
    public void setState(Integer state){
      this.state = state;
    }
    public String getReason(){
      return reason;
    }
    public void setReason(String reason){
      this.reason = reason;
    }
    public Integer getIsDelete(){
      return isDelete;
    }
    public void setIsDelete(Integer isDelete){
      this.isDelete = isDelete;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}