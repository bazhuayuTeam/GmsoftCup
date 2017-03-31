package com.cqut.entity.target;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 指标
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Target extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"targetCode",
			"targetName",
			"targetExplain",
			"targetLevel",
			"targetParentCode",
			"isLastTarget",
			"targetScore",
			"standardVersionID",
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			Integer.class, 
			String.class, 
			Integer.class, 
			Integer.class, 
			String.class, 
	};

	public Target(){
		
	}
	
	public Target(Map<String, Object> data){
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
	public String getTargetCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetCode(String targetCode) {
		getProperties().put(PROPERTICE_NAME[0], targetCode);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getTargetCode();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setTargetCode(key);
	}
	
	/**
	 * 指标名称
	 */
	@Column
	public String getTargetName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetName(String targetName) {
		getProperties().put(PROPERTICE_NAME[1], targetName);
	}
	
	/**
	 * 指标说明
	 */
	@Column
	public String getTargetExplain() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetExplain(String targetExplain) {
		getProperties().put(PROPERTICE_NAME[2], targetExplain);
	}
	
	/**
	 * 指标等级
	 */
	@Column
	public Integer getTargetLevel() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setTargetLevel(Integer targetLevel) {
		getProperties().put(PROPERTICE_NAME[3], targetLevel);
	}
	
	/**
	 * 父级指标代码
	 */
	@Column
	public String getTargetParentCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTargetParentCode(String targetParentCode) {
		getProperties().put(PROPERTICE_NAME[4], targetParentCode);
	}
	
	/**
	 * 是否是末级指标
	 */
	@Column
	public Integer getIsLastTarget() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setIsLastTarget(Integer isLastTarget) {
		getProperties().put(PROPERTICE_NAME[5], isLastTarget);
	}
	
	/**
	 * 指标分数
	 */
	@Column
	public Integer getTargetScore() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setTargetScore(Integer targetScore) {
		getProperties().put(PROPERTICE_NAME[6], targetScore);
	}
	
	/**
	 * 所属版本ID
	 */
	@Column
	public String getStandardVersionID() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setStandardVersionID(String standardVersionID) {
		getProperties().put(PROPERTICE_NAME[7], standardVersionID);
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
