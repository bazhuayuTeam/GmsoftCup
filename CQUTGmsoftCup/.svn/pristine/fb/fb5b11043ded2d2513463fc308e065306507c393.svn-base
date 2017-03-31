package com.cqut.dao.expert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expert.Expert;

import com.cqut.dao.expert.customInterface.IExpertEntityDao;

@Component
public class ExpertEntityDao extends BaseDao implements IExpertEntityDao {

	@Override
	public Class<?> getEntity() {
		return Expert.class;
	}
	
	public List<Expert> findExperts(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Expert> results = new ArrayList<Expert>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Expert(item));
		}
		return results;
	}

}
