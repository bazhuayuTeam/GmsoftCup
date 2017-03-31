package com.cqut.service.expertScore;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.expertScore.customInterface.IExpertScoreEntityDao;
import com.cqut.dao.expertScore.customInterface.IExpertScoreMapDao;
import com.cqut.entity.expertScore.ExpertScore;

import com.cqut.service.expertScore.customInterface.IExpertScoreService;

@Controller  
@RemoteProxy
public class ExpertScoreService implements IExpertScoreService {

	@Resource(name = "expertScoreMapDao")
	private IExpertScoreMapDao mapDao;
	@Resource(name = "expertScoreEntityDao")
	private IExpertScoreEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findExpertScores(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExpertScores(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExpertScores(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getExpertScore(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findExpertScores(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public ExpertScore getExpertScoreEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findExpertScores(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new ExpertScore(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<ExpertScore> findExpertScoreByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findExpertScores(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(ExpertScore.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(ExpertScore value) {
		return deleteById(value.getExpertScoreID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(ExpertScore.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(ExpertScore[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (ExpertScore item : values) {
			ids[index++] = item.getExpertScoreID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new ExpertScore(value));
	}

	@RemoteMethod
	public boolean saveEntity(ExpertScore value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new ExpertScore(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(ExpertScore value) {
		ExpertScore expertScore = (ExpertScore) commonDao.merge(value);
		if (expertScore != null) {
			return expertScore.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(ExpertScore data, String condition) {
		if(mapDao.updateExpertScore(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
