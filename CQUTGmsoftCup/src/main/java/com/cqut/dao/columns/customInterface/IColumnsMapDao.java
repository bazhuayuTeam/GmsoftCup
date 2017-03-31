package com.cqut.dao.columns.customInterface;

import java.util.List;
import java.util.Map;

public interface IColumnsMapDao {

	public List<Map<String, Object>> findColumnss(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateColumns(Map<String, Object> properties, String condition);
}
