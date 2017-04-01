package com.cqut.dao.problemManager;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.problemManager.ProblemManager;

import com.cqut.dao.problemManager.customInterface.IProblemManagerMapDao;

@Component
public class ProblemManagerMapDao extends BaseDao implements IProblemManagerMapDao {

	
	public Class<?> getEntity() {
		return ProblemManager.class;
	}

	public List<Map<String, Object>> findProblemManagers(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateProblemManager(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}