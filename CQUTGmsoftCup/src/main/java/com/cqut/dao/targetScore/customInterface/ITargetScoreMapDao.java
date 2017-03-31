package com.cqut.dao.targetScore.customInterface;

import java.util.List;
import java.util.Map;

public interface ITargetScoreMapDao {

	public List<Map<String, Object>> findTargetScores(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateTargetScore(Map<String, Object> properties, String condition);
}
