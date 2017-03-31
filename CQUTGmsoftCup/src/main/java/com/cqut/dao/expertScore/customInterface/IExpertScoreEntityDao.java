package com.cqut.dao.expertScore.customInterface;

import java.util.List;

import com.cqut.entity.expertScore.ExpertScore;

public interface IExpertScoreEntityDao {

	public List<ExpertScore> findExpertScores (String[] property,
			String condition, boolean needLink, int index, int limit);
}
