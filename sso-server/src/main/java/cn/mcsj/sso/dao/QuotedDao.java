package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.Quoted;

@Mapper
@SuppressWarnings("rawtypes")
public interface QuotedDao {
	
	Quoted getOne(Long id);

	List<Quoted> list(Map map);

	int total(Map map);

	int insert(Quoted entity);

	int update(Quoted entity);

	int delete(Long id);
}
