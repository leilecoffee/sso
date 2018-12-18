package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.InfoPermission;

@Mapper
@SuppressWarnings("rawtypes")
public interface InfoPermissionDao {
	
	InfoPermission getOne(Long id);

	List<InfoPermission> list(Map map);

	int total(Map map);

	int insert(InfoPermission entity);

	int update(InfoPermission entity);

	int delete(Long id);
}
