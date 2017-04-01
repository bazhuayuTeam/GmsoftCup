package com.cqut.dao.hostUnit.customInterface;

import java.util.List;
import java.util.Map;

public interface IHostUnitMapDao {

	public List<Map<String, Object>> findHostUnits(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateHostUnit(Map<String, Object> properties, String condition);
}
