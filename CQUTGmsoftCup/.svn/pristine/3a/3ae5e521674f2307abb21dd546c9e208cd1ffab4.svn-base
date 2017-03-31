package com.cqut.entity.project;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 项目
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Project extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"projectID",
			"teamId",
			"fileId",
			"type"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public Project(){
		
	}
	
	public Project(Map<String, Object> data){
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
	public String getProjectID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProjectID(String projectID) {
		getProperties().put(PROPERTICE_NAME[0], projectID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getProjectID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setProjectID(key);
	}
	
	/**
	 * 团队id
	 */
	@Column
	public String getTeamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamId(String teamId) {
		getProperties().put(PROPERTICE_NAME[1], teamId);
	}
	
	/**
	 * 文件id
	 */
	@Column
	public String getFileId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileId(String fileId) {
		getProperties().put(PROPERTICE_NAME[2], fileId);
	}
	
	/**
	 * 比赛阶段
	 */
	@Column
	public String getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setType(String type) {
		getProperties().put(PROPERTICE_NAME[3], type);
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
