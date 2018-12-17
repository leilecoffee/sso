package cn.mcsj.sso.entity;

public class UserProductTypePermission {

    private Long id;
    private Long userId;
    private Long fromCompanyId;
    private Long toCompanyId;
    private Long productTypeId;
    private String permColumn;

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
    public Long getFromCompanyId(){
      return fromCompanyId;
    }
    public void setFromCompanyId(Long fromCompanyId){
      this.fromCompanyId = fromCompanyId;
    }
    public Long getToCompanyId(){
      return toCompanyId;
    }
    public void setToCompanyId(Long toCompanyId){
      this.toCompanyId = toCompanyId;
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
}