package com.cqut.service.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.game.customInterface.IGameEntityDao;
import com.cqut.dao.game.customInterface.IGameMapDao;
import com.cqut.entity.game.Game;
import com.cqut.entity.hostUnit.HostUnit;
import com.cqut.service.game.customInterface.IGameService;
import com.cqut.service.hostUnit.customInterface.IHostUnitService;
import com.cqut.util.BeanUtil;

@Controller  
@RemoteProxy
public class GameService implements IGameService {

	@Resource(name = "gameMapDao")
	private IGameMapDao mapDao;
	@Resource(name = "gameEntityDao")
	private IGameEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "hostUnitService")
	private IHostUnitService hostUnitService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		String[] gameProperties=new String[7];
		for(int i=0,j=0,len=properties.length;i<len;i++){
			if(!properties[i].equals("hostUnit")){
				gameProperties[j]=properties[i];
				j++;
			}
		}
		List<Map<String, Object>> data = mapDao.findGames(gameProperties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		for(int k=0,leng=data.size();k<leng;k++){
			Map<String,Object> item=data.get(k);
			String hostUnitName="";
			List<Map<String,Object>> hosts= hostUnitService.findMapByPropertiesQuick(new String[]{"hostUnitName"}, "gameId='"+item.get("gameId")+"' and type=0",false);
			for(int h=0,len=hosts.size();h<len;h++){
				hostUnitName+=(hosts.get(h).get("hostUnitName"));
				if(h<len-1){
					hostUnitName+=",";
				}
			}
			data.get(k).put("hostUnit", hostUnitName);
		}
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
	public Map<String, Object> getGame(String[] gameProperties,String[] hostUnitProperties,String gameCondtion,
			String hostUnitcondition, boolean needLink){
		String hostUnitStr="";
		String secondUnitStr="";
		List<Map<String, Object>> data = mapDao.findGames(gameProperties, gameCondtion, "", "", needLink, -1, -1);
		List<Map<String,Object>> hostUnitData=hostUnitService.findMapByPropertiesQuick(hostUnitProperties, hostUnitcondition, false);
		for(int i=0,len=hostUnitData.size();i<len;i++){
			if(hostUnitData.get(i).get("type").equals("0")){
				hostUnitStr+=(hostUnitData.get(i).get("hostUnitName")+",");
			}else{
				secondUnitStr+=(hostUnitData.get(i).get("hostUnitName")+",");
			}
		}
		if(data!=null&&data.size()>0&&hostUnitData!=null&&hostUnitData.size()>0){
			data.get(0).put("hostUnitName",hostUnitStr);
			data.get(0).put("secondUnitName",secondUnitStr);
		}
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
	public String saveData(Game data){
		String id = BeanUtil.createId();
		data.setGameID(id);
		data.setState("0");
		saveEntity(data);
		return id;
	}
	
	@RemoteMethod
	public boolean updateData(Game data,String condtion){
		return updateEntity(data,condtion);
	}
}
