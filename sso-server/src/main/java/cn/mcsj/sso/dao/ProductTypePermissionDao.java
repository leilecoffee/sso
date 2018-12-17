package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.ProductTypePermission;

@Mapper
@SuppressWarnings("rawtypes")
public interface ProductTypePermissionDao {
	
	ProductTypePermission getOne(Long id);

	List<ProductTypePermission> list(Map map);

	int total(Map map);

	int insert(ProductTypePermission entity);

	int update(ProductTypePermission entity);

	int delete(Long id);
}
