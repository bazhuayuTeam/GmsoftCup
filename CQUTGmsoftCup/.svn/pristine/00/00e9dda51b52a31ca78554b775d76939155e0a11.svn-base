package com.cqut.dao.targetScore;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.targetScore.TargetScore;

import com.cqut.dao.targetScore.customInterface.ITargetScoreMapDao;

@Component
public class TargetScoreMapDao extends BaseDao implements ITargetScoreMapDao {

	
	public Class<?> getEntity() {
		return TargetScore.class;
	}

	public List<Map<String, Object>> findTargetScores(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateTargetScore(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}