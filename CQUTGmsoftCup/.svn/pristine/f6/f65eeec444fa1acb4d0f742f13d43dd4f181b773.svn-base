package com.cqut.entity.role;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 角色
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Role extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"roleID",
			"roleName"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class 
	};

	public Role(){
		
	}
	
	public Role(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 角色ID
	 */
	@Id
	public String getRoleID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setRoleID(String roleID) {
		getProperties().put(PROPERTICE_NAME[0], roleID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getRoleID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setRoleID(key);
	}
	
	@Column
	public String getRoleName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setRoleName(String roleName) {
		getProperties().put(PROPERTICE_NAME[1], roleName);
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
