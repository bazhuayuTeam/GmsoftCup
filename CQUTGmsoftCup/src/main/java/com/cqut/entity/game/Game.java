package com.cqut.entity.game;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 比赛表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Game extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameID",
			"year",
			"gameName",
			"startTime",
			"endTime",
			"state"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class,
			String.class, 
			String.class,
			Integer.class
	};

	public Game(){
		
	}
	
	public Game(Map<String, Object> data){
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
	public String getGameID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameID(String gameID) {
		getProperties().put(PROPERTICE_NAME[0], gameID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getGameID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setGameID(key);
	}
	
	/**
	 * 学年
	 */
	@Column
	public String getYear() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setYear(String year) {
		getProperties().put(PROPERTICE_NAME[1], year);
	}
	
	/**
	 * 比赛名称
	 */
	@Column
	public String getGameName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameName(String gameName) {
		getProperties().put(PROPERTICE_NAME[2], gameName);
	}
	
	@Column
	public String getStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStartTime(String startTime) {
		getProperties().put(PROPERTICE_NAME[3], startTime);
	}
	
	@Column
	public String getEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEndTime(String endTime) {
		getProperties().put(PROPERTICE_NAME[4], endTime);
	}
	
	@Column
	public Integer getState() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ?(Integer) obj : 0;
	}

	public void setState(Integer state) {
		getProperties().put(PROPERTICE_NAME[5], state);
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
