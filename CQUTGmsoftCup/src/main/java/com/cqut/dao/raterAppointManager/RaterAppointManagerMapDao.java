package com.cqut.dao.raterAppointManager;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.raterAppointManager.RaterAppointManager;

import com.cqut.dao.raterAppointManager.customInterface.IRaterAppointManagerMapDao;

@Component
public class RaterAppointManagerMapDao extends BaseDao implements IRaterAppointManagerMapDao {

	
	public Class<?> getEntity() {
		return RaterAppointManager.class;
	}

	public List<Map<String, Object>> findRaterAppointManagers(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateRaterAppointManager(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}