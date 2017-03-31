package com.cqut.dao.targetSystem;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.targetSystem.Standardversion;

import com.cqut.dao.targetSystem.customInterface.ITargetSystemMapDao;

@Component
public class TargetSystemMapDao extends BaseDao implements ITargetSystemMapDao {

	
	public Class<?> getEntity() {
		return Standardversion.class;
	}

	public List<Map<String, Object>> findTargetSystems(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateTargetSystem(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}