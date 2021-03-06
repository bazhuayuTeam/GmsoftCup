package com.cqut.service.expertTargetDistribute;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.expertTargetDistribute.customInterface.IExpertTargetDistributeEntityDao;
import com.cqut.dao.expertTargetDistribute.customInterface.IExpertTargetDistributeMapDao;
import com.cqut.entity.expertTargetDistribute.ExpertTargetDistribute;

import com.cqut.service.expertTargetDistribute.customInterface.IExpertTargetDistributeService;

@Controller  
@RemoteProxy
public class ExpertTargetDistributeService implements IExpertTargetDistributeService {

	@Resource(name = "expertTargetDistributeMapDao")
	private IExpertTargetDistributeMapDao mapDao;
	@Resource(name = "expertTargetDistributeEntityDao")
	private IExpertTargetDistributeEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getExpertTargetDistribute(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	@RemoteMethod
	public Map<String, Object> getData(String teamID,String gameStepDetail){
		List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(new String[]{"expertTargetDistributeID"}, "teamID='"+teamID+"' and gameStepDetailid='"+gameStepDetail+"'", "", "", false, -1, -1);
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public ExpertTargetDistribute getExpertTargetDistributeEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findExpertTargetDistributes(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new ExpertTargetDistribute(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<ExpertTargetDistribute> findExpertTargetDistributeByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findExpertTargetDistributes(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(ExpertTargetDistribute.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(ExpertTargetDistribute value) {
		return deleteById(value.getExpertTargetDistributeID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(ExpertTargetDistribute.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(ExpertTargetDistribute[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (ExpertTargetDistribute item : values) {
			ids[index++] = item.getExpertTargetDistributeID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new ExpertTargetDistribute(value));
	}

	@RemoteMethod
	public boolean saveEntity(ExpertTargetDistribute value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new ExpertTargetDistribute(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(ExpertTargetDistribute value) {
		ExpertTargetDistribute expertTargetDistribute = (ExpertTargetDistribute) commonDao.merge(value);
		if (expertTargetDistribute != null) {
			return expertTargetDistribute.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(ExpertTargetDistribute data, String condition) {
		if(mapDao.updateExpertTargetDistribute(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveData(String expert,String[] id,String taskid){
		for(int i=0;i<id.length;i++){
			String del="delete from ExpertTargetDistribute where teamID='"+id[i]+"' and gameStepDetailID='"+taskid+"'";
			commonDao.execute(del);
	
			if(null!=expert&&!expert.equals("")){
			String[] expertId=expert.split(",");
			for(int j=0;j<expertId.length;j++){
				ExpertTargetDistribute expertTargetDistribute=new ExpertTargetDistribute();
				expertTargetDistribute.setTeamID(id[i]);
				expertTargetDistribute.setGameStepDetailID(taskid);
				expertTargetDistribute.setExpertID(expertId[j]);
				if(!saveEntity(expertTargetDistribute)){
					return false;
				}
			}
			}
		}
		return true;
	}
}
