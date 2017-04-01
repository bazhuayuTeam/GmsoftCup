package com.cqut.dao.followManager.customInterface;

import java.util.List;
import java.util.Map;

public interface IFollowManagerMapDao {

	public List<Map<String, Object>> findFollowManagers(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateFollowManager(Map<String, Object> properties, String condition);
}
