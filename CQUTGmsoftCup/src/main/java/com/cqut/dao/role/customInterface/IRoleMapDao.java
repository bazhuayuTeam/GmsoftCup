package com.cqut.dao.role.customInterface;

import java.util.List;
import java.util.Map;

import com.cqut.entity.role.Role;

public interface IRoleMapDao {

	public List<Map<String, Object>> findRoles(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int update(Role role,String condition);
	
	public int updateRoleProperties(Map<String,Object> properties,String condition);
}
