package com.cqut.entity.expert;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 评委表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Expert extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"expertID",
			"telephone",
			"email",
			"department",
			"education",
			"name",
			"operatorID",
			"birth"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public Expert(){
		
	}
	
	public Expert(Map<String, Object> data){
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
	public String getExpertID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertID(String expertID) {
		getProperties().put(PROPERTICE_NAME[0], expertID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getExpertID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setExpertID(key);
	}
	
	@Column
	public String getTelephone() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTelephone(String telephone) {
		getProperties().put(PROPERTICE_NAME[1], telephone);
	}
	
	@Column
	public String getEmail() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEmail(String email) {
		getProperties().put(PROPERTICE_NAME[2], email);
	}
	
	@Column
	public String getDepartment() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDepartment(String department) {
		getProperties().put(PROPERTICE_NAME[3], department);
	}
	
	/**
	 * 学历
	 */
	@Column
	public String getEducation() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEducation(String education) {
		getProperties().put(PROPERTICE_NAME[4], education);
	}
	
	@Column
	public String getName() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setName(String name) {
		getProperties().put(PROPERTICE_NAME[5], name);
	}
	
	@Column
	public String getOperatorID() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorID(String operatorID) {
		getProperties().put(PROPERTICE_NAME[6], operatorID);
	}
	
	@Column
	public String getBirth() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setBirth(String birth) {
		getProperties().put(PROPERTICE_NAME[7], birth);
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
