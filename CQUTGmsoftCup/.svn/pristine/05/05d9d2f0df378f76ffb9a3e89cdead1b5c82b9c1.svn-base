package com.cqut.dao.crew.customInterface;

import java.util.List;
import java.util.Map;

public interface ICrewMapDao {

	public List<Map<String, Object>> findCrews(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateCrew(Map<String, Object> properties, String condition);
}
