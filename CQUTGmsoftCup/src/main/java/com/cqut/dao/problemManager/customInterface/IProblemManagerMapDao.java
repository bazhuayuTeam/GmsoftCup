package com.cqut.dao.problemManager.customInterface;

import java.util.List;
import java.util.Map;

public interface IProblemManagerMapDao {

	public List<Map<String, Object>> findProblemManagers(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateProblemManager(Map<String, Object> properties, String condition);
}
