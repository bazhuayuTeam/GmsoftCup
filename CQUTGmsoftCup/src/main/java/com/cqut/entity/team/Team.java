package com.cqut.entity.team;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;
import java.util.Date;

import com.cqut.entity.AbstractEntity;

/**
 * 团队表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Team extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"teamID",
			"teamName",
			"captianId",
			"gameStepID",
			"checkState",
			"note",
			"title",
			"time",
			"no",
			"gameStyle",
			"teacher"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
			String.class, 
			String.class, 
			Date.class,
			String.class, 
			String.class,
			String.class
	};

	public Team(){
		
	}
	
	public Team(Map<String, Object> data){
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
	public String getTeamID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamID(String teamID) {
		getProperties().put(PROPERTICE_NAME[0], teamID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getTeamID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setTeamID(key);
	}
	
	@Column
	public String getTeamName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamName(String teamName) {
		getProperties().put(PROPERTICE_NAME[1], teamName);
	}
	
	@Column
	public String getCaptianId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCaptianId(String captianId) {
		getProperties().put(PROPERTICE_NAME[2], captianId);
	}
	
	@Column
	public String getGameStepID() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepID(String gameStepID) {
		getProperties().put(PROPERTICE_NAME[3], gameStepID);
	}
	
	/**
	 * 审核状态
	 */
	@Column
	public Integer getCheckState() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setCheckState(Integer checkState) {
		getProperties().put(PROPERTICE_NAME[4], checkState);
	}
	
	@Column
	public String getNote() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNote(String note) {
		getProperties().put(PROPERTICE_NAME[5], note);
	}
	
	@Column
	public String getTitle() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTitle(String title) {
		getProperties().put(PROPERTICE_NAME[6], title);
	}
	
	@Column
	public Date getTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? (Date)obj : null;
	}

	public void setTime(Date time) {
		getProperties().put(PROPERTICE_NAME[7], time);
	}
	
	@Column
	public String getNo() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNo(String no) {
		getProperties().put(PROPERTICE_NAME[8], no);
	}
	
	@Column
	public String getGameStyle() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStyle(String gameStyle) {
		getProperties().put(PROPERTICE_NAME[9], gameStyle);
	}
	
	@Column
	public String getTeacher() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeacher(String teacher) {
		getProperties().put(PROPERTICE_NAME[10], teacher);
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
