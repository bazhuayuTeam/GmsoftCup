package com.cqut.service.followManager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.followManager.customInterface.IFollowManagerEntityDao;
import com.cqut.dao.followManager.customInterface.IFollowManagerMapDao;
import com.cqut.entity.followManager.FollowManager;

import com.cqut.service.followManager.customInterface.IFollowManagerService;

@Controller  
@RemoteProxy
public class FollowManagerService implements IFollowManagerService {

	@Resource(name = "followManagerMapDao")
	private IFollowManagerMapDao mapDao;
	@Resource(name = "followManagerEntityDao")
	private IFollowManagerEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findFollowManagers(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findFollowManagers(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findFollowManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getFollowManager(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findFollowManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public FollowManager getFollowManagerEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findFollowManagers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new FollowManager(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<FollowManager> findFollowManagerByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findFollowManagers(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(FollowManager.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(FollowManager value) {
		return deleteById(value.getFollowId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(FollowManager.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(FollowManager[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (FollowManager item : values) {
			ids[index++] = item.getFollowId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new FollowManager(value));
	}

	@RemoteMethod
	public boolean saveEntity(FollowManager value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new FollowManager(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(FollowManager value) {
		FollowManager followManager = (FollowManager) commonDao.merge(value);
		if (followManager != null) {
			return followManager.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(FollowManager data, String condition) {
		if(mapDao.updateFollowManager(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
