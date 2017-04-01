package com.cqut.entity.raterAppointManager;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 评委指定表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class RaterAppointManager extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"raterAppointId",
			"processResultDetailID",
			"judgesIds"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public RaterAppointManager(){
		
	}
	
	public RaterAppointManager(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getRaterAppointId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setRaterAppointId(String raterAppointId) {
		getProperties().put(PROPERTICE_NAME[0], raterAppointId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getRaterAppointId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setRaterAppointId(key);
	}
	
	/**
	 * 团队比赛流程明细ID
	 */
	@Column
	public String getProcessResultDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProcessResultDetailID(String processResultDetailID) {
		getProperties().put(PROPERTICE_NAME[1], processResultDetailID);
	}
	
	/**
	 * 指定评委Id
	 */
	@Column
	public String getJudgesIds() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setJudgesIds(String judgesIds) {
		getProperties().put(PROPERTICE_NAME[2], judgesIds);
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
