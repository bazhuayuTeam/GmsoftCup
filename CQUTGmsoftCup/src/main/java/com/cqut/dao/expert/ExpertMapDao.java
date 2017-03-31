package com.cqut.dao.expert;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expert.Expert;

import com.cqut.dao.expert.customInterface.IExpertMapDao;

@Component
public class ExpertMapDao extends BaseDao implements IExpertMapDao {

	
	public Class<?> getEntity() {
		return Expert.class;
	}

	public List<Map<String, Object>> findExperts(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateExpert(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}