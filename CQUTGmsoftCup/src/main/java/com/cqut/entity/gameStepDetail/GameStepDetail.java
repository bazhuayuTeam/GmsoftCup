package com.cqut.entity.gameStepDetail;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛流程明细表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class GameStepDetail extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameStepDetailID",
			"processID",
			"gameTime",
			"standardVersionID",
			"checkStartTime",
			"checkEndTime",
			"works",
			"startTime",
			"endTime",
			"fileID",
			"parentID"
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
			String.class, 
			String.class, 
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
	 * 赛制流程
	 */
	@Column
	public String getProcessID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProcessID(String processID) {
		getProperties().put(PROPERTICE_NAME[1], processID);
	}
	
	/**
	 * 比赛时间
	 */
	@Column
	public String getGameTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameTime(String gameTime) {
		getProperties().put(PROPERTICE_NAME[2], gameTime);
	}
	
	/**
	 * 评审版本
	 */
	@Column
	public String getStandardVersionID() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionID(String standardVersionID) {
		getProperties().put(PROPERTICE_NAME[3], standardVersionID);
	}
	
	/**
	 * 评审开始时间
	 */
	@Column
	public String getCheckStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckStartTime(String checkStartTime) {
		getProperties().put(PROPERTICE_NAME[4], checkStartTime);
	}
	
	/**
	 * 评审结束时间
	 */
	@Column
	public String getCheckEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckEndTime(String checkEndTime) {
		getProperties().put(PROPERTICE_NAME[5], checkEndTime);
	}
	
	/**
	 * 是否需要提交作品
	 */
	@Column
	public String getWorks() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setWorks(String works) {
		getProperties().put(PROPERTICE_NAME[6], works);
	}
	
	/**
	 * 提交作品开始时间
	 */
	@Column
	public String getStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStartTime(String startTime) {
		getProperties().put(PROPERTICE_NAME[7], startTime);
	}
	
	/**
	 * 提交作品结束时间
	 */
	@Column
	public String getEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEndTime(String endTime) {
		getProperties().put(PROPERTICE_NAME[8], endTime);
	}
	
	/**
	 * 附件
	 */
	@Column
	public String getFileID() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileID(String fileID) {
		getProperties().put(PROPERTICE_NAME[9], fileID);
	}
	
	/**
	 * 所属大赛或阶段
	 */
	@Column
	public String getParentID() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? obj.toString() : null;
	}

	public void setParentID(String parentID) {
		getProperties().put(PROPERTICE_NAME[10], parentID);
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
