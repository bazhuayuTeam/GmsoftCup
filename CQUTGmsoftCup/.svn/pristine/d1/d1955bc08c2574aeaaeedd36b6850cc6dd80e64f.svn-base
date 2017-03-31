package com.cqut.dao.codeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.codeTable.CodeTable;

import com.cqut.dao.codeTable.customInterface.ICodeTableEntityDao;

@Component
public class CodeTableEntityDao extends BaseDao implements ICodeTableEntityDao {

	@Override
	public Class<?> getEntity() {
		return CodeTable.class;
	}
	
	public List<CodeTable> findCodeTables(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<CodeTable> results = new ArrayList<CodeTable>(list.size());
		for(Map<String, Object> item : list){
			results.add(new CodeTable(item));
		}
		return results;
	}

}
