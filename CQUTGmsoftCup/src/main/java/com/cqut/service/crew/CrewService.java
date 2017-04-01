package com.cqut.service.crew;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.crew.customInterface.ICrewEntityDao;
import com.cqut.dao.crew.customInterface.ICrewMapDao;
import com.cqut.entity.crew.Crew;

import com.cqut.service.crew.customInterface.ICrewService;

@Controller  
@RemoteProxy
public class CrewService implements ICrewService {

	@Resource(name = "crewMapDao")
	private ICrewMapDao mapDao;
	@Resource(name = "crewEntityDao")
	private ICrewEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findCrews(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findCrews(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findCrews(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getCrew(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findCrews(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Crew getCrewEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findCrews(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Crew(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Crew> findCrewByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findCrews(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Crew.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Crew value) {
		return deleteById(value.getCrewID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Crew.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Crew[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Crew item : values) {
			ids[index++] = item.getCrewID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Crew(value));
	}

	@RemoteMethod
	public boolean saveEntity(Crew value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Crew(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Crew value) {
		Crew crew = (Crew) commonDao.merge(value);
		if (crew != null) {
			return crew.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Crew data, String condition) {
		if(mapDao.updateCrew(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
