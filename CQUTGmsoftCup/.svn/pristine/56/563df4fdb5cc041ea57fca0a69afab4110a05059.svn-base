package com.cqut.entity.systemFile;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;
import java.util.Date;

import com.cqut.entity.AbstractEntity;

/**
 * 系统文件实体
 * 
 * @author lifangxun
 * 
 */
@Entity
@DataTransferObject
public class SystemFile extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"ID",
			"FileName",
			"NewTime",
			"AlterTime",
			"Creator",
			"WhoAlter",
			"FileSize",
			"FileType",
			"SavePath"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			Date.class, 
			Date.class, 
			String.class, 
			String.class, 
			Long.class, 
			String.class, 
			String.class 
	};

	public SystemFile(){
		
	}
	
	public SystemFile(Map<String, Object> data){
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
	public String getID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setID(String ID) {
		getProperties().put(PROPERTICE_NAME[0], ID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setID(key);
	}
	
	/**
	 * 文件编号
	 */
	@Column
	public String getFileName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileName(String FileName) {
		getProperties().put(PROPERTICE_NAME[1], FileName);
	}
	
	/**
	 * 创建时间
	 */
	@Column
	public Date getNewTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? (Date)obj : null;
	}

	public void setNewTime(Date NewTime) {
		getProperties().put(PROPERTICE_NAME[2], NewTime);
	}
	
	/**
	 * 修改时间
	 */
	@Column
	public Date getAlterTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Date)obj : null;
	}

	public void setAlterTime(Date AlterTime) {
		getProperties().put(PROPERTICE_NAME[3], AlterTime);
	}
	
	/**
	 * 创建人
	 */
	@Column
	public String getCreator() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCreator(String Creator) {
		getProperties().put(PROPERTICE_NAME[4], Creator);
	}
	
	/**
	 * 修改人
	 */
	@Column
	public String getWhoAlter() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setWhoAlter(String WhoAlter) {
		getProperties().put(PROPERTICE_NAME[5], WhoAlter);
	}
	
	/**
	 * 文件大小
	 */
	@Column
	public Long getFileSize() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? (Long)obj : 0;
	}

	public void setFileSize(Long FileSize) {
		getProperties().put(PROPERTICE_NAME[6], FileSize);
	}
	
	/**
	 * 文件类型
	 */
	@Column
	public String getFileType() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileType(String FileType) {
		getProperties().put(PROPERTICE_NAME[7], FileType);
	}
	
	/**
	 * 存储路径
	 */
	@Column
	public String getSavePath() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSavePath(String SavePath) {
		getProperties().put(PROPERTICE_NAME[8], SavePath);
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
