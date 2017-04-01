package com.cqut.dao.acceptDetail.customInterface;

import java.util.List;

import com.cqut.entity.acceptDetail.AcceptDetail;

public interface IAcceptDetailEntityDao {

	public List<AcceptDetail> findAcceptDetails (String[] property,
			String condition, boolean needLink, int index, int limit);
}
