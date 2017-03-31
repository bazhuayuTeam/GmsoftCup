package com.cqut.dao.systemFile;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.systemFile.SystemFile;

import com.cqut.dao.systemFile.customInterface.ISystemFileMapDao;

@Component
public class SystemFileMapDao extends BaseDao implements ISystemFileMapDao {

	
	public Class<?> getEntity() {
		return SystemFile.class;
	}

	public List<Map<String, Object>> findSystemFiles(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateSystemFile(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}