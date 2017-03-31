package com.cqut.entity.messageReceive;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 消息接收表
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class MessageReceive extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"messageReceiveID",
			"messageID",
			"operatorID",
			"isRead"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			Integer.class 
	};

	public MessageReceive(){
		
	}
	
	public MessageReceive(Map<String, Object> data){
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
	public String getMessageReceiveID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageReceiveID(String messageReceiveID) {
		getProperties().put(PROPERTICE_NAME[0], messageReceiveID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getMessageReceiveID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setMessageReceiveID(key);
	}
	
	@Column
	public String getMessageID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageID(String messageID) {
		getProperties().put(PROPERTICE_NAME[1], messageID);
	}
	
	/**
	 * 接受者
	 */
	@Column
	public String getOperatorID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorID(String operatorID) {
		getProperties().put(PROPERTICE_NAME[2], operatorID);
	}
	
	/**
	 * 是否已读
	 */
	@Column
	public Integer getIsRead() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setIsRead(Integer i) {
		getProperties().put(PROPERTICE_NAME[3], i);
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
