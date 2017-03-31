package com.cqut.dao.expert.customInterface;

import java.util.List;

import com.cqut.entity.expert.Expert;

public interface IExpertEntityDao {

	public List<Expert> findExperts (String[] property,
			String condition, boolean needLink, int index, int limit);
}
