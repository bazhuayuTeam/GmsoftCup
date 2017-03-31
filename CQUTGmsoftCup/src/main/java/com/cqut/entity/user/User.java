package com.cqut.entity.user;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 用户表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class User extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"userID",
			"roleID",
			"name",
			"academy",
			"major",
			"education",
			"phone",
			"email",
			"password",
			"QQ",
			"units",         
			"profession",    //职称
			"type"
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
			String.class,
			String.class,
			Integer.class
	};

	public User(){
		
	}
	
	public User(Map<String, Object> data){
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
	public String getUserID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUserID(String userID) {
		getProperties().put(PROPERTICE_NAME[0], userID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getUserID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setUserID(key);
	}
	
	/**
	 * 用户类型
	 */
	@Column
	public String getRoleID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? (String)obj : null;
	}

	public void setRoleID(String roleID) {
		getProperties().put(PROPERTICE_NAME[1], roleID);
	}
	
	
	/**
	 * 姓名
	 */
	@Column
	public String getName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setName(String name) {
		getProperties().put(PROPERTICE_NAME[2], name);
	}
	
	/**
	 * 学院
	 */
	@Column
	public String getAcademy() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademy(String academy) {
		getProperties().put(PROPERTICE_NAME[3], academy);
	}
	
	/**
	 * 专业
	 */
	@Column
	public String getMajor() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMajor(String major) {
		getProperties().put(PROPERTICE_NAME[4], major);
	}
	
	/**
	 * 学历
	 */
	@Column
	public String getEducation() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEducation(String education) {
		getProperties().put(PROPERTICE_NAME[5], education);
	}
	
	/**
	 * 电话号码
	 */
	@Column
	public String getPhone() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPhone(String phone) {
		getProperties().put(PROPERTICE_NAME[6], phone);
	}
	
	/**
	 * 邮箱
	 */
	@Column
	public String getEmail() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEmail(String email) {
		getProperties().put(PROPERTICE_NAME[7], email);
	}
	
	/**
	 * 密码
	 */
	@Column
	public String getPassword() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPassword(String password) {
		getProperties().put(PROPERTICE_NAME[8], password);
	}
	
	/**
	 * QQ号
	 */
	@Column
	public String getQQ() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setQQ(String QQ) {
		getProperties().put(PROPERTICE_NAME[9], QQ);
	}
	
	@Column
	public String getUnits() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUnits(String units) {
		getProperties().put(PROPERTICE_NAME[10], units);
	}
	
	//职称
	@Column
	public String getProfession() {
		Object obj = getProperties().get(PROPERTICE_NAME[11]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProfession(String profession) {
		getProperties().put(PROPERTICE_NAME[11], profession);
	}
	
	@Column
	public int getType() {
		Object obj = getProperties().get(PROPERTICE_NAME[12]);
	    return obj != null ? (Integer)obj : 0;
	}

	public void setType(int type) {
		getProperties().put(PROPERTICE_NAME[12], type);
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
