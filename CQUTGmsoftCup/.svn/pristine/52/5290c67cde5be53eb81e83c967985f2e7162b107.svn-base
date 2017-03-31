package com.cqut.entity.gameResult;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 竞赛结果表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class GameResult extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"Code",
			"teamId",
			"gameStepDetailID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public GameResult(){
		
	}
	
	public GameResult(Map<String, Object> data){
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
	public String getCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCode(String Code) {
		getProperties().put(PROPERTICE_NAME[0], Code);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getCode();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setCode(key);
	}
	
	@Column
	public String getTeamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamId(String teamId) {
		getProperties().put(PROPERTICE_NAME[1], teamId);
	}
	
	@Column
	public String getGameStepDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepDetailID(String gameStepDetailID) {
		getProperties().put(PROPERTICE_NAME[2], gameStepDetailID);
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
