package com.cqut.dao.gameStepDetail.customInterface;

import java.util.List;

import com.cqut.entity.gameStepDetail.GameStepDetail;

public interface IGameStepDetailEntityDao {

	public List<GameStepDetail> findGameStepDetails (String[] property,
			String condition, boolean needLink, int index, int limit);
}
