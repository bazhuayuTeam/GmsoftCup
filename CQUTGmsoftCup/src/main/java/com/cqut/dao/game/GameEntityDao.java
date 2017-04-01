package com.cqut.dao.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.game.Game;

import com.cqut.dao.game.customInterface.IGameEntityDao;

@Component
public class GameEntityDao extends BaseDao implements IGameEntityDao {

	@Override
	public Class<?> getEntity() {
		return Game.class;
	}
	
	public List<Game> findGames(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Game> results = new ArrayList<Game>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Game(item));
		}
		return results;
	}

}
