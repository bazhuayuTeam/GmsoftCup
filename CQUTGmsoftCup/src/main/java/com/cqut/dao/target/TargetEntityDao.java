package com.cqut.dao.target;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.target.Target;

import com.cqut.dao.target.customInterface.ITargetEntityDao;

@Component
public class TargetEntityDao extends BaseDao implements ITargetEntityDao {

	@Override
	public Class<?> getEntity() {
		return Target.class;
	}
	
	public List<Target> findTargets(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Target> results = new ArrayList<Target>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Target(item));
		}
		return results;
	}

}
