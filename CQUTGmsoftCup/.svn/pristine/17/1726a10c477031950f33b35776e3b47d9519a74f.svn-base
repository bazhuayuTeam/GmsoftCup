package com.cqut.entity.standardVersion;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 指标版本
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class StandardVersion extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"standardVersionID",
			"citeState",
			"standardVersionName"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public StandardVersion(){
		
	}
	
	public StandardVersion(Map<String, Object> data){
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

	public void setStandardVersionID(String standardVersionID) {
		getProperties().put(PROPERTICE_NAME[0], standardVersionID);
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
	
	/**
	 * 竞赛类别
	 */
	@Column
	public String getCiteState() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCiteState(String citeState) {
		getProperties().put(PROPERTICE_NAME[1], citeState);
	}
	
	/**
	 * 版本名称
	 */
	@Column
	public String getStandardVersionName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionName(String standardVersionName) {
		getProperties().put(PROPERTICE_NAME[2], standardVersionName);
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
