package com.cqut.entity.academic;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 部门表
 * 
 * @author wujuntao
 * 
 */
@Entity
@DataTransferObject
public class Academic extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"academicID",
			"academicCode",
			"academicName",
			"academicShort",
			"academicType"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public Academic(){
		
	}
	
	public Academic(Map<String, Object> data){
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
	public String getAcademicID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicID(String academicID) {
		getProperties().put(PROPERTICE_NAME[0], academicID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getAcademicID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setAcademicID(key);
	}
	
	/**
	 * 部门代号
	 */
	@Column
	public String getAcademicCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicCode(String academicCode) {
		getProperties().put(PROPERTICE_NAME[1], academicCode);
	}
	
	/**
	 * 部门名称
	 */
	@Column
	public String getAcademicName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicName(String academicName) {
		getProperties().put(PROPERTICE_NAME[2], academicName);
	}
	
	/**
	 * 部门简称
	 */
	@Column
	public String getAcademicShort() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicShort(String academicShort) {
		getProperties().put(PROPERTICE_NAME[3], academicShort);
	}
	
	/**
	 * 部门类别
	 */
	@Column
	public String getAcademicType() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicType(String academicType) {
		getProperties().put(PROPERTICE_NAME[4], academicType);
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
