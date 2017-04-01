package com.cqut.dao.comParam.customInterface;

import java.util.List;
import java.util.Map;

public interface IComParamMapDao {

	public List<Map<String, Object>> findComParams(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateComParam(Map<String, Object> properties, String condition);
}
