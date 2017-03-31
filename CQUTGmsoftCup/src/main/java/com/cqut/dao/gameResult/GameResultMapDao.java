package com.cqut.dao.gameResult;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameResult.GameResult;

import com.cqut.dao.gameResult.customInterface.IGameResultMapDao;

@Component
public class GameResultMapDao extends BaseDao implements IGameResultMapDao {

	
	public Class<?> getEntity() {
		return GameResult.class;
	}

	public List<Map<String, Object>> findGameResults(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateGameResult(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}