package com.cqut.dao.targetScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.targetScore.TargetScore;

import com.cqut.dao.targetScore.customInterface.ITargetScoreEntityDao;

@Component
public class TargetScoreEntityDao extends BaseDao implements ITargetScoreEntityDao {

	@Override
	public Class<?> getEntity() {
		return TargetScore.class;
	}
	
	public List<TargetScore> findTargetScores(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<TargetScore> results = new ArrayList<TargetScore>(list.size());
		for(Map<String, Object> item : list){
			results.add(new TargetScore(item));
		}
		return results;
	}

}
