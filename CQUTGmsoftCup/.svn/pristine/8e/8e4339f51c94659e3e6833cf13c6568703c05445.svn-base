package com.cqut.dao.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.role.Role;

import com.cqut.dao.role.customInterface.IRoleEntityDao;

@Component
public class RoleEntityDao extends BaseDao implements IRoleEntityDao {

	@Override
	public Class<?> getEntity() {
		return Role.class;
	}
	
	public List<Role> findRoles(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Role> results = new ArrayList<Role>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Role(item));
		}
		return results;
	}

}
