package com.cqut.service.messageNotification;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.messageNotification.customInterface.IMessageNotificationEntityDao;
import com.cqut.dao.messageNotification.customInterface.IMessageNotificationMapDao;
import com.cqut.entity.messageNotification.MessageNotification;

import com.cqut.service.messageNotification.customInterface.IMessageNotificationService;

@Controller  
@RemoteProxy
public class MessageNotificationService implements IMessageNotificationService {

	@Resource(name = "messageNotificationMapDao")
	private IMessageNotificationMapDao mapDao;
	@Resource(name = "messageNotificationEntityDao")
	private IMessageNotificationEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findMessageNotifications(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessageNotifications(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findMessageNotifications(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getMessageNotification(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findMessageNotifications(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public MessageNotification getMessageNotificationEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findMessageNotifications(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new MessageNotification(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<MessageNotification> findMessageNotificationByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findMessageNotifications(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(MessageNotification.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(MessageNotification value) {
		return deleteById(value.getMessageId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(MessageNotification.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(MessageNotification[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (MessageNotification item : values) {
			ids[index++] = item.getMessageId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new MessageNotification(value));
	}

	@RemoteMethod
	public boolean saveEntity(MessageNotification value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new MessageNotification(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(MessageNotification value) {
		MessageNotification messageNotification = (MessageNotification) commonDao.merge(value);
		if (messageNotification != null) {
			return messageNotification.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(MessageNotification data, String condition) {
		if(mapDao.updateMessageNotification(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
