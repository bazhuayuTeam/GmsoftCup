package com.cqut.dao.notice.customInterface;

import java.util.List;

import com.cqut.entity.notice.Notice;

public interface INoticeEntityDao {

	public List<Notice> findNotices (String[] property,
			String condition, boolean needLink, int index, int limit);
}
