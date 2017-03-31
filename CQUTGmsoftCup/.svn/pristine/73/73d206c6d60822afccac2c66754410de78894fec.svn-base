package com.cqut.dao.team;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.team.Team;

import com.cqut.dao.team.customInterface.ITeamMapDao;

@Component
public class TeamMapDao extends BaseDao implements ITeamMapDao {

	
	public Class<?> getEntity() {
		return Team.class;
	}

	public List<Map<String, Object>> findTeams(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateTeam(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}