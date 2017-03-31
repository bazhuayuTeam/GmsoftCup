package com.cqut.dao.codeTable;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.codeTable.CodeTable;

import com.cqut.dao.codeTable.customInterface.ICodeTableMapDao;

@Component
public class CodeTableMapDao extends BaseDao implements ICodeTableMapDao {

	
	public Class<?> getEntity() {
		return CodeTable.class;
	}

	public List<Map<String, Object>> findCodeTables(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateCodeTable(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}