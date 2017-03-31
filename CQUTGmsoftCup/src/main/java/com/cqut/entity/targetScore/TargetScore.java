package com.cqut.entity.targetScore;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 指标分数表
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class TargetScore extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"TargetScoreID",
			"expertTargetDistributeID",
			"targetCode",
			"score"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			Integer.class 
	};

	public TargetScore(){
		
	}
	
	public TargetScore(Map<String, Object> data){
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
	public String getTargetScoreID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetScoreID(String TargetScoreID) {
		getProperties().put(PROPERTICE_NAME[0], TargetScoreID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getTargetScoreID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setTargetScoreID(key);
	}
	
	/**
	 * 指标分配ID
	 */
	@Column
	public String getExpertTargetDistributeID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertTargetDistributeID(String expertTargetDistributeID) {
		getProperties().put(PROPERTICE_NAME[1], expertTargetDistributeID);
	}
	
	/**
	 * 指标代码
	 */
	@Column
	public String getTargetCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetCode(String targetCode) {
		getProperties().put(PROPERTICE_NAME[2], targetCode);
	}
	
	/**
	 * 分数
	 */
	@Column
	public Integer getScore() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setScore(Integer score) {
		getProperties().put(PROPERTICE_NAME[3], score);
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
