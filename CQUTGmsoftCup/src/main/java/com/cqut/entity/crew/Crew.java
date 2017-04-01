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
			"teamId",
			"membernName",
			"membernNo",
			"membernAcademy",
			"type",
			"telePhone",
			"email",
			"school"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
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
	 * 团队ID
	 */
	@Column
	public String getTeamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamId(String teamId) {
		getProperties().put(PROPERTICE_NAME[1], teamId);
	}
	
	/**
	 * 成员姓名
	 */
	@Column
	public String getMembernName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMembernName(String membernName) {
		getProperties().put(PROPERTICE_NAME[2], membernName);
	}
	
	/**
	 * 成员学号
	 */
	@Column
	public String getMembernNo() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMembernNo(String membernNo) {
		getProperties().put(PROPERTICE_NAME[3], membernNo);
	}
	
	/**
	 * 成员学院
	 */
	@Column
	public String getMembernAcademy() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMembernAcademy(String membernAcademy) {
		getProperties().put(PROPERTICE_NAME[4], membernAcademy);
	}
	
	/**
	 * 类型
	 */
	@Column
	public String getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setType(String type) {
		getProperties().put(PROPERTICE_NAME[5], type);
	}
	
	/**
	 * 电话号码
	 */
	@Column
	public String getTelePhone() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTelePhone(String telePhone) {
		getProperties().put(PROPERTICE_NAME[6], telePhone);
	}
	
	/**
	 * 邮箱
	 */
	@Column
	public String getEmail() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEmail(String email) {
		getProperties().put(PROPERTICE_NAME[7], email);
	}
	
	/**
	 * 学校
	 */
	@Column
	public String getSchool() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSchool(String school) {
		getProperties().put(PROPERTICE_NAME[8], school);
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
