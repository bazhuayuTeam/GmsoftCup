package com.cqut.dao.discipline.customInterface;

import java.util.List;
import java.util.Map;

public interface IDisciplineMapDao {

	public List<Map<String, Object>> findDisciplines(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateDiscipline(Map<String, Object> properties, String condition);
}
