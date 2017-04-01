package com.cqut.dao.notice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.notice.Notice;

import com.cqut.dao.notice.customInterface.INoticeMapDao;

@Component
public class NoticeMapDao extends BaseDao implements INoticeMapDao {

	
	public Class<?> getEntity() {
		return Notice.class;
	}

	public List<Map<String, Object>> findNotices(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateNotice(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}