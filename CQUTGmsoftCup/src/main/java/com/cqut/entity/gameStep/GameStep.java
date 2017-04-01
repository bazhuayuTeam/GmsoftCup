package com.cqut.entity.gameStep;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛阶段表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class GameStep extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameStepID",
			"gameStepName",
			"startTime",
			"endTime",
			"siGetPro",
			"type",
			"leastNumber",
			"maxNumber",
			"gameId"
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

	public GameStep(){
		
	}
	
	public GameStep(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getGameStepID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepID(String gameStepID) {
		getProperties().put(PROPERTICE_NAME[0], gameStepID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getGameStepID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setGameStepID(key);
	}
	
	/**
	 * 大赛阶段名称
	 */
	@Column
	public String getGameStepName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepName(String gameStepName) {
		getProperties().put(PROPERTICE_NAME[1], gameStepName);
	}
	
	/**
	 * 报名开始时间
	 */
	@Column
	public String getStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStartTime(String startTime) {
		getProperties().put(PROPERTICE_NAME[2], startTime);
	}
	
	/**
	 * 报名结束时间
	 */
	@Column
	public String getEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEndTime(String endTime) {
		getProperties().put(PROPERTICE_NAME[3], endTime);
	}
	
	/**
	 * 是否需要提交作品
	 */
	@Column
	public String getSiGetPro() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSiGetPro(String siGetPro) {
		getProperties().put(PROPERTICE_NAME[4], siGetPro);
	}
	
	/**
	 * 参赛形式
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
	 * 最少人数
	 */
	@Column
	public String getLeastNumber() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setLeastNumber(String leastNumber) {
		getProperties().put(PROPERTICE_NAME[6], leastNumber);
	}
	
	/**
	 * 最多人数
	 */
	@Column
	public String getMaxNumber() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMaxNumber(String maxNumber) {
		getProperties().put(PROPERTICE_NAME[7], maxNumber);
	}
	
	/**
	 * 大赛id
	 */
	@Column
	public String getGameId() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameId(String gameId) {
		getProperties().put(PROPERTICE_NAME[8], gameId);
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
