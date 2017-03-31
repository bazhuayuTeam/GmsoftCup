package com.cqut.dao.expertScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expertScore.ExpertScore;

import com.cqut.dao.expertScore.customInterface.IExpertScoreEntityDao;

@Component
public class ExpertScoreEntityDao extends BaseDao implements IExpertScoreEntityDao {

	@Override
	public Class<?> getEntity() {
		return ExpertScore.class;
	}
	
	public List<ExpertScore> findExpertScores(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<ExpertScore> results = new ArrayList<ExpertScore>(list.size());
		for(Map<String, Object> item : list){
			results.add(new ExpertScore(item));
		}
		return results;
	}

}
