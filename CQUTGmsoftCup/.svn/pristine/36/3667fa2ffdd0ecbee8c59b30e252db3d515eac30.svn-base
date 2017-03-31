package com.cqut.dao.standardVersion.customInterface;

import java.util.List;
import java.util.Map;

public interface IStandardVersionMapDao {

	public List<Map<String, Object>> findStandardVersions(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateStandardVersion(Map<String, Object> properties, String condition);
}
