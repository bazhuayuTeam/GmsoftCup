package com.cqut.dao.gameStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameStep.GameStep;

import com.cqut.dao.gameStep.customInterface.IGameStepEntityDao;

@Component
public class GameStepEntityDao extends BaseDao implements IGameStepEntityDao {

	@Override
	public Class<?> getEntity() {
		return GameStep.class;
	}
	
	public List<GameStep> findGameSteps(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<GameStep> results = new ArrayList<GameStep>(list.size());
		for(Map<String, Object> item : list){
			results.add(new GameStep(item));
		}
		return results;
	}

}
