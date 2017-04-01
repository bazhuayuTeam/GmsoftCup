package com.cqut.dao.hostUnit;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.hostUnit.HostUnit;

import com.cqut.dao.hostUnit.customInterface.IHostUnitMapDao;

@Component
public class HostUnitMapDao extends BaseDao implements IHostUnitMapDao {

	
	public Class<?> getEntity() {
		return HostUnit.class;
	}

	public List<Map<String, Object>> findHostUnits(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateHostUnit(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}