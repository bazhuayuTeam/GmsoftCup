package com.cqut.entity.crew;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 组员
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Crew extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"crewID",
			"userId",
			"teamId",
			"type"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public Crew(){
		
	}
	
	public Crew(Map<String, Object> data){
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
	public String getCrewID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCrewID(String crewID) {
		getProperties().put(PROPERTICE_NAME[0], crewID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getCrewID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setCrewID(key);
	}
	
	/**
	 * 用户id
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
	 * 团队id
	 */
	@Column
	public String getTeamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamId(String teamId) {
		getProperties().put(PROPERTICE_NAME[2], teamId);
	}
	
	/**
	 * 类型
	 */
	@Column
	public String getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setType(String type) {
		getProperties().put(PROPERTICE_NAME[3], type);
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
