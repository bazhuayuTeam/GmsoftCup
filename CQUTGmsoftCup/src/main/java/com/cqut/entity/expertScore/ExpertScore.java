package com.cqut.entity.expertScore;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 学生实体
 * 
 * @author zhangmiao
 * 
 */
@Entity
@DataTransferObject
public class ExpertScore extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"expertScoreID",
			"score",
			"teamId",
			"expertTargetDistributeID"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class 
	};

	public ExpertScore(){
		
	}
	
	public ExpertScore(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 年+序号
	 */
	@Id
	public String getExpertScoreID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertScoreID(String expertScoreID) {
		getProperties().put(PROPERTICE_NAME[0], expertScoreID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getExpertScoreID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setExpertScoreID(key);
	}
	
	/**
	 * 总分数
	 */
	@Column
	public String getScore() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setScore(String score) {
		getProperties().put(PROPERTICE_NAME[1], score);
	}
	
	/**
	 * 团队id
	 */
	@Column
	public String getTeamId() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setTeamId(String teamId) {
		getProperties().put(PROPERTICE_NAME[2], teamId);
	}
	
	/**
	 * 竞赛阶段详情ID
	 */
	@Column
	public String getExpertTargetDistributeID() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setExpertTargetDistributeID(String expertTargetDistributeID) {
		getProperties().put(PROPERTICE_NAME[3], expertTargetDistributeID);
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
