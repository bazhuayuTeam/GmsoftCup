package com.cqut.dao.processResultDetail;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.processResultDetail.ProcessResultDetail;

import com.cqut.dao.processResultDetail.customInterface.IProcessResultDetailMapDao;

@Component
public class ProcessResultDetailMapDao extends BaseDao implements IProcessResultDetailMapDao {

	
	public Class<?> getEntity() {
		return ProcessResultDetail.class;
	}

	public List<Map<String, Object>> findProcessResultDetails(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateProcessResultDetail(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}