package com.cqut.service.gameStep;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.gameStep.customInterface.IGameStepEntityDao;
import com.cqut.dao.gameStep.customInterface.IGameStepMapDao;
import com.cqut.entity.gameStep.GameStep;
import com.cqut.service.gameStep.customInterface.IGameStepService;
import com.cqut.util.OperatorUtil;

import flex.messaging.io.ArrayList;

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
	public List<Map<String, Object>> findPropertiesQuick(String[] properties,
			String condition, boolean needLink,HttpServletRequest request) {
		int type = Integer.parseInt(request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE)+"");
		if(type == 1){
			return null;
		}
		String currentTime = System.currentTimeMillis() + "";
		List<Map<String, Object>> data = findMapByPropertiesQuick(properties, " startTime<" + currentTime + " and endTime>" + currentTime, needLink);
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
	
	@RemoteMethod
	public boolean checkTime(){
		String currentTime = System.currentTimeMillis() + "";
		int length = mapDao.findCount(new String[]{"gameStepID"}, " startTime<" + currentTime + " and endTime>" + currentTime, false);
		if(length>0){
			return true;
		}else{
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteData(String id){
		if(this.deleteById(id)){
			String sql="delete from gameStepDetail where gameStepID='"+id+"'";
			commonDao.execute(sql);
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> getTime(){
		List<Map<String, Object>> dataList=new ArrayList();
		String[] properties={"me_endTime","mo_codeTableName","me_gameStepID"};
		List<Map<String, Object>> data=this.findMapByPropertiesQuick(properties, "true=true", true);
		Map<String, Object> map=new HashMap<String, Object>();
		for(int i=0;i<data.size();i++){
			if("创意阶段".equals(data.get(i).get("mo_codeTableName"))){
				
				map.put("Time1", data.get(i).get("me_endTime").toString());
				String[] proper={"gs_endTime","me_gameStepID"};
				List<Map<String, Object>> newdata=this.findMapByPropertiesQuick(proper, "me.gameStepID='"+data.get(i).get("me_gameStepID")+"'", true);
				if(newdata.size()>0){
					map.put("Time2", newdata.get(0).get("gs_endTime"));
				}
			}
			else if("作品阶段".equals(data.get(i).get("mo_codeTableName"))){
				map.put("Time3", data.get(i).get("me_endTime").toString());
				String[] proper={"gs_endTime","me_gameStepID"};
				List<Map<String, Object>> newdata=this.findMapByPropertiesQuick(proper, "me.gameStepID='"+data.get(i).get("me_gameStepID")+"'", true);
				if(newdata.size()>0){
					map.put("Time4", newdata.get(0).get("gs_endTime"));
				}
			}
		}
		dataList.add(map);
		return dataList;
	}
}
