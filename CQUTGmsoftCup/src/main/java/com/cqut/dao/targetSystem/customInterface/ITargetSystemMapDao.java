package com.cqut.dao.targetSystem.customInterface;

import java.util.List;
import java.util.Map;

public interface ITargetSystemMapDao {

	public List<Map<String, Object>> findTargetSystems(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateTargetSystem(Map<String, Object> properties, String condition);
}
