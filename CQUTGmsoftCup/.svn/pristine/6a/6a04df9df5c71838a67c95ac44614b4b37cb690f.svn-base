package com.cqut.dao.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.team.Team;

import com.cqut.dao.team.customInterface.ITeamEntityDao;

@Component
public class TeamEntityDao extends BaseDao implements ITeamEntityDao {

	@Override
	public Class<?> getEntity() {
		return Team.class;
	}
	
	public List<Team> findTeams(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Team> results = new ArrayList<Team>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Team(item));
		}
		return results;
	}

}
