package com.cqut.service.message;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.message.customInterface.IMessageEntityDao;
import com.cqut.dao.message.customInterface.IMessageMapDao;
import com.cqut.entity.message.Message;
import com.cqut.service.message.customInterface.IMessageService;

@Controller  
@RemoteProxy
public class MessageService implements IMessageService {

	@Resource(name = "messageMapDao")
	private IMessageMapDao mapDao;
	@Resource(name = "messageEntityDao")
	private IMessageEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findMessages(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessages(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessages(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getMessage(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findMessages(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Message getMessageEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findMessages(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Message(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Message> findMessageByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findMessages(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Message.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Message value) {
		return deleteById(value.getMessageID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Message.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Message[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Message item : values) {
			ids[index++] = item.getMessageID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Message(value));
	}

	@RemoteMethod
	public boolean saveEntity(Message value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Message(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Message value) {
		Message message = (Message) commonDao.merge(value);
		if (message != null) {
			return message.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Message data, String condition) {
		if(mapDao.updateMessage(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean sendMessage(Message value){
		value.setSendTime(new Date());
		
		try {
			this.saveEntity(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
