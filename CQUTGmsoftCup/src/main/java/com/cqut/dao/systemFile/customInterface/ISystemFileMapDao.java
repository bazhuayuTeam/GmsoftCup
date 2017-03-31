package com.cqut.dao.systemFile.customInterface;

import java.util.List;
import java.util.Map;

public interface ISystemFileMapDao {

	public List<Map<String, Object>> findSystemFiles(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateSystemFile(Map<String, Object> properties, String condition);
}
