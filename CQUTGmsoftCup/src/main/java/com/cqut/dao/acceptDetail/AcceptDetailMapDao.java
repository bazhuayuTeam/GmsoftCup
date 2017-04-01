package com.cqut.dao.acceptDetail;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.acceptDetail.AcceptDetail;

import com.cqut.dao.acceptDetail.customInterface.IAcceptDetailMapDao;

@Component
public class AcceptDetailMapDao extends BaseDao implements IAcceptDetailMapDao {

	
	public Class<?> getEntity() {
		return AcceptDetail.class;
	}

	public List<Map<String, Object>> findAcceptDetails(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateAcceptDetail(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}