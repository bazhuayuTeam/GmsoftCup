package com.cqut.entity.roleAssign;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 角色分配
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class RoleAssign extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"roleAssignID",
			"roleID",
			"operatorID",
			"taskID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class,
			String.class
	};

	public RoleAssign(){
		
	}
	
	public RoleAssign(Map<String, Object> data){
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
	public String getRoleAssignID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setRoleAssignID(String roleAssignID) {
		getProperties().put(PROPERTICE_NAME[0], roleAssignID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getRoleAssignID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setRoleAssignID(key);
	}
	
	@Column
	public String getRoleID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setRoleID(String roleID) {
		getProperties().put(PROPERTICE_NAME[1], roleID);
	}
	
	/**
	 * 操作员ID
	 */
	@Column
	public String getOperatorID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setOperatorID(String operatorID) {
		getProperties().put(PROPERTICE_NAME[2], operatorID);
	}
	/**
	 * 任务ID
	 */
	@Column
	public String getTaskID() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTaskID(String taskID) {
		getProperties().put(PROPERTICE_NAME[3], taskID);
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
