package com.cqut.service.problemManager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.problemManager.customInterface.IProblemManagerEntityDao;
import com.cqut.dao.problemManager.customInterface.IProblemManagerMapDao;
import com.cqut.entity.problemManager.ProblemManager;

import com.cqut.service.problemManager.customInterface.IProblemManagerService;

@Controller  
@RemoteProxy
public class ProblemManagerService implements IProblemManagerService {

	@Resource(name = "problemManagerMapDao")
	private IProblemManagerMapDao mapDao;
	@Resource(name = "problemManagerEntityDao")
	private IProblemManagerEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findProblemManagers(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProblemManagers(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProblemManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getProblemManager(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findProblemManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public ProblemManager getProblemManagerEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findProblemManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new ProblemManager(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<ProblemManager> findProblemManagerByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findProblemManagers(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(ProblemManager.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(ProblemManager value) {
		return deleteById(value.getProblemId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(ProblemManager.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(ProblemManager[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (ProblemManager item : values) {
			ids[index++] = item.getProblemId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new ProblemManager(value));
	}

	@RemoteMethod
	public boolean saveEntity(ProblemManager value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new ProblemManager(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(ProblemManager value) {
		ProblemManager problemManager = (ProblemManager) commonDao.merge(value);
		if (problemManager != null) {
			return problemManager.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(ProblemManager data, String condition) {
		if(mapDao.updateProblemManager(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
