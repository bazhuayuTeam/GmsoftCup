package com.cqut.entity.game;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Game extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"gameID",
			"createPersonId",
			"gameName",
			"signUpStartTime",
			"sigeUpEndTime",
			"isMultiStage",
			"propagandaPath",
			"competitionType",
			"state",
			"checkUserId",
			"checkTime",
			"checkRemark",
			"competitionStartTime",
			"competitionEndTime",
			"competitionImage",
			"competitionMovie",
			"competitionRemark",
			"enclosure",
			"browseVolume",
			"level",
			"gameType",
			"leastNumbe",
			"maxNumber",
			"priority"
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
			String.class 
	};

	public Game(){
		
	}
	
	public Game(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getGameID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameID(String gameID) {
		getProperties().put(PROPERTICE_NAME[0], gameID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getGameID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setGameID(key);
	}
	
	/**
	 * 创建人ID
	 */
	@Column
	public String getCreatePersonId() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCreatePersonId(String createPersonId) {
		getProperties().put(PROPERTICE_NAME[1], createPersonId);
	}
	
	/**
	 * 大赛名称
	 */
	@Column
	public String getGameName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameName(String gameName) {
		getProperties().put(PROPERTICE_NAME[2], gameName);
	}
	
	/**
	 * 报名开始时间
	 */
	@Column
	public String getSignUpStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSignUpStartTime(String signUpStartTime) {
		getProperties().put(PROPERTICE_NAME[3], signUpStartTime);
	}
	
	/**
	 * 报名结束时间
	 */
	@Column
	public String getSigeUpEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setSigeUpEndTime(String sigeUpEndTime) {
		getProperties().put(PROPERTICE_NAME[4], sigeUpEndTime);
	}
	
	/**
	 * 是否多阶段
	 */
	@Column
	public String getIsMultiStage() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setIsMultiStage(String isMultiStage) {
		getProperties().put(PROPERTICE_NAME[5], isMultiStage);
	}
	
	/**
	 * 大赛宣传照片
	 */
	@Column
	public String getPropagandaPath() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPropagandaPath(String propagandaPath) {
		getProperties().put(PROPERTICE_NAME[6], propagandaPath);
	}
	
	/**
	 * 参赛形式
	 */
	@Column
	public String getCompetitionType() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionType(String competitionType) {
		getProperties().put(PROPERTICE_NAME[7], competitionType);
	}
	
	/**
	 * 审核状态
	 */
	@Column
	public String getState() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setState(String state) {
		getProperties().put(PROPERTICE_NAME[8], state);
	}
	
	/**
	 * 审核人
	 */
	@Column
	public String getCheckUserId() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckUserId(String checkUserId) {
		getProperties().put(PROPERTICE_NAME[9], checkUserId);
	}
	
	/**
	 * 审核时间
	 */
	@Column
	public String getCheckTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckTime(String checkTime) {
		getProperties().put(PROPERTICE_NAME[10], checkTime);
	}
	
	/**
	 * 审核备注
	 */
	@Column
	public String getCheckRemark() {
		Object obj = getProperties().get(PROPERTICE_NAME[11]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCheckRemark(String checkRemark) {
		getProperties().put(PROPERTICE_NAME[11], checkRemark);
	}
	
	/**
	 * 比赛开始时间
	 */
	@Column
	public String getCompetitionStartTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[12]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionStartTime(String competitionStartTime) {
		getProperties().put(PROPERTICE_NAME[12], competitionStartTime);
	}
	
	/**
	 * 比赛结束时间
	 */
	@Column
	public String getCompetitionEndTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[13]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionEndTime(String competitionEndTime) {
		getProperties().put(PROPERTICE_NAME[13], competitionEndTime);
	}
	
	/**
	 * 比赛精彩图片
	 */
	@Column
	public String getCompetitionImage() {
		Object obj = getProperties().get(PROPERTICE_NAME[14]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionImage(String competitionImage) {
		getProperties().put(PROPERTICE_NAME[14], competitionImage);
	}
	
	/**
	 * 比赛精彩视频
	 */
	@Column
	public String getCompetitionMovie() {
		Object obj = getProperties().get(PROPERTICE_NAME[15]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionMovie(String competitionMovie) {
		getProperties().put(PROPERTICE_NAME[15], competitionMovie);
	}
	
	/**
	 * 赛事说明
	 */
	@Column
	public String getCompetitionRemark() {
		Object obj = getProperties().get(PROPERTICE_NAME[16]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCompetitionRemark(String competitionRemark) {
		getProperties().put(PROPERTICE_NAME[16], competitionRemark);
	}
	
	/**
	 * 附件
	 */
	@Column
	public String getEnclosure() {
		Object obj = getProperties().get(PROPERTICE_NAME[17]);
		    return obj != null ? obj.toString() : null;
	}

	public void setEnclosure(String enclosure) {
		getProperties().put(PROPERTICE_NAME[17], enclosure);
	}
	
	/**
	 * 浏览量
	 */
	@Column
	public String getBrowseVolume() {
		Object obj = getProperties().get(PROPERTICE_NAME[18]);
		    return obj != null ? obj.toString() : null;
	}

	public void setBrowseVolume(String browseVolume) {
		getProperties().put(PROPERTICE_NAME[18], browseVolume);
	}
	
	/**
	 * 级别
	 */
	@Column
	public String getLevel() {
		Object obj = getProperties().get(PROPERTICE_NAME[19]);
		    return obj != null ? obj.toString() : null;
	}

	public void setLevel(String level) {
		getProperties().put(PROPERTICE_NAME[19], level);
	}
	
	/**
	 * 竞赛类型
	 */
	@Column
	public String getGameType() {
		Object obj = getProperties().get(PROPERTICE_NAME[20]);
		    return obj != null ? obj.toString() : null;
	}

	public void setGameType(String gameType) {
		getProperties().put(PROPERTICE_NAME[20], gameType);
	}
	
	/**
	 * 最少人数
	 */
	@Column
	public String getLeastNumbe() {
		Object obj = getProperties().get(PROPERTICE_NAME[21]);
		    return obj != null ? obj.toString() : null;
	}

	public void setLeastNumbe(String leastNumbe) {
		getProperties().put(PROPERTICE_NAME[21], leastNumbe);
	}
	
	/**
	 * 最多人数
	 */
	@Column
	public String getMaxNumber() {
		Object obj = getProperties().get(PROPERTICE_NAME[22]);
		    return obj != null ? obj.toString() : null;
	}

	public void setMaxNumber(String maxNumber) {
		getProperties().put(PROPERTICE_NAME[22], maxNumber);
	}
	
	/**
	 * 优先级
	 */
	@Column
	public String getPriority() {
		Object obj = getProperties().get(PROPERTICE_NAME[23]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPriority(String priority) {
		getProperties().put(PROPERTICE_NAME[23], priority);
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
