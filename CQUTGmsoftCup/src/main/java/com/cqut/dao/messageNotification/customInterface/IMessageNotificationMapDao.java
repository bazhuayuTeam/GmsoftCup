package com.cqut.dao.messageNotification.customInterface;

import java.util.List;
import java.util.Map;

public interface IMessageNotificationMapDao {

	public List<Map<String, Object>> findMessageNotifications(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateMessageNotification(Map<String, Object> properties, String condition);
}
