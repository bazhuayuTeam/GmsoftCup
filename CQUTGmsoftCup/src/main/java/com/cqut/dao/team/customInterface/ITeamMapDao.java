package com.cqut.dao.team.customInterface;

import java.util.List;
import java.util.Map;

public interface ITeamMapDao {

	public List<Map<String, Object>> findTeams(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateTeam(Map<String, Object> properties, String condition);
}
