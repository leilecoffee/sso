package cn.mcsj.sso.entity;

import java.util.Date;

public class InfoPermission {

    private Long infoPermId;
    private String name;
    private String code;
    private String permColumn;
    private String permDesc;
    private Date createTime;

    public Long getInfoPermId(){
      return infoPermId;
    }
    public void setInfoPermId(Long infoPermId){
      this.infoPermId = infoPermId;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getPermColumn(){
      return permColumn;
    }
    public void setPermColumn(String permColumn){
      this.permColumn = permColumn;
    }
    public String getPermDesc(){
      return permDesc;
    }
    public void setPermDesc(String permDesc){
      this.permDesc = permDesc;
    }
    public Date getCreateTime(){
      return createTime;
    }
    public void setCreateTime(Date createTime){
      this.createTime = createTime;
    }
}