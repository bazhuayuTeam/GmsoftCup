package com.cqut.dao.messageReceive.customInterface;

import java.util.List;
import java.util.Map;

public interface IMessageReceiveMapDao {

	public List<Map<String, Object>> findMessageReceives(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateMessageReceive(Map<String, Object> properties, String condition);
}
