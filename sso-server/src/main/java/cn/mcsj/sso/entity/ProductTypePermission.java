package cn.mcsj.sso.entity;

import java.util.Date;

public class ProductTypePermission {

    private Long id;
    private Long productTypeId;
    private String permColumn;
    private Date createTime;

    public Long getId(){
      return id;
    }
    public void setId(Long id){
      this.id = id;
    }
    public Long getProductTypeId(){
      return productTypeId;
    }
    public void setProductTypeId(Long productTypeId){
      this.productTypeId = productTypeId;
    }
    public String getPermColumn(){
      return permColumn;
    }
    public void setPermColumn(String permColumn){
      this.permColumn = permColumn;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}