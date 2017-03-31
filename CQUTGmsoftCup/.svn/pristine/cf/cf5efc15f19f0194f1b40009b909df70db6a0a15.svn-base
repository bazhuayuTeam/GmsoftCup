package com.cqut.entity.operator;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 操作员表
 * 
 * 
 * 
 */
@Entity
@DataTransferObject
public class Operator extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"operatorID",
			"ZGH",
			"password",
			"disabled",
			"name",
			"academicID",
			"disciplineID",
			"systemFileID",
			"operatorKind"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class,
			String.class
	};

	public Operator(){
		
	}
	
	public Operator(Map<String, Object> data){
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
	public String getOperatorID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorID(String operatorID) {
		getProperties().put(PROPERTICE_NAME[0], operatorID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getOperatorID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setOperatorID(key);
	}
	
	/**
	 * 职工号
	 */
	@Column
	public String getZGH() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setZGH(String ZGH) {
		getProperties().put(PROPERTICE_NAME[1], ZGH);
	}
	
	/**
	 * 密码
	 */
	@Column
	public String getPassword() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPassword(String password) {
		getProperties().put(PROPERTICE_NAME[2], password);
	}
	
	/**
	 * 是否禁用
	 */
	@Column
	public Integer getDisabled() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setDisabled(Integer disabled) {
		getProperties().put(PROPERTICE_NAME[3], disabled);
	}
	
	/**
	 * 姓名
	 */
	@Column
	public String getName() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setName(String name) {
		getProperties().put(PROPERTICE_NAME[4], name);
	}
	
	/**
	 * 学院ID
	 */
	@Column
	public String getAcademicID() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicID(String academicID) {
		getProperties().put(PROPERTICE_NAME[5], academicID);
	}
	
	/**
	 * 专业ID
	 */
	@Column
	public String getDisciplineID() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDisciplineID(String disciplineID) {
		getProperties().put(PROPERTICE_NAME[6], disciplineID);
	}
	
	/**
	 * 头像
	 */
	@Column
	public String getSystemFileID() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSystemFileID(String systemFileID) {
		getProperties().put(PROPERTICE_NAME[7], systemFileID);
	}
	
	/**
	 * 操作人员类型
	 */
	@Column
	public String getOperatorKind() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorKind(String operatorKind) {
		getProperties().put(PROPERTICE_NAME[8], operatorKind);
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
