package com.cqut.entity.hostUnit;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛主办/赞助单位
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class HostUnit extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"hostUnitId",
			"hostUnitContact",
			"hostUnitContactPerson",
			"hostUnitContactMail",
			"type",
			"gameId"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public HostUnit(){
		
	}
	
	public HostUnit(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getHostUnitId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setHostUnitId(String hostUnitId) {
		getProperties().put(PROPERTICE_NAME[0], hostUnitId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getHostUnitId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setHostUnitId(key);
	}
	
	/**
	 * 主办单位联系方式
	 */
	@Column
	public String getHostUnitContact() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setHostUnitContact(String hostUnitContact) {
		getProperties().put(PROPERTICE_NAME[1], hostUnitContact);
	}
	
	/**
	 * 主办单位联系人
	 */
	@Column
	public String getHostUnitContactPerson() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setHostUnitContactPerson(String hostUnitContactPerson) {
		getProperties().put(PROPERTICE_NAME[2], hostUnitContactPerson);
	}
	
	/**
	 * 主办单位联系邮箱
	 */
	@Column
	public String getHostUnitContactMail() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setHostUnitContactMail(String hostUnitContactMail) {
		getProperties().put(PROPERTICE_NAME[3], hostUnitContactMail);
	}
	
	/**
	 * 类型
	 */
	@Column
	public String getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setType(String type) {
		getProperties().put(PROPERTICE_NAME[4], type);
	}
	
	/**
	 * 大赛ID
	 */
	@Column
	public String getGameId() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameId(String gameId) {
		getProperties().put(PROPERTICE_NAME[5], gameId);
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
