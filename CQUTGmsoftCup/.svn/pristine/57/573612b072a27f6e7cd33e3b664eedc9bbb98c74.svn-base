package com.cqut.dao.game.customInterface;

import java.util.List;
import java.util.Map;

public interface IGameMapDao {

	public List<Map<String, Object>> findGames(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateGame(Map<String, Object> properties, String condition);
}
