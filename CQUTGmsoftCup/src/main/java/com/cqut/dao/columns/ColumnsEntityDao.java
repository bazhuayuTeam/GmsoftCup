package com.cqut.dao.columns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.columns.Columns;

import com.cqut.dao.columns.customInterface.IColumnsEntityDao;

@Component
public class ColumnsEntityDao extends BaseDao implements IColumnsEntityDao {

	@Override
	public Class<?> getEntity() {
		return Columns.class;
	}
	
	public List<Columns> findColumnss(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Columns> results = new ArrayList<Columns>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Columns(item));
		}
		return results;
	}

}
