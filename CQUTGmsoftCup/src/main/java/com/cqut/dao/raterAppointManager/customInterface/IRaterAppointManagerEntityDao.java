package com.cqut.dao.raterAppointManager.customInterface;

import java.util.List;

import com.cqut.entity.raterAppointManager.RaterAppointManager;

public interface IRaterAppointManagerEntityDao {

	public List<RaterAppointManager> findRaterAppointManagers (String[] property,
			String condition, boolean needLink, int index, int limit);
}
