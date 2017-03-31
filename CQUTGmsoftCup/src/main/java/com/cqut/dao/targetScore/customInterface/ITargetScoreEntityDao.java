package com.cqut.dao.targetScore.customInterface;

import java.util.List;

import com.cqut.entity.targetScore.TargetScore;

public interface ITargetScoreEntityDao {

	public List<TargetScore> findTargetScores (String[] property,
			String condition, boolean needLink, int index, int limit);
}
