package com.cqut.dao.gameStep.customInterface;

import java.util.List;
import java.util.Map;

public interface IGameStepMapDao {

	public List<Map<String, Object>> findGameSteps(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateGameStep(Map<String, Object> properties, String condition);
}
