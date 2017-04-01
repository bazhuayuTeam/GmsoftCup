package com.cqut.dao.notice.customInterface;

import java.util.List;
import java.util.Map;

public interface INoticeMapDao {

	public List<Map<String, Object>> findNotices(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateNotice(Map<String, Object> properties, String condition);
}
