package cn.mcsj.sso.entity;

import java.util.Date;

public class Company {

    private Long companyId;
    private String code;
    private String name;
    private Date createTime;

    public Long getCompanyId(){
      return companyId;
    }
    public void setCompanyId(Long companyId){
      this.companyId = companyId;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
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