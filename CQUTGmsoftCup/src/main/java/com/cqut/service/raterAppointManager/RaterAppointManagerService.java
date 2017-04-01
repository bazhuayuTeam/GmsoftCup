package com.cqut.service.raterAppointManager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.raterAppointManager.customInterface.IRaterAppointManagerEntityDao;
import com.cqut.dao.raterAppointManager.customInterface.IRaterAppointManagerMapDao;
import com.cqut.entity.raterAppointManager.RaterAppointManager;

import com.cqut.service.raterAppointManager.customInterface.IRaterAppointManagerService;

@Controller  
@RemoteProxy
public class RaterAppointManagerService implements IRaterAppointManagerService {

	@Resource(name = "raterAppointManagerMapDao")
	private IRaterAppointManagerMapDao mapDao;
	@Resource(name = "raterAppointManagerEntityDao")
	private IRaterAppointManagerEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findRaterAppointManagers(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRaterAppointManagers(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRaterAppointManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getRaterAppointManager(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findRaterAppointManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public RaterAppointManager getRaterAppointManagerEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findRaterAppointManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new RaterAppointManager(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<RaterAppointManager> findRaterAppointManagerByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findRaterAppointManagers(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(RaterAppointManager.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(RaterAppointManager value) {
		return deleteById(value.getRaterAppointId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(RaterAppointManager.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(RaterAppointManager[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (RaterAppointManager item : values) {
			ids[index++] = item.getRaterAppointId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new RaterAppointManager(value));
	}

	@RemoteMethod
	public boolean saveEntity(RaterAppointManager value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new RaterAppointManager(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(RaterAppointManager value) {
		RaterAppointManager raterAppointManager = (RaterAppointManager) commonDao.merge(value);
		if (raterAppointManager != null) {
			return raterAppointManager.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(RaterAppointManager data, String condition) {
		if(mapDao.updateRaterAppointManager(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
