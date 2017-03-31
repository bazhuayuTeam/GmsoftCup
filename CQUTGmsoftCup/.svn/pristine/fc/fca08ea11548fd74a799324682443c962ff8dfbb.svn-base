package com.cqut.dao.messageReceive;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.messageReceive.MessageReceive;

import com.cqut.dao.messageReceive.customInterface.IMessageReceiveMapDao;

@Component
public class MessageReceiveMapDao extends BaseDao implements IMessageReceiveMapDao {

	
	public Class<?> getEntity() {
		return MessageReceive.class;
	}

	public List<Map<String, Object>> findMessageReceives(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateMessageReceive(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}