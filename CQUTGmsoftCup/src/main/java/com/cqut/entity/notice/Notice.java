package com.cqut.entity.notice;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 大赛公告
 * 
 * @author wsf
 * 
 */
@Entity
@DataTransferObject
public class Notice extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"noticeId",
			"noticeTitle",
			"publishTime",
			"publishId",
			"publishName",
			"noticeDetaile",
			"noticeType",
			"pageView",
			"fileID"
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
			String.class 
	};

	public Notice(){
		
	}
	
	public Notice(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Id
	public String getNoticeId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNoticeId(String noticeId) {
		getProperties().put(PROPERTICE_NAME[0], noticeId);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getNoticeId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setNoticeId(key);
	}
	
	/**
	 * 公告标题
	 */
	@Column
	public String getNoticeTitle() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNoticeTitle(String noticeTitle) {
		getProperties().put(PROPERTICE_NAME[1], noticeTitle);
	}
	
	/**
	 * 发布时间
	 */
	@Column
	public String getPublishTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPublishTime(String publishTime) {
		getProperties().put(PROPERTICE_NAME[2], publishTime);
	}
	
	/**
	 * 发布人Id
	 */
	@Column
	public String getPublishId() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPublishId(String publishId) {
		getProperties().put(PROPERTICE_NAME[3], publishId);
	}
	
	/**
	 * 发布人姓名
	 */
	@Column
	public String getPublishName() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPublishName(String publishName) {
		getProperties().put(PROPERTICE_NAME[4], publishName);
	}
	
	/**
	 * 公告详情
	 */
	@Column
	public String getNoticeDetaile() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNoticeDetaile(String noticeDetaile) {
		getProperties().put(PROPERTICE_NAME[5], noticeDetaile);
	}
	
	/**
	 * 公告类型
	 */
	@Column
	public String getNoticeType() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? obj.toString() : null;
	}

	public void setNoticeType(String noticeType) {
		getProperties().put(PROPERTICE_NAME[6], noticeType);
	}
	
	/**
	 * 点击量
	 */
	@Column
	public String getPageView() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setPageView(String pageView) {
		getProperties().put(PROPERTICE_NAME[7], pageView);
	}
	
	/**
	 * 附件
	 */
	@Column
	public String getFileID() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setFileID(String fileID) {
		getProperties().put(PROPERTICE_NAME[8], fileID);
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
