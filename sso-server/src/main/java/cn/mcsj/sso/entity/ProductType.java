package cn.mcsj.sso.entity;

import java.util.Date;

public class ProductType {

    private Long productTypeId;
    private String name;
    private Date createTime;

    public Long getProductTypeId(){
      return productTypeId;
    }
    public void setProductTypeId(Long productTypeId){
      this.productTypeId = productTypeId;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}