package com.cqut.dao.followManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.followManager.FollowManager;

import com.cqut.dao.followManager.customInterface.IFollowManagerEntityDao;

@Component
public class FollowManagerEntityDao extends BaseDao implements IFollowManagerEntityDao {

	@Override
	public Class<?> getEntity() {
		return FollowManager.class;
	}
	
	public List<FollowManager> findFollowManagers(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<FollowManager> results = new ArrayList<FollowManager>(list.size());
		for(Map<String, Object> item : list){
			results.add(new FollowManager(item));
		}
		return results;
	}

}
