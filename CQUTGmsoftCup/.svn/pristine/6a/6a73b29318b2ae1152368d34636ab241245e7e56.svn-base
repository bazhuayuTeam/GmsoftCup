package com.cqut.service.gameResult;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.gameResult.customInterface.IGameResultEntityDao;
import com.cqut.dao.gameResult.customInterface.IGameResultMapDao;
import com.cqut.entity.gameResult.GameResult;

import com.cqut.service.expertTargetDistribute.ExpertTargetDistributeService;
import com.cqut.service.gameResult.customInterface.IGameResultService;

@Controller  
@RemoteProxy
public class GameResultService implements IGameResultService {

	@Resource(name = "gameResultMapDao")
	private IGameResultMapDao mapDao;
	@Resource(name = "gameResultEntityDao")
	private IGameResultEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "expertTargetDistributeService")
	private ExpertTargetDistributeService expertTargetDistributeService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimitNew(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		for(int i=0;i<data.size();i++){
			String[] pro={"ex_name","expertTargetDistributeID"};
			List<Map<String, Object>> map=expertTargetDistributeService.findMapByPropertiesQuick(pro,
					"teamID='"+data.get(i).get("mo_teamID")+"' and gameStepDetailID='"+data.get(i).get("gameStepDetailID")+"'", true);
			String expert="";
			for(int j=0;j<map.size();j++){
				if(j==map.size()-1){
					expert+=map.get(j).get("ex_name");
				}
				else{
					expert+=map.get(j).get("ex_name")+",";
				}
			}
			data.get(i).put("expert", expert);
		}
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getGameResult(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public GameResult getGameResultEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findGameResults(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new GameResult(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<GameResult> findGameResultByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findGameResults(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(GameResult.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(GameResult value) {
		return deleteById(value.getCode());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(GameResult.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(GameResult[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (GameResult item : values) {
			ids[index++] = item.getCode();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new GameResult(value));
	}

	@RemoteMethod
	public boolean saveEntity(GameResult value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new GameResult(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(GameResult value) {
		GameResult gameResult = (GameResult) commonDao.merge(value);
		if (gameResult != null) {
			return gameResult.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(GameResult data, String condition) {
		if(mapDao.updateGameResult(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
