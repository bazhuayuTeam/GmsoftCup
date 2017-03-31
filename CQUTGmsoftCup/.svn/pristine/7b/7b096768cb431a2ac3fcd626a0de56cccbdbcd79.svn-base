package com.cqut.dao.columns;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.columns.Columns;

import com.cqut.dao.columns.customInterface.IColumnsMapDao;

@Component
public class ColumnsMapDao extends BaseDao implements IColumnsMapDao {

	
	public Class<?> getEntity() {
		return Columns.class;
	}

	public List<Map<String, Object>> findColumnss(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateColumns(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}