package com.cqut.dao.messageNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.messageNotification.MessageNotification;

import com.cqut.dao.messageNotification.customInterface.IMessageNotificationEntityDao;

@Component
public class MessageNotificationEntityDao extends BaseDao implements IMessageNotificationEntityDao {

	@Override
	public Class<?> getEntity() {
		return MessageNotification.class;
	}
	
	public List<MessageNotification> findMessageNotifications(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<MessageNotification> results = new ArrayList<MessageNotification>(list.size());
		for(Map<String, Object> item : list){
			results.add(new MessageNotification(item));
		}
		return results;
	}

}
