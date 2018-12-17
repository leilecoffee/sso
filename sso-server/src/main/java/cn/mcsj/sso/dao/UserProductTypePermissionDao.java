package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.UserProductTypePermission;

@Mapper
@SuppressWarnings("rawtypes")
public interface UserProductTypePermissionDao {
	
	UserProductTypePermission getOne(Long id);

	List<UserProductTypePermission> list(Map map);

	int total(Map map);

	int insert(UserProductTypePermission entity);

	int update(UserProductTypePermission entity);

	int delete(Long id);
}
