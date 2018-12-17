package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.ProductType;

@Mapper
@SuppressWarnings("rawtypes")
public interface ProductTypeDao {
	
	ProductType getOne(Long id);

	List<ProductType> list(Map map);

	int total(Map map);

	int insert(ProductType entity);

	int update(ProductType entity);

	int delete(Long id);
}
