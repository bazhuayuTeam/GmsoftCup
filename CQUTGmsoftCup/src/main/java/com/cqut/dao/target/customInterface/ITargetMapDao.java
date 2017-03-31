package com.cqut.dao.target.customInterface;

import java.util.List;
import java.util.Map;

public interface ITargetMapDao {

	public List<Map<String, Object>> findTargets(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateTarget(Map<String, Object> properties, String condition);
}
