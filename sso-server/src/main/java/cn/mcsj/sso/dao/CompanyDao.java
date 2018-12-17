package cn.mcsj.sso.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.mcsj.sso.entity.Company;

@Mapper
@SuppressWarnings("rawtypes")
public interface CompanyDao {
	
	Company getOne(Long id);

	List<Company> list(Map map);

	int total(Map map);

	int insert(Company entity);

	int update(Company entity);

	int delete(Long id);
}
