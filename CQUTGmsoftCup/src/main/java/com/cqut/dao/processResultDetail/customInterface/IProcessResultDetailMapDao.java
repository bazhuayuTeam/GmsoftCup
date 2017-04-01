package com.cqut.dao.processResultDetail.customInterface;

import java.util.List;
import java.util.Map;

public interface IProcessResultDetailMapDao {

	public List<Map<String, Object>> findProcessResultDetails(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateProcessResultDetail(Map<String, Object> properties, String condition);
}
