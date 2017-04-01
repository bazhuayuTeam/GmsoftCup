package com.cqut.dao.processResultDetail.customInterface;

import java.util.List;

import com.cqut.entity.processResultDetail.ProcessResultDetail;

public interface IProcessResultDetailEntityDao {

	public List<ProcessResultDetail> findProcessResultDetails (String[] property,
			String condition, boolean needLink, int index, int limit);
}
