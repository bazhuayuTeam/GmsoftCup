package com.cqut.service.gameStep;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.gameStep.customInterface.IGameStepEntityDao;
import com.cqut.dao.gameStep.customInterface.IGameStepMapDao;
import com.cqut.entity.gameStep.GameStep;

import com.cqut.service.gameStep.customInterface.IGameStepService;

@Controller  
@RemoteProxy
public class GameStepService implements IGameStepService {

	@Resource(name = "gameStepMapDao")
	private IGameStepMapDao mapDao;
	@Resource(name = "gameStepEntityDao")
	private IGameStepEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGameSteps(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameSteps(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameSteps(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getGameStep(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findGameSteps(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public GameStep getGameStepEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findGameSteps(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new GameStep(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<GameStep> findGameStepByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findGameSteps(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(GameStep.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(GameStep value) {
		return deleteById(value.getGameStepID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(GameStep.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(GameStep[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (GameStep item : values) {
			ids[index++] = item.getGameStepID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new GameStep(value));
	}

	@RemoteMethod
	public boolean saveEntity(GameStep value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new GameStep(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(GameStep value) {
		GameStep gameStep = (GameStep) commonDao.merge(value);
		if (gameStep != null) {
			return gameStep.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(GameStep data, String condition) {
		if(mapDao.updateGameStep(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
}
