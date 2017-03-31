package com.cqut.dao.game.customInterface;

import java.util.List;

import com.cqut.entity.game.Game;

public interface IGameEntityDao {

	public List<Game> findGames (String[] property,
			String condition, boolean needLink, int index, int limit);
}
