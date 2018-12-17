package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.ReadLog;

@Mapper
@SuppressWarnings("rawtypes")
public interface ReadLogDao {
	
	ReadLog getOne(Long id);

	List<ReadLog> list(Map map);

	int total(Map map);

	int insert(ReadLog entity);

	int update(ReadLog entity);

	int delete(Long id);
}
