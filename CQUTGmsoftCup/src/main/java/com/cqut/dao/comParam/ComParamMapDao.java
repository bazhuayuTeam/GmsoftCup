package com.cqut.dao.comParam;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.comParam.ComParam;

import com.cqut.dao.comParam.customInterface.IComParamMapDao;

@Component
public class ComParamMapDao extends BaseDao implements IComParamMapDao {

	
	public Class<?> getEntity() {
		return ComParam.class;
	}

	public List<Map<String, Object>> findComParams(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateComParam(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}