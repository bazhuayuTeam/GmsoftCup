package com.cqut.dao.target;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.target.Target;

import com.cqut.dao.target.customInterface.ITargetMapDao;

@Component
public class TargetMapDao extends BaseDao implements ITargetMapDao {

	
	public Class<?> getEntity() {
		return Target.class;
	}

	public List<Map<String, Object>> findTargets(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateTarget(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}