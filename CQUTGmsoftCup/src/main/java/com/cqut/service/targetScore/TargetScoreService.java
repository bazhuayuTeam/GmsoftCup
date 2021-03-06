package com.cqut.service.targetScore;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.targetScore.customInterface.ITargetScoreEntityDao;
import com.cqut.dao.targetScore.customInterface.ITargetScoreMapDao;
import com.cqut.entity.targetScore.TargetScore;
import com.cqut.service.targetScore.customInterface.ITargetScoreService;
import com.cqut.service.team.TeamService;

@Controller  
@RemoteProxy
public class TargetScoreService implements ITargetScoreService {
	@Resource(name = "targetScoreMapDao")
	private ITargetScoreMapDao mapDao;
	@Resource(name = "targetScoreEntityDao")
	private ITargetScoreEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "teamService")
	private TeamService teamService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTargetScores(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetScores(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetScores(properties, condition, "", "", needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getTargetScore(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findTargetScores(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public TargetScore getTargetScoreEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findTargetScores(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new TargetScore(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<TargetScore> findTargetScoreByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findTargetScores(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(TargetScore.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(TargetScore value) {
		return deleteById(value.getTargetScoreID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(TargetScore.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(TargetScore[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (TargetScore item : values) {
			ids[index++] = item.getTargetScoreID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new TargetScore(value));
	}
	
	@RemoteMethod
	public boolean saveScore(String expertTargetDistributeID,String targetCode,String score) {
		TargetScore targetScore = new TargetScore();
		targetScore.setExpertTargetDistributeID(expertTargetDistributeID);
		targetScore.setTargetCode(targetCode);
		targetScore.setScore(Integer.parseInt(score));
		return saveEntity(targetScore);
	}

	@RemoteMethod
	public boolean saveEntity(TargetScore value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new TargetScore(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(TargetScore value) {
		TargetScore targetScore = (TargetScore) commonDao.merge(value);
		if (targetScore != null) {
			return targetScore.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateScore(String expertTargetDistributeID,String targetCode,String score, String condition) {
		TargetScore targetScore = new TargetScore();
		targetScore.setExpertTargetDistributeID(expertTargetDistributeID);
		targetScore.setTargetCode(targetCode);
		targetScore.setScore(Integer.parseInt(score));
		return updateEntity(targetScore, "TargetScoreID='"+condition+"'");
	}
	
	@RemoteMethod
	public boolean updateEntity(TargetScore data, String condition) {
		if(mapDao.updateTargetScore(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> getOthers(String gameStepDetailID,String targetCode) {
		List<Map<String, Object>> list = findMapByProperties(new String[]{"Score","te_teamID"},"targetCode='"+targetCode+"' and te.gameStepDetailID='"+gameStepDetailID+"'","Score","desc",true);
		int linking = 1;
		int linkScore = -1;
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String teamId = map.get("te_teamID") + "";
			int score = Integer.parseInt(map.get("Score") + "");
			if(i == 0){
				linkScore = score;
			}
			if(score == linkScore){
				list.get(i).put("link", linking);
			}else{
				linkScore = score;
				list.get(i).put("link", ++linking);
			}
			Map<String, Object> info = teamService.getTeam(new String[]{"teamName","na_name"}, "team.teamId='"+teamId+"'", true);
			if(info!=null){
				list.get(i).put("teamName", info.get("teamName"));
				list.get(i).put("name", info.get("na_name"));
			}
		}
		return list;
	}
}
