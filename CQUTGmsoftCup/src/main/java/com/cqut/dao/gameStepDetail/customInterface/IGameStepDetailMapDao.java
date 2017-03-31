package com.cqut.dao.gameStepDetail.customInterface;

import java.util.List;
import java.util.Map;

public interface IGameStepDetailMapDao {

	public List<Map<String, Object>> findGameStepDetails(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateGameStepDetail(Map<String, Object> properties, String condition);
}
