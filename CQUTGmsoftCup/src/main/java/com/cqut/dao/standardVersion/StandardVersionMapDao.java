package com.cqut.dao.standardVersion;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.standardVersion.StandardVersion;

import com.cqut.dao.standardVersion.customInterface.IStandardVersionMapDao;

@Component
public class StandardVersionMapDao extends BaseDao implements IStandardVersionMapDao {

	
	public Class<?> getEntity() {
		return StandardVersion.class;
	}

	public List<Map<String, Object>> findStandardVersions(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateStandardVersion(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}