package com.cqut.service.standardVersion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.standardVersion.customInterface.IStandardVersionEntityDao;
import com.cqut.dao.standardVersion.customInterface.IStandardVersionMapDao;
import com.cqut.entity.standardVersion.StandardVersion;

import com.cqut.service.standardVersion.customInterface.IStandardVersionService;

@Controller  
@RemoteProxy
public class StandardVersionService implements IStandardVersionService {

	@Resource(name = "standardVersionMapDao")
	private IStandardVersionMapDao mapDao;
	@Resource(name = "standardVersionEntityDao")
	private IStandardVersionEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findStandardVersions(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findStandardVersions(properties, condition, sortField, order, needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findProperties() {
		List<Map<String, Object>> data = mapDao.findStandardVersions(new String[]{"standardVersionID","standardVersionName"}, "", "", "", false, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findStandardVersions(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getStandardVersion(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findStandardVersions(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public StandardVersion getStandardVersionEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findStandardVersions(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new StandardVersion(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<StandardVersion> findStandardVersionByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findStandardVersions(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(StandardVersion.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(StandardVersion value) {
		return deleteById(value.getStandardVersionID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(StandardVersion.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(StandardVersion[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (StandardVersion item : values) {
			ids[index++] = item.getStandardVersionID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new StandardVersion(value));
	}

	@RemoteMethod
	public boolean saveEntity(StandardVersion value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new StandardVersion(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(StandardVersion value) {
		StandardVersion standardVersion = (StandardVersion) commonDao.merge(value);
		if (standardVersion != null) {
			return standardVersion.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(StandardVersion data, String condition) {
		if(mapDao.updateStandardVersion(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
