package com.cqut.dao.comParam.customInterface;

import java.util.List;

import com.cqut.entity.comParam.ComParam;

public interface IComParamEntityDao {

	public List<ComParam> findComParams (String[] property,
			String condition, boolean needLink, int index, int limit);
}
