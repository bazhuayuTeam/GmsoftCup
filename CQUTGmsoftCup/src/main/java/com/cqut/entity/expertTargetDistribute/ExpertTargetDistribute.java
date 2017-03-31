package com.cqut.entity.expertTargetDistribute;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 评委指标分配表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class ExpertTargetDistribute extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"expertTargetDistributeID",
			"teamID",
			"expertID",
			"gameStepDetailID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public ExpertTargetDistribute(){
		
	}
	
	public ExpertTargetDistribute(Map<String, Object> data){
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
	public String getExpertTargetDistributeID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertTargetDistributeID(String expertTargetDistributeID) {
		getProperties().put(PROPERTICE_NAME[0], expertTargetDistributeID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getExpertTargetDistributeID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setExpertTargetDistributeID(key);
	}
	
	@Column
	public String getTeamID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamID(String teamID) {
		getProperties().put(PROPERTICE_NAME[1], teamID);
	}
	
	@Column
	public String getExpertID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertID(String expertID) {
		getProperties().put(PROPERTICE_NAME[2], expertID);
	}
	
	@Column
	public String getGameStepDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepDetailID(String gameStepDetailID) {
		getProperties().put(PROPERTICE_NAME[3], gameStepDetailID);
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
