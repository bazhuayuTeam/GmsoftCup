package com.cqut.service.comParam;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.comParam.customInterface.IComParamEntityDao;
import com.cqut.dao.comParam.customInterface.IComParamMapDao;
import com.cqut.entity.comParam.ComParam;

import com.cqut.service.comParam.customInterface.IComParamService;

@Controller  
@RemoteProxy
public class ComParamService implements IComParamService {

	@Resource(name = "comParamMapDao")
	private IComParamMapDao mapDao;
	@Resource(name = "comParamEntityDao")
	private IComParamEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findComParams(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findComParams(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findComParams(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getComParam(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findComParams(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public ComParam getComParamEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findComParams(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new ComParam(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<ComParam> findComParamByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findComParams(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(ComParam.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(ComParam value) {
		return deleteById(value.getComParamId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(ComParam.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(ComParam[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (ComParam item : values) {
			ids[index++] = item.getComParamId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new ComParam(value));
	}

	@RemoteMethod
	public boolean saveEntity(ComParam value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new ComParam(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(ComParam value) {
		ComParam comParam = (ComParam) commonDao.merge(value);
		if (comParam != null) {
			return comParam.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(ComParam data, String condition) {
		if(mapDao.updateComParam(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
