package com.cqut.entity.targetSystem;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 指标体系表
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Standardversion extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
		"standardVersionID",
		"standardVersionName",
		"citeState",
		"createrId",
		"createrTime"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
		String.class,  
		String.class,
		String.class,
		String.class,
		String.class
	};

	public Standardversion() {

	}

	public Standardversion(Map<String, Object> data) {
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
	public String getStandardVersionID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionID(String targetSystemID) {
		getProperties().put(PROPERTICE_NAME[0], targetSystemID);
	}

	@Transient
	@Override
	public String getEntityKey() {
		return getStandardVersionID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setStandardVersionID(key);
	}

	@Column
	public String getStandardVersionName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionName(String targetSystemName) {
		getProperties().put(PROPERTICE_NAME[1], targetSystemName);
	}
	
	@Column
	public String getCiteState() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		return obj != null ? obj.toString() : null;
	}

	public void setCiteState(String citeState) {
		getProperties().put(PROPERTICE_NAME[2], citeState);
	}
	
	@Column
	public String getCreaterId() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		return obj != null ? obj.toString() : null;
	}

	public void setCreaterId(String createrId) {
		getProperties().put(PROPERTICE_NAME[3], createrId);
	}
	
	@Column
	public String getCreaterTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		return obj != null ? obj.toString() : null;
	}

	public void setCreaterTime(String createrTime) {
		getProperties().put(PROPERTICE_NAME[4], createrTime);
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
