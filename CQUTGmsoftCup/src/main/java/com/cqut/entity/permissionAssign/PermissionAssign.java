package com.cqut.entity.permissionAssign;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 权限分配表
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class PermissionAssign extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"permissionAssignID",
			"roleID",
			"moduleID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public PermissionAssign(){
		
	}
	
	public PermissionAssign(Map<String, Object> data){
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
	public String getPermissionAssignID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPermissionAssignID(String permissionAssignID) {
		getProperties().put(PROPERTICE_NAME[0], permissionAssignID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getPermissionAssignID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setPermissionAssignID(key);
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
	 * moduleID
	 */
	@Column
	public String getmoduleID() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setmoduleID(String moduleID) {
		getProperties().put(PROPERTICE_NAME[2], moduleID);
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
