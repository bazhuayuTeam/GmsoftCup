package com.cqut.dao.operator.customInterface;

import java.util.List;
import java.util.Map;

public interface IOperatorMapDao {

	public List<Map<String, Object>> findOperators(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateOperator(Map<String, Object> properties, String condition);
}
