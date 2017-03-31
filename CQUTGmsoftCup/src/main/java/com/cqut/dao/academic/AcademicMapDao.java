package com.cqut.dao.academic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.academic.Academic;

import com.cqut.dao.academic.customInterface.IAcademicMapDao;

@Component
public class AcademicMapDao extends BaseDao implements IAcademicMapDao {

	
	public Class<?> getEntity() {
		return Academic.class;
	}

	public List<Map<String, Object>> findAcademics(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateAcademic(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}