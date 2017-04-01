package com.cqut.dao.raterAppointManager.customInterface;

import java.util.List;
import java.util.Map;

public interface IRaterAppointManagerMapDao {

	public List<Map<String, Object>> findRaterAppointManagers(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateRaterAppointManager(Map<String, Object> properties, String condition);
}
