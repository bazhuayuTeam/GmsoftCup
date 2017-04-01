package com.cqut.dao.problemManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.problemManager.ProblemManager;

import com.cqut.dao.problemManager.customInterface.IProblemManagerEntityDao;

@Component
public class ProblemManagerEntityDao extends BaseDao implements IProblemManagerEntityDao {

	@Override
	public Class<?> getEntity() {
		return ProblemManager.class;
	}
	
	public List<ProblemManager> findProblemManagers(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<ProblemManager> results = new ArrayList<ProblemManager>(list.size());
		for(Map<String, Object> item : list){
			results.add(new ProblemManager(item));
		}
		return results;
	}

}
