package com.cqut.dao.raterAppointManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.raterAppointManager.RaterAppointManager;

import com.cqut.dao.raterAppointManager.customInterface.IRaterAppointManagerEntityDao;

@Component
public class RaterAppointManagerEntityDao extends BaseDao implements IRaterAppointManagerEntityDao {

	@Override
	public Class<?> getEntity() {
		return RaterAppointManager.class;
	}
	
	public List<RaterAppointManager> findRaterAppointManagers(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<RaterAppointManager> results = new ArrayList<RaterAppointManager>(list.size());
		for(Map<String, Object> item : list){
			results.add(new RaterAppointManager(item));
		}
		return results;
	}

}
