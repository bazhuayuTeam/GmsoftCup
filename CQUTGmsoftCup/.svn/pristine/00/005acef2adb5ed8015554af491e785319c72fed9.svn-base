package com.cqut.dao.role;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.role.Role;
import com.cqut.dao.role.customInterface.IRoleMapDao;

@Component
public class RoleMapDao extends BaseDao implements IRoleMapDao {

	
	public Class<?> getEntity() {
		return Role.class;
	}

	public List<Map<String, Object>> findRoles(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateRole(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

	@Override
	public int update(Role role, String condition) {
		return updateProperties(role.getProperties(), condition);
	}

	@Override
	public int updateRoleProperties(Map<String, Object> properties,
			String condition) {
		return this.updateProperties(properties, condition);
	}

}