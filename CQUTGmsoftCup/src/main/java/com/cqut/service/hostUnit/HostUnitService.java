package com.cqut.service.hostUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.hostUnit.customInterface.IHostUnitEntityDao;
import com.cqut.dao.hostUnit.customInterface.IHostUnitMapDao;
import com.cqut.entity.hostUnit.HostUnit;
import com.cqut.service.hostUnit.customInterface.IHostUnitService;

@Controller  
@RemoteProxy
public class HostUnitService implements IHostUnitService {

	@Resource(name = "hostUnitMapDao")
	private IHostUnitMapDao mapDao;
	@Resource(name = "hostUnitEntityDao")
	private IHostUnitEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findHostUnits(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findHostUnits(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findHostUnits(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getHostUnit(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findHostUnits(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public HostUnit getHostUnitEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findHostUnits(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new HostUnit(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<HostUnit> findHostUnitByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findHostUnits(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(HostUnit.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(HostUnit value) {
		return deleteById(value.getHostUnitId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(HostUnit.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(HostUnit[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (HostUnit item : values) {
			ids[index++] = item.getHostUnitId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new HostUnit(value));
	}

	@RemoteMethod
	public boolean saveEntity(HostUnit value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new HostUnit(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(HostUnit value) {
		HostUnit hostUnit = (HostUnit) commonDao.merge(value);
		if (hostUnit != null) {
			return hostUnit.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(HostUnit data, String condition) {
		if(mapDao.updateHostUnit(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveData(Map<String,String> data) {
		String[] hostUnits= data.get("hostUnitName").split(",");
		String[] secondUnits= data.get("secondUnitName").split(",");
		String gameID=data.get("gameId");
		for(int i=0,len=hostUnits.length;i<len;i++){
			HostUnit hostUnit=new HostUnit();
			hostUnit.setGameId(gameID);
			hostUnit.setHostUnitName(hostUnits[i]);
			hostUnit.setType("0");
			saveEntity(hostUnit);
		}
		for(int i=0,len=secondUnits.length;i<len;i++){
			HostUnit hostUnit=new HostUnit();
			hostUnit.setGameId(gameID);
			hostUnit.setHostUnitName(secondUnits[i]);
			hostUnit.setType("1");
			saveEntity(hostUnit);
		}
		return true;
	}
	
	@RemoteMethod
	public boolean updateData(Map<String,String> data) {
		String[] hostUnits= data.get("hostUnitName").split(",");
		String[] secondUnits= data.get("secondUnitName").split(",");
		String gameID=data.get("gameId");
		List<Map<String,Object>> hostUnitIDs=findMapByPropertiesQuick(new String[]{"hostUnitId"},"gameId='"+gameID+"'",false);
		String[] removeIDs=new String[hostUnitIDs.size()];
		for(int i=0,len=hostUnitIDs.size();i<len;i++){
			removeIDs[i]=hostUnitIDs.get(i).get("hostUnitId").toString();
		}
		deleteByIds(removeIDs);
		for(int i=0,len=hostUnits.length;i<len;i++){
			HostUnit hostUnit=new HostUnit();
			hostUnit.setGameId(gameID);
			hostUnit.setHostUnitName(hostUnits[i]);
			hostUnit.setType("0");
			saveEntity(hostUnit);
		}
		for(int i=0,len=secondUnits.length;i<len;i++){
			HostUnit hostUnit=new HostUnit();
			hostUnit.setGameId(gameID);
			hostUnit.setHostUnitName(secondUnits[i]);
			hostUnit.setType("1");
			saveEntity(hostUnit);
		}
		return true;
	}
	
}
