package com.cqut.entity.problemManager;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 问题管理
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class ProblemManager extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"problemId",
			"problemDetail",
			"programme"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class 
	};

	public ProblemManager(){
		
	}
	
	public ProblemManager(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getProblemId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProblemId(String problemId) {
		getProperties().put(PROPERTICE_NAME[0], problemId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getProblemId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setProblemId(key);
	}
	
	/**
	 * 问题详情
	 */
	@Column
	public String getProblemDetail() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProblemDetail(String problemDetail) {
		getProperties().put(PROPERTICE_NAME[1], problemDetail);
	}
	
	/**
	 * 问题答案
	 */
	@Column
	public String getProgramme() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setProgramme(String programme) {
		getProperties().put(PROPERTICE_NAME[2], programme);
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
