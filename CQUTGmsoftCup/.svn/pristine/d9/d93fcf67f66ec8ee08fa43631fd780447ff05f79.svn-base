package com.cqut.dao.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.message.Message;

import com.cqut.dao.message.customInterface.IMessageEntityDao;

@Component
public class MessageEntityDao extends BaseDao implements IMessageEntityDao {

	@Override
	public Class<?> getEntity() {
		return Message.class;
	}
	
	public List<Message> findMessages(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Message> results = new ArrayList<Message>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Message(item));
		}
		return results;
	}

}
