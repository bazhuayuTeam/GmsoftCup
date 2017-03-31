package com.cqut.dao.expertScore;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expertScore.ExpertScore;

import com.cqut.dao.expertScore.customInterface.IExpertScoreMapDao;

@Component
public class ExpertScoreMapDao extends BaseDao implements IExpertScoreMapDao {

	
	public Class<?> getEntity() {
		return ExpertScore.class;
	}

	public List<Map<String, Object>> findExpertScores(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateExpertScore(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}