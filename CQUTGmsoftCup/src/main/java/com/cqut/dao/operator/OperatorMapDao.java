package com.cqut.dao.operator;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.operator.Operator;

import com.cqut.dao.operator.customInterface.IOperatorMapDao;

@Component
public class OperatorMapDao extends BaseDao implements IOperatorMapDao {

	
	public Class<?> getEntity() {
		return Operator.class;
	}

	public List<Map<String, Object>> findOperators(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateOperator(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}