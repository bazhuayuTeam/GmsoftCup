package com.cqut.dao.messageNotification.customInterface;

import java.util.List;

import com.cqut.entity.messageNotification.MessageNotification;

public interface IMessageNotificationEntityDao {

	public List<MessageNotification> findMessageNotifications (String[] property,
			String condition, boolean needLink, int index, int limit);
}
