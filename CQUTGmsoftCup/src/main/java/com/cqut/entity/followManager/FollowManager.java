package com.cqut.entity.followManager;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛关注表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class FollowManager extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"followId",
			"userId",
			"followTime"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public FollowManager(){
		
	}
	
	public FollowManager(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getFollowId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFollowId(String followId) {
		getProperties().put(PROPERTICE_NAME[0], followId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getFollowId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setFollowId(key);
	}
	
	/**
	 * 用户ID
	 */
	@Column
	public String getUserId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUserId(String userId) {
		getProperties().put(PROPERTICE_NAME[1], userId);
	}
	
	/**
	 * 关注时间
	 */
	@Column
	public String getFollowTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFollowTime(String followTime) {
		getProperties().put(PROPERTICE_NAME[2], followTime);
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
