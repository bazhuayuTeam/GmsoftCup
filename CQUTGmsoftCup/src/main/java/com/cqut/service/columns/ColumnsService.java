package com.cqut.service.columns;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.columns.customInterface.IColumnsEntityDao;
import com.cqut.dao.columns.customInterface.IColumnsMapDao;
import com.cqut.entity.columns.Columns;

import com.cqut.service.columns.customInterface.IColumnsService;

@Controller  
@RemoteProxy
public class ColumnsService implements IColumnsService {

	@Resource(name = "columnsMapDao")
	private IColumnsMapDao mapDao;
	@Resource(name = "columnsEntityDao")
	private IColumnsEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findColumnss(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findColumnss(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findColumnss(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getColumns(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findColumnss(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Columns getColumnsEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findColumnss(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Columns(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Columns> findColumnsByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findColumnss(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Columns.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Columns value) {
		return deleteById(value.getColumnID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Columns.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Columns[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Columns item : values) {
			ids[index++] = item.getColumnID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Columns(value));
	}

	@RemoteMethod
	public boolean saveEntity(Columns value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Columns(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Columns value) {
		Columns columns = (Columns) commonDao.merge(value);
		if (columns != null) {
			return columns.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Columns data, String condition) {
		if(mapDao.updateColumns(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
