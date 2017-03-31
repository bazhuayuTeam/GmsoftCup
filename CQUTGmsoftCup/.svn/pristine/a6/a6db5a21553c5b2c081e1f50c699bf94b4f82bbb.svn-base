package com.cqut.entity.message;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;
import java.util.Date;

import com.cqut.entity.AbstractEntity;

/**
 * 消息
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Message extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"messageID",
			"messageType",
			"title",
			"content",
			"sender",
			"attachment",
			"sendTime"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			Integer.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Date.class 
	};

	public Message(){
		
	}
	
	public Message(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ID
	 */
	@Id
	public String getMessageID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageID(String messageID) {
		getProperties().put(PROPERTICE_NAME[0], messageID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getMessageID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setMessageID(key);
	}
	
	@Column
	public Integer getMessageType() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setMessageType(Integer messageType) {
		getProperties().put(PROPERTICE_NAME[1], messageType);
	}
	
	/**
	 * 标题
	 */
	@Column
	public String getTitle() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTitle(String title) {
		getProperties().put(PROPERTICE_NAME[2], title);
	}
	
	/**
	 * 内容
	 */
	@Column
	public String getContent() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setContent(String content) {
		getProperties().put(PROPERTICE_NAME[3], content);
	}
	
	/**
	 * 发送者
	 */
	@Column
	public String getSender() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSender(String sender) {
		getProperties().put(PROPERTICE_NAME[4], sender);
	}
	
	/**
	 * 附件
	 */
	@Column
	public String getAttachment() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAttachment(String attachment) {
		getProperties().put(PROPERTICE_NAME[5], attachment);
	}
	
	/**
	 * 发送时间
	 */
	@Column
	public Date getSendTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? (Date)obj : null;
	}

	public void setSendTime(Date sendTime) {
		getProperties().put(PROPERTICE_NAME[6], sendTime);
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
