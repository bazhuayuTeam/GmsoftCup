package com.cqut.dao.gameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameResult.GameResult;

import com.cqut.dao.gameResult.customInterface.IGameResultEntityDao;

@Component
public class GameResultEntityDao extends BaseDao implements IGameResultEntityDao {

	@Override
	public Class<?> getEntity() {
		return GameResult.class;
	}
	
	public List<GameResult> findGameResults(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<GameResult> results = new ArrayList<GameResult>(list.size());
		for(Map<String, Object> item : list){
			results.add(new GameResult(item));
		}
		return results;
	}

}
