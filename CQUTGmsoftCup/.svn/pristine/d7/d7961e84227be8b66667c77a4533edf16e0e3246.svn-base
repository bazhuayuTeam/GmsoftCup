package com.cqut.dao.module.customInterface;

import java.util.List;
import java.util.Map;

public interface IModuleMapDao {

	public List<Map<String, Object>> findModules(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateModule(Map<String, Object> properties, String condition);
}
