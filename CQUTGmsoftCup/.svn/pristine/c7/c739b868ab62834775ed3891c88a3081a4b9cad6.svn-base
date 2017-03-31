package com.cqut.entity.gameStep;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 竞赛阶段详情
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class GameStep extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameStepID",
			"gameStep",
			"startTime",
			"endTime",
			"limits",
			"state",
			"gameID",
			"prizeID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
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
	
	
	/**
	 * ID
	 */
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
	 * 竞赛类别
	 */
	@Column
	public String getGameStep() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStep(String gameStep) {
		getProperties().put(PROPERTICE_NAME[1], gameStep);
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
	 * 报名截止时间
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
	 * 团队人数限制
	 */
	@Column
	public Integer getLimits() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setLimits(Integer limits) {
		getProperties().put(PROPERTICE_NAME[4], limits);
	}
	
	/**
	 * 状态
	 */
	@Column
	public String getState() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setState(String state) {
		getProperties().put(PROPERTICE_NAME[5], state);
	}
	
	/**
	 * 比赛id
	 */
	@Column
	public String getGameID() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameID(String gameID) {
		getProperties().put(PROPERTICE_NAME[6], gameID);
	}
	
	/**
	 * 奖项版本
	 */
	@Column
	public String getPrizeID() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPrizeID(String prizeID) {
		getProperties().put(PROPERTICE_NAME[7], prizeID);
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
