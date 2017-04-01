package com.cqut.entity.messageNotification;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 消息通知表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class MessageNotification extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"messageId",
			"noticeId",
			"messageTime",
			"messageType",
			"message"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public MessageNotification(){
		
	}
	
	public MessageNotification(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getMessageId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageId(String messageId) {
		getProperties().put(PROPERTICE_NAME[0], messageId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getMessageId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setMessageId(key);
	}
	
	/**
	 * 公告ID
	 */
	@Column
	public String getNoticeId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNoticeId(String noticeId) {
		getProperties().put(PROPERTICE_NAME[1], noticeId);
	}
	
	/**
	 * 通知时间
	 */
	@Column
	public String getMessageTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageTime(String messageTime) {
		getProperties().put(PROPERTICE_NAME[2], messageTime);
	}
	
	/**
	 * 类型
	 */
	@Column
	public String getMessageType() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageType(String messageType) {
		getProperties().put(PROPERTICE_NAME[3], messageType);
	}
	
	/**
	 * 内容
	 */
	@Column
	public String getMessage() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessage(String message) {
		getProperties().put(PROPERTICE_NAME[4], message);
	}
	

	@Transient
	@Override
	public String[] getEntityPropertiesName() {
		return PROPERTICE_NAME;
	}

	@Transient
	@Override
	public Class<?>[] getEntityPropertiesType() {
		return PROPERTICE_TYPE;
	}

}
