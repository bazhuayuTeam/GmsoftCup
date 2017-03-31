package com.cqut.dao.crew;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.crew.Crew;

import com.cqut.dao.crew.customInterface.ICrewMapDao;

@Component
public class CrewMapDao extends BaseDao implements ICrewMapDao {

	
	public Class<?> getEntity() {
		return Crew.class;
	}

	public List<Map<String, Object>> findCrews(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateCrew(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}