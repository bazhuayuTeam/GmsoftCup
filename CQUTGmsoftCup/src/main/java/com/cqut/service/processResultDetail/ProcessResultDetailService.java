package com.cqut.service.processResultDetail;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.processResultDetail.customInterface.IProcessResultDetailEntityDao;
import com.cqut.dao.processResultDetail.customInterface.IProcessResultDetailMapDao;
import com.cqut.entity.processResultDetail.ProcessResultDetail;

import com.cqut.service.processResultDetail.customInterface.IProcessResultDetailService;

@Controller  
@RemoteProxy
public class ProcessResultDetailService implements IProcessResultDetailService {

	@Resource(name = "processResultDetailMapDao")
	private IProcessResultDetailMapDao mapDao;
	@Resource(name = "processResultDetailEntityDao")
	private IProcessResultDetailEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findProcessResultDetails(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProcessResultDetails(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProcessResultDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getProcessResultDetail(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findProcessResultDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public ProcessResultDetail getProcessResultDetailEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findProcessResultDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new ProcessResultDetail(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<ProcessResultDetail> findProcessResultDetailByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findProcessResultDetails(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(ProcessResultDetail.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(ProcessResultDetail value) {
		return deleteById(value.getProcessResultDetailID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(ProcessResultDetail.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(ProcessResultDetail[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (ProcessResultDetail item : values) {
			ids[index++] = item.getProcessResultDetailID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new ProcessResultDetail(value));
	}

	@RemoteMethod
	public boolean saveEntity(ProcessResultDetail value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new ProcessResultDetail(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(ProcessResultDetail value) {
		ProcessResultDetail processResultDetail = (ProcessResultDetail) commonDao.merge(value);
		if (processResultDetail != null) {
			return processResultDetail.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(ProcessResultDetail data, String condition) {
		if(mapDao.updateProcessResultDetail(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
