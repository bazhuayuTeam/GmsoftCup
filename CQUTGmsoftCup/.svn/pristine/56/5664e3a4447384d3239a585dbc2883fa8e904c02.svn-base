package com.cqut.dao.roleAssign.customInterface;

import java.util.List;
import java.util.Map;

public interface IRoleAssignMapDao {

	public List<Map<String, Object>> findRoleAssigns(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateRoleAssign(Map<String, Object> properties, String condition);
}
