package com.cqut.entity.acceptDetail;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 接收明细表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class AcceptDetail extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"acceptId",
			"messageId",
			"userId"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public AcceptDetail(){
		
	}
	
	public AcceptDetail(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getAcceptId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcceptId(String acceptId) {
		getProperties().put(PROPERTICE_NAME[0], acceptId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getAcceptId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setAcceptId(key);
	}
	
	/**
	 * 消息通知ID
	 */
	@Column
	public String getMessageId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMessageId(String messageId) {
		getProperties().put(PROPERTICE_NAME[1], messageId);
	}
	
	/**
	 * 用户Id
	 */
	@Column
	public String getUserId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUserId(String userId) {
		getProperties().put(PROPERTICE_NAME[2], userId);
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
