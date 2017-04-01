package com.cqut.dao.messageNotification;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.messageNotification.MessageNotification;

import com.cqut.dao.messageNotification.customInterface.IMessageNotificationMapDao;

@Component
public class MessageNotificationMapDao extends BaseDao implements IMessageNotificationMapDao {

	
	public Class<?> getEntity() {
		return MessageNotification.class;
	}

	public List<Map<String, Object>> findMessageNotifications(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateMessageNotification(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}