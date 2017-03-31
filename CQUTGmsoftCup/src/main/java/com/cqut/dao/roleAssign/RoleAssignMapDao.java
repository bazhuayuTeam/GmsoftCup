package com.cqut.dao.roleAssign;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.roleAssign.RoleAssign;

import com.cqut.dao.roleAssign.customInterface.IRoleAssignMapDao;

@Component
public class RoleAssignMapDao extends BaseDao implements IRoleAssignMapDao {

	
	public Class<?> getEntity() {
		return RoleAssign.class;
	}

	public List<Map<String, Object>> findRoleAssigns(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateRoleAssign(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}