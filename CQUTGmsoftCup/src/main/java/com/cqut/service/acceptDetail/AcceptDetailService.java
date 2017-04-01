package com.cqut.service.acceptDetail;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.acceptDetail.customInterface.IAcceptDetailEntityDao;
import com.cqut.dao.acceptDetail.customInterface.IAcceptDetailMapDao;
import com.cqut.entity.acceptDetail.AcceptDetail;

import com.cqut.service.acceptDetail.customInterface.IAcceptDetailService;

@Controller  
@RemoteProxy
public class AcceptDetailService implements IAcceptDetailService {

	@Resource(name = "acceptDetailMapDao")
	private IAcceptDetailMapDao mapDao;
	@Resource(name = "acceptDetailEntityDao")
	private IAcceptDetailEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findAcceptDetails(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findAcceptDetails(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findAcceptDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getAcceptDetail(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findAcceptDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public AcceptDetail getAcceptDetailEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findAcceptDetails(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new AcceptDetail(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<AcceptDetail> findAcceptDetailByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findAcceptDetails(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(AcceptDetail.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(AcceptDetail value) {
		return deleteById(value.getAcceptId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(AcceptDetail.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(AcceptDetail[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (AcceptDetail item : values) {
			ids[index++] = item.getAcceptId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new AcceptDetail(value));
	}

	@RemoteMethod
	public boolean saveEntity(AcceptDetail value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new AcceptDetail(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(AcceptDetail value) {
		AcceptDetail acceptDetail = (AcceptDetail) commonDao.merge(value);
		if (acceptDetail != null) {
			return acceptDetail.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(AcceptDetail data, String condition) {
		if(mapDao.updateAcceptDetail(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
