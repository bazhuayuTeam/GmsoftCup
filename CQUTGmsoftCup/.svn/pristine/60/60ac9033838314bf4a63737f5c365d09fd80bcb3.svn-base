package com.cqut.dao.crew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.crew.Crew;

import com.cqut.dao.crew.customInterface.ICrewEntityDao;

@Component
public class CrewEntityDao extends BaseDao implements ICrewEntityDao {

	@Override
	public Class<?> getEntity() {
		return Crew.class;
	}
	
	public List<Crew> findCrews(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Crew> results = new ArrayList<Crew>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Crew(item));
		}
		return results;
	}

}
