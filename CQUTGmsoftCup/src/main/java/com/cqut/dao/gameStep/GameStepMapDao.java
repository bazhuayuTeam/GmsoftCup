package com.cqut.dao.gameStep;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameStep.GameStep;

import com.cqut.dao.gameStep.customInterface.IGameStepMapDao;

@Component
public class GameStepMapDao extends BaseDao implements IGameStepMapDao {

	
	public Class<?> getEntity() {
		return GameStep.class;
	}

	public List<Map<String, Object>> findGameSteps(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateGameStep(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}