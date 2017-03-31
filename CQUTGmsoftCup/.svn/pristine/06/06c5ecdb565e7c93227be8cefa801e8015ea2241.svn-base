package com.cqut.service.game;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import time.ChangeTime;

import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.game.customInterface.IGameEntityDao;
import com.cqut.dao.game.customInterface.IGameMapDao;
import com.cqut.entity.game.Game;

import com.cqut.service.game.customInterface.IGameService;

@Controller  
@RemoteProxy
public class GameService implements IGameService {

	@Resource(name = "gameMapDao")
	private IGameMapDao mapDao;
	@Resource(name = "gameEntityDao")
	private IGameEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGames(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGames(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGames(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getGame(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findGames(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Game getGameEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findGames(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Game(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Game> findGameByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findGames(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Game.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Game value) {
		return deleteById(value.getGameID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Game.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Game[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Game item : values) {
			ids[index++] = item.getGameID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Game(value));
	}

	@RemoteMethod
	public boolean saveEntity(Game value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Game(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Game value) {
		Game game = (Game) commonDao.merge(value);
		if (game != null) {
			return game.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Game data, String condition) {
		if(mapDao.updateGame(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean deleteData(String id){
		if(this.deleteById(id)){
			String sqlString="delete from gameStep where gameId='"+id+"'";
			commonDao.execute(sqlString);
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveData(Game game){
		try{
		game.setStartTime(ChangeTime.changeToMills(game.getStartTime())+"");
		game.setEndTime(ChangeTime.changeToMills(game.getEndTime())+"");
		game.setState(1);
		if(!this.saveEntity(game)){
			return false;
		}
		return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
