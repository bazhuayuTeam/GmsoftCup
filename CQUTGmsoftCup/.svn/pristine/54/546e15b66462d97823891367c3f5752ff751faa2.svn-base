package com.cqut.dao.discipline;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.discipline.Discipline;

import com.cqut.dao.discipline.customInterface.IDisciplineMapDao;

@Component
public class DisciplineMapDao extends BaseDao implements IDisciplineMapDao {

	
	public Class<?> getEntity() {
		return Discipline.class;
	}

	public List<Map<String, Object>> findDisciplines(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateDiscipline(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}