package com.cqut.dao.discipline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.discipline.Discipline;

import com.cqut.dao.discipline.customInterface.IDisciplineEntityDao;

@Component
public class DisciplineEntityDao extends BaseDao implements IDisciplineEntityDao {

	@Override
	public Class<?> getEntity() {
		return Discipline.class;
	}
	
	public List<Discipline> findDisciplines(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Discipline> results = new ArrayList<Discipline>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Discipline(item));
		}
		return results;
	}

}
