package com.cqut.entity.processResultDetail;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 报名-比赛流程明细表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class ProcessResultDetail extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"processResultDetailID",
			"gameStepDetailID",
			"signUpId",
			"fileId",
			"fileReviewTime"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public ProcessResultDetail(){
		
	}
	
	public ProcessResultDetail(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getProcessResultDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProcessResultDetailID(String processResultDetailID) {
		getProperties().put(PROPERTICE_NAME[0], processResultDetailID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getProcessResultDetailID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setProcessResultDetailID(key);
	}
	
	/**
	 * 比赛流程明细ID
	 */
	@Column
	public String getGameStepDetailID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameStepDetailID(String gameStepDetailID) {
		getProperties().put(PROPERTICE_NAME[1], gameStepDetailID);
	}
	
	/**
	 * 报名Id
	 */
	@Column
	public String getSignUpId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSignUpId(String signUpId) {
		getProperties().put(PROPERTICE_NAME[2], signUpId);
	}
	
	/**
	 * 作品ID
	 */
	@Column
	public String getFileId() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileId(String fileId) {
		getProperties().put(PROPERTICE_NAME[3], fileId);
	}
	
	/**
	 * 作品提交时间
	 */
	@Column
	public String getFileReviewTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileReviewTime(String fileReviewTime) {
		getProperties().put(PROPERTICE_NAME[4], fileReviewTime);
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
