package com.cqut.service.gameStepDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import time.ChangeTime;

import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.gameStepDetail.customInterface.IGameStepDetailEntityDao;
import com.cqut.dao.gameStepDetail.customInterface.IGameStepDetailMapDao;
import com.cqut.entity.codeTable.CodeTable;
import com.cqut.entity.gameStepDetail.GameStepDetail;
import com.cqut.service.codeTable.CodeTableService;
import com.cqut.service.gameStepDetail.customInterface.IGameStepDetailService;

@Controller  
@RemoteProxy
public class GameStepDetailService implements IGameStepDetailService {

	@Resource(name = "gameStepDetailMapDao")
	private IGameStepDetailMapDao mapDao;
	@Resource(name = "gameStepDetailEntityDao")
	private IGameStepDetailEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "codeTableService")
	private CodeTableService codeTableService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		for(int i=0;i<data.size();i++){
			Map<String, Object> map = codeTableService.getCodeTable(new String[]{"codeTableName"}, "codeTableCode='"+data.get(i).get("me_type")+"'", false);
			data.get(i).put("type", map.get("codeTableName"));
		}
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getGameStepDetail(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	@RemoteMethod
	public String getTime(String condition){
		Map<String, Object> data = getGameStepDetail(new String[]{"checkEndTime","checkStartTime"}, "gameStepDetailID = '"+condition+"'", false);
		String result = "";
		if(data!=null){
			long endTime = Long.parseLong(data.get("checkEndTime") + "");
			long startTime = Long.parseLong(data.get("checkStartTime") + "");
			long currentMillis = System.currentTimeMillis();
			if(currentMillis>=startTime&&currentMillis<=endTime){
				Date date = new Date(endTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				result = "评审截止时间：" + sdf.format(date);
			}else{
				result = "over";
			}
		}else{
			result = "over";
		}
		return result;
	}
	
	public GameStepDetail getGameStepDetailEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findGameStepDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new GameStepDetail(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<GameStepDetail> findGameStepDetailByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findGameStepDetails(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(GameStepDetail.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(GameStepDetail value) {
		return deleteById(value.getGameStepDetailID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(GameStepDetail.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(GameStepDetail[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (GameStepDetail item : values) {
			ids[index++] = item.getGameStepDetailID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new GameStepDetail(value));
	}

	@RemoteMethod
	public boolean saveEntity(GameStepDetail value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new GameStepDetail(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(GameStepDetail value) {
		GameStepDetail gameStepDetail = (GameStepDetail) commonDao.merge(value);
		if (gameStepDetail != null) {
			return gameStepDetail.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(GameStepDetail data, String condition) {
		if(mapDao.updateGameStepDetail(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveData(GameStepDetail game) {
		try{
			game.setStartTime(ChangeTime.changeToMills(game.getStartTime())+"");
			game.setEndTime(ChangeTime.changeToMills(game.getEndTime())+"");
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
