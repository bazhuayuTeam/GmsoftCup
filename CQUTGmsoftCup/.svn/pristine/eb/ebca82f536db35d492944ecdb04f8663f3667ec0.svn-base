package com.cqut.dao.permissionAssign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.permissionAssign.PermissionAssign;

import com.cqut.dao.permissionAssign.customInterface.IPermissionAssignEntityDao;

@Component
public class PermissionAssignEntityDao extends BaseDao implements IPermissionAssignEntityDao {

	@Override
	public Class<?> getEntity() {
		return PermissionAssign.class;
	}
	
	public List<PermissionAssign> findPermissionAssigns(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<PermissionAssign> results = new ArrayList<PermissionAssign>(list.size());
		for(Map<String, Object> item : list){
			results.add(new PermissionAssign(item));
		}
		return results;
	}

}
