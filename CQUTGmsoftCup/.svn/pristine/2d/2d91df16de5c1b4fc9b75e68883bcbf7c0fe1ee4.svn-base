package com.cqut.dao.message.customInterface;

import java.util.List;
import java.util.Map;

public interface IMessageMapDao {

	public List<Map<String, Object>> findMessages(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateMessage(Map<String, Object> properties, String condition);
}
