package com.cqut.dao.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.notice.Notice;

import com.cqut.dao.notice.customInterface.INoticeEntityDao;

@Component
public class NoticeEntityDao extends BaseDao implements INoticeEntityDao {

	@Override
	public Class<?> getEntity() {
		return Notice.class;
	}
	
	public List<Notice> findNotices(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Notice> results = new ArrayList<Notice>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Notice(item));
		}
		return results;
	}

}
