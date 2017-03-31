package com.cqut.service.messageReceive;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.messageReceive.customInterface.IMessageReceiveEntityDao;
import com.cqut.dao.messageReceive.customInterface.IMessageReceiveMapDao;
import com.cqut.entity.messageReceive.MessageReceive;

import com.cqut.service.messageReceive.customInterface.IMessageReceiveService;

@Controller  
@RemoteProxy
public class MessageReceiveService implements IMessageReceiveService {

	@Resource(name = "messageReceiveMapDao")
	private IMessageReceiveMapDao mapDao;
	@Resource(name = "messageReceiveEntityDao")
	private IMessageReceiveEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findMessageReceives(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessageReceives(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessageReceives(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getMessageReceive(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findMessageReceives(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public MessageReceive getMessageReceiveEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findMessageReceives(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new MessageReceive(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<MessageReceive> findMessageReceiveByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findMessageReceives(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(MessageReceive.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(MessageReceive value) {
		return deleteById(value.getMessageReceiveID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(MessageReceive.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(MessageReceive[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (MessageReceive item : values) {
			ids[index++] = item.getMessageReceiveID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new MessageReceive(value));
	}

	@RemoteMethod
	public boolean saveEntity(MessageReceive value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new MessageReceive(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(MessageReceive value) {
		MessageReceive messageReceive = (MessageReceive) commonDao.merge(value);
		if (messageReceive != null) {
			return messageReceive.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(MessageReceive data, String condition) {
		if(mapDao.updateMessageReceive(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveByReceiverAryAndOperator(String[] receiverAry, String messageId)
	{
		for (int i = 0; i < receiverAry.length; i++)
		{
			MessageReceive mr = new MessageReceive();
			mr.setMessageID(messageId);
			mr.setIsRead(1);
			mr.setOperatorID(receiverAry[i]);
			this.saveEntity(mr);
		}
		return true;
	}
}
