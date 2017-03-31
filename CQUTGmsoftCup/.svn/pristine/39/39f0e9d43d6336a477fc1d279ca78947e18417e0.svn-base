package com.cqut.dao.roleAssign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.roleAssign.RoleAssign;

import com.cqut.dao.roleAssign.customInterface.IRoleAssignEntityDao;

@Component
public class RoleAssignEntityDao extends BaseDao implements IRoleAssignEntityDao {

	@Override
	public Class<?> getEntity() {
		return RoleAssign.class;
	}
	
	public List<RoleAssign> findRoleAssigns(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<RoleAssign> results = new ArrayList<RoleAssign>(list.size());
		for(Map<String, Object> item : list){
			results.add(new RoleAssign(item));
		}
		return results;
	}

}
