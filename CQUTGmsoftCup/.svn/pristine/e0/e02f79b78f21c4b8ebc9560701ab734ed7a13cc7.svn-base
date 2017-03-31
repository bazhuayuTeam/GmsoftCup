package com.cqut.dao.academic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.academic.Academic;

import com.cqut.dao.academic.customInterface.IAcademicEntityDao;

@Component
public class AcademicEntityDao extends BaseDao implements IAcademicEntityDao {

	@Override
	public Class<?> getEntity() {
		return Academic.class;
	}
	
	public List<Academic> findAcademics(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Academic> results = new ArrayList<Academic>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Academic(item));
		}
		return results;
	}

}
