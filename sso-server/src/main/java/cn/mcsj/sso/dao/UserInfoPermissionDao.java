package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.UserInfoPermission;

@Mapper
@SuppressWarnings("rawtypes")
public interface UserInfoPermissionDao {
	
	UserInfoPermission getOne(Long id);

	List<UserInfoPermission> list(Map map);

	int total(Map map);

	int insert(UserInfoPermission entity);

	int update(UserInfoPermission entity);

	int delete(Long id);
}
