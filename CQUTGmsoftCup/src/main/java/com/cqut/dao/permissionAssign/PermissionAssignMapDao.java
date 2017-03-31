package com.cqut.dao.permissionAssign;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.permissionAssign.PermissionAssign;

import com.cqut.dao.permissionAssign.customInterface.IPermissionAssignMapDao;

@Component
public class PermissionAssignMapDao extends BaseDao implements IPermissionAssignMapDao {

	
	public Class<?> getEntity() {
		return PermissionAssign.class;
	}

	public List<Map<String, Object>> findPermissionAssigns(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updatePermissionAssign(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}