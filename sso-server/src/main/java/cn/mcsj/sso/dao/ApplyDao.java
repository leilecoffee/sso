package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.Apply;

@Mapper
@SuppressWarnings("rawtypes")
public interface ApplyDao {
	
	Apply getOne(Long id);

	List<Apply> list(Map map);

	int total(Map map);

	int insert(Apply entity);

	int update(Apply entity);

	int delete(Long id);
}
