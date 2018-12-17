package cn.mcsj.sso.entity;

import java.util.Date;

public class ReadLog {

    private Long id;
    private Long userId;
    private Long productTypeId;
    private Long productInfoId;
    private Long fromCompanyId;
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
    public Long getProductTypeId(){
      return productTypeId;
    }
    public void setProductTypeId(Long productTypeId){
      this.productTypeId = productTypeId;
    }
    public Long getProductInfoId(){
      return productInfoId;
    }
    public void setProductInfoId(Long productInfoId){
      this.productInfoId = productInfoId;
    }
    public Long getFromCompanyId(){
      return fromCompanyId;
    }
    public void setFromCompanyId(Long fromCompanyId){
      this.fromCompanyId = fromCompanyId;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}