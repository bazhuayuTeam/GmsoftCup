package com.cqut.dao.gameStepDetail;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.gameStepDetail.GameStepDetail;

import com.cqut.dao.gameStepDetail.customInterface.IGameStepDetailMapDao;

@Component
public class GameStepDetailMapDao extends BaseDao implements IGameStepDetailMapDao {

	
	public Class<?> getEntity() {
		return GameStepDetail.class;
	}

	public List<Map<String, Object>> findGameStepDetails(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateGameStepDetail(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}