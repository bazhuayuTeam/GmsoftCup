package com.cqut.dao.gameResult.customInterface;

import java.util.List;
import java.util.Map;

public interface IGameResultMapDao {

	public List<Map<String, Object>> findGameResults(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateGameResult(Map<String, Object> properties, String condition);
}
