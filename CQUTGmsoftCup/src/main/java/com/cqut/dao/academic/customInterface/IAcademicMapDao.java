package com.cqut.dao.academic.customInterface;

import java.util.List;
import java.util.Map;

public interface IAcademicMapDao {

	public List<Map<String, Object>> findAcademics(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateAcademic(Map<String, Object> properties, String condition);
}
