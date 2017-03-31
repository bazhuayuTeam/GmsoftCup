package com.cqut.dao.gameStepDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameStepDetail.GameStepDetail;

import com.cqut.dao.gameStepDetail.customInterface.IGameStepDetailEntityDao;

@Component
public class GameStepDetailEntityDao extends BaseDao implements IGameStepDetailEntityDao {

	@Override
	public Class<?> getEntity() {
		return GameStepDetail.class;
	}
	
	public List<GameStepDetail> findGameStepDetails(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<GameStepDetail> results = new ArrayList<GameStepDetail>(list.size());
		for(Map<String, Object> item : list){
			results.add(new GameStepDetail(item));
		}
		return results;
	}

}
