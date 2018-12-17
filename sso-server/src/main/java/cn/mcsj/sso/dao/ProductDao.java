package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.Product;

@Mapper
@SuppressWarnings("rawtypes")
public interface ProductDao {
	
	Product getOne(Long id);

	List<Product> list(Map map);

	int total(Map map);

	int insert(Product entity);

	int update(Product entity);

	int delete(Long id);
}
