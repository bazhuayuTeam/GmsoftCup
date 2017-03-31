package com.cqut.entity.gameStepDetail;

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
public class GameStepDetail extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameStepDetailID",
			"gameStepID",
			"type",
			"endTime",
			"state",
			"startTime",
			"checkStartTime",
			"checkEndTime",
			"standardVersionID"
			
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
			String.class , 
			String.class , 
			String.class , 
			String.class 
	};

	public GameStepDetail(){
		
	}
	
	public GameStepDetail(Map<String, Object> data){
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
	public String getGameStepDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepDetailID(String gameStepDetailID) {
		getProperties().put(PROPERTICE_NAME[0], gameStepDetailID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getGameStepDetailID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setGameStepDetailID(key);
	}
	
	/**
	 * 竞赛阶段id
	 */
	@Column
	public String getGameStepID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepID(String gameStepID) {
		getProperties().put(PROPERTICE_NAME[1], gameStepID);
	}
	
	/**
	 * 类型
	 */
	@Column
	public String getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setType(String type) {
		getProperties().put(PROPERTICE_NAME[2], type);
	}
	
	/**
	 * 文档提交截止时间
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
	 * 是否发布
	 */
	@Column
	public Integer getState() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setState(Integer state) {
		getProperties().put(PROPERTICE_NAME[4], state);
	}
	
	/**
	 * 时间
	 */
	@Column
	public String getStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStartTime(String startTime) {
		getProperties().put(PROPERTICE_NAME[5], startTime);
	}
	
	@Column
	public String getCheckStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckStartTime(String checkStartTime) {
		getProperties().put(PROPERTICE_NAME[6], checkStartTime);
	}
	
	@Column
	public String getCheckEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckEndTime(String checkEndTime) {
		getProperties().put(PROPERTICE_NAME[7], checkEndTime);
	}
	
	@Column
	public String getStandardVersionID() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionID(String standardVersionID) {
		getProperties().put(PROPERTICE_NAME[8], standardVersionID);
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
