package com.cqut.dao.targetSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.targetSystem.Standardversion;

import com.cqut.dao.targetSystem.customInterface.ITargetSystemEntityDao;

@Component
public class TargetSystemEntityDao extends BaseDao implements ITargetSystemEntityDao {

	@Override
	public Class<?> getEntity() {
		return Standardversion.class;
	}
	
	public List<Standardversion> findTargetSystems(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Standardversion> results = new ArrayList<Standardversion>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Standardversion(item));
		}
		return results;
	}

}
