package com.cqut.dao.followManager;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.followManager.FollowManager;

import com.cqut.dao.followManager.customInterface.IFollowManagerMapDao;

@Component
public class FollowManagerMapDao extends BaseDao implements IFollowManagerMapDao {

	
	public Class<?> getEntity() {
		return FollowManager.class;
	}

	public List<Map<String, Object>> findFollowManagers(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateFollowManager(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}