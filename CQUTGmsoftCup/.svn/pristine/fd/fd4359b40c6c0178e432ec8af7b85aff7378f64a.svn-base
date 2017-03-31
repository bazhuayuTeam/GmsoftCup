package com.cqut.dao.permissionAssign.customInterface;

import java.util.List;
import java.util.Map;

public interface IPermissionAssignMapDao {

	public List<Map<String, Object>> findPermissionAssigns(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updatePermissionAssign(Map<String, Object> properties, String condition);
}
