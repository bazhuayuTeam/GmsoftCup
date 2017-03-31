package com.cqut.dao.message;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.message.Message;

import com.cqut.dao.message.customInterface.IMessageMapDao;

@Component
public class MessageMapDao extends BaseDao implements IMessageMapDao {

	
	public Class<?> getEntity() {
		return Message.class;
	}

	public List<Map<String, Object>> findMessages(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateMessage(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}