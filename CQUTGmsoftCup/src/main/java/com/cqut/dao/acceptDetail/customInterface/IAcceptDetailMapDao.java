package com.cqut.dao.acceptDetail.customInterface;

import java.util.List;
import java.util.Map;

public interface IAcceptDetailMapDao {

	public List<Map<String, Object>> findAcceptDetails(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateAcceptDetail(Map<String, Object> properties, String condition);
}
