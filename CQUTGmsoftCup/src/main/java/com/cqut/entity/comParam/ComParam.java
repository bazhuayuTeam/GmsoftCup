package com.cqut.entity.comParam;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛参数设置
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class ComParam extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"comParamId",
			"comParamNo",
			"comParamType",
			"comParamName",
			"comParamLevel",
			"parentComParamNo",
			"useId"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public ComParam(){
		
	}
	
	public ComParam(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getComParamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setComParamId(String comParamId) {
		getProperties().put(PROPERTICE_NAME[0], comParamId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getComParamId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setComParamId(key);
	}
	
	/**
	 * 比赛参数编码
	 */
	@Column
	public String getComParamNo() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setComParamNo(String comParamNo) {
		getProperties().put(PROPERTICE_NAME[1], comParamNo);
	}
	
	/**
	 * 比赛参数类型
	 */
	@Column
	public String getComParamType() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setComParamType(String comParamType) {
		getProperties().put(PROPERTICE_NAME[2], comParamType);
	}
	
	/**
	 * 比赛参赛名称
	 */
	@Column
	public String getComParamName() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setComParamName(String comParamName) {
		getProperties().put(PROPERTICE_NAME[3], comParamName);
	}
	
	/**
	 * 比赛参数等级
	 */
	@Column
	public String getComParamLevel() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setComParamLevel(String comParamLevel) {
		getProperties().put(PROPERTICE_NAME[4], comParamLevel);
	}
	
	/**
	 * 父级编码
	 */
	@Column
	public String getParentComParamNo() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setParentComParamNo(String parentComParamNo) {
		getProperties().put(PROPERTICE_NAME[5], parentComParamNo);
	}
	
	/**
	 * 用户ID
	 */
	@Column
	public String getUseId() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUseId(String useId) {
		getProperties().put(PROPERTICE_NAME[6], useId);
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
