package com.cqut.dao.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.game.Game;

import com.cqut.dao.game.customInterface.IGameMapDao;

@Component
public class GameMapDao extends BaseDao implements IGameMapDao {

	
	public Class<?> getEntity() {
		return Game.class;
	}

	public List<Map<String, Object>> findGames(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateGame(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}