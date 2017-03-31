package com.cqut.entity.desktop;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 桌面
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Desktop extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"desktopID",
			"operatorID",
			"columnID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public Desktop(){
		
	}
	
	public Desktop(Map<String, Object> data){
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
	public String getDesktopID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDesktopID(String desktopID) {
		getProperties().put(PROPERTICE_NAME[0], desktopID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getDesktopID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setDesktopID(key);
	}
	
	@Column
	public String getOperatorID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorID(String operatorID) {
		getProperties().put(PROPERTICE_NAME[1], operatorID);
	}
	
	/**
	 * 栏目ID
	 */
	@Column
	public String getColumnID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setColumnID(String columnID) {
		getProperties().put(PROPERTICE_NAME[2], columnID);
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
