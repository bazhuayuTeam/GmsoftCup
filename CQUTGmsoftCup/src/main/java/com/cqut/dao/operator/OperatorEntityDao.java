package com.cqut.dao.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.operator.Operator;

import com.cqut.dao.operator.customInterface.IOperatorEntityDao;

@Component
public class OperatorEntityDao extends BaseDao implements IOperatorEntityDao {

	@Override
	public Class<?> getEntity() {
		return Operator.class;
	}
	
	public List<Operator> findOperators(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Operator> results = new ArrayList<Operator>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Operator(item));
		}
		return results;
	}

}
