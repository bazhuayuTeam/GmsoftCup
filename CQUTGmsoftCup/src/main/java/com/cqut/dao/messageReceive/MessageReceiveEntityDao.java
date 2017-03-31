package com.cqut.dao.messageReceive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.messageReceive.MessageReceive;

import com.cqut.dao.messageReceive.customInterface.IMessageReceiveEntityDao;

@Component
public class MessageReceiveEntityDao extends BaseDao implements IMessageReceiveEntityDao {

	@Override
	public Class<?> getEntity() {
		return MessageReceive.class;
	}
	
	public List<MessageReceive> findMessageReceives(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<MessageReceive> results = new ArrayList<MessageReceive>(list.size());
		for(Map<String, Object> item : list){
			results.add(new MessageReceive(item));
		}
		return results;
	}

}
