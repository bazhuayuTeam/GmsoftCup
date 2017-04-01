package com.cqut.dao.problemManager.customInterface;

import java.util.List;

import com.cqut.entity.problemManager.ProblemManager;

public interface IProblemManagerEntityDao {

	public List<ProblemManager> findProblemManagers (String[] property,
			String condition, boolean needLink, int index, int limit);
}
