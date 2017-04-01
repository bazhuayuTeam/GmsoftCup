package com.cqut.dao.followManager.customInterface;

import java.util.List;

import com.cqut.entity.followManager.FollowManager;

public interface IFollowManagerEntityDao {

	public List<FollowManager> findFollowManagers (String[] property,
			String condition, boolean needLink, int index, int limit);
}
