package com.cqut.service.targetSystem;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.targetSystem.customInterface.ITargetSystemEntityDao;
import com.cqut.dao.targetSystem.customInterface.ITargetSystemMapDao;

import com.cqut.entity.targetSystem.Standardversion;
import com.cqut.service.target.customInterface.ITargetService;
import com.cqut.service.targetSystem.customInterface.ITargetSystemService;

@Controller
@RemoteProxy
public class TargetSystemService implements ITargetSystemService {

	@Resource(name = "targetSystemMapDao")
	private ITargetSystemMapDao mapDao;
	@Resource(name = "targetSystemEntityDao")
	private ITargetSystemEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "targetService")
	private ITargetService targetService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(
			String[] properties, String condition, String sortField,
			String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findTargetSystems(properties,
				condition, sortField, order, needLink, ((curPage - 1) * limit),
				limit);

		return data;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetSystems(properties,
				condition, sortField, order, needLink, -1, -1);

		return data;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(
			String[] properties, String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetSystems(properties,
				condition, "", "", needLink, -1, -1);

		return data;
	}

	@RemoteMethod
	public Map<String, Object> getTargetSystem(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetSystems(properties,
				condition, "", "", needLink, -1, -1);

		return data != null && data.size() > 0 ? data.get(0) : null;
	}

	@RemoteMethod
	public Standardversion getTargetSystemEntity(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findTargetSystems(properties,
				condition, "", "", needLink, -1, -1);

		return data != null && data.size() > 0 ? new Standardversion(data
				.get(0)) : null;
	}

	@RemoteMethod
	public int findCountByProperties(String[] properties, String condition,
			boolean needLink) {
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}

	public List<Standardversion> findTargetSystemByProperties(
			String[] properties, String condition, boolean needLink, int index,
			int limit) {
		return entityDao.findTargetSystems(properties, condition, needLink,
				index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Standardversion.class, id);
			List<Map<String, Object>> targets = targetService.findMapByPropertiesQuick(new String[]{"targetCode"}, "standardVersionID='" + id + "'", false);
			int size = targets.size();
			if(size > 0){
				String[] ids = new String[size];
				for(int i = 0; i < ids.length; i++){
					ids[i] = targets.get(i).get("targetCode").toString();
				}
				targetService.deleteByIds(ids);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntity(Standardversion value) {
		return deleteById(value.getStandardVersionID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Standardversion.class, ids);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Standardversion[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Standardversion item : values) {
			ids[index++] = item.getStandardVersionID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Standardversion(value));
	}

	@RemoteMethod
	public boolean saveStandard(String standardVersionName,
			String standardVersionB,String createrId) {
		if ("".equals(standardVersionB) || standardVersionB == null) {
			Standardversion s = new Standardversion();
			s.setStandardVersionName(standardVersionName);
			s.setCiteState("0");
			s.setCreaterId(createrId);
			s.setCreaterTime((new Date().getTime())+"");
			return saveEntity(s);
		} else {
			Standardversion s = new Standardversion();
			s.setStandardVersionName(standardVersionName);
			s.setCiteState("0");
			String id = saveAndReturn(s).get("standardVersionID").toString();
			String condition = "standardVersionID='" + standardVersionB + "'";
			String[] properties = new String[]{"targetCode","isLastTarget","standardVersionID","targetExplain","targetLevel","targetName","targetParentCode","targetScore"};
			List<Map<String, Object>> targets = targetService.findMapByPropertiesQuick(properties, condition, false);
			for(Map<String,Object> target : targets){
				target.put("standardVersionID", id);
				String tt = target.get("targetCode").toString().replaceFirst(standardVersionB, id);
				target.put("targetCode", tt);
				try{
					target.put("targetParentCode", target.get("targetParentCode").toString().replaceFirst(standardVersionB, id));
				}catch(Exception e){
					target.put("targetParentCode", "");
				}
				targetService.save(target);
			}
			return true;
		}
	}

	@RemoteMethod
	public boolean saveEntity(Standardversion value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Standardversion(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Standardversion value) {
		Standardversion targetSystem = (Standardversion) commonDao.merge(value);
		if (targetSystem != null) {
			return targetSystem.getProperties();
		}
		return null;
	}

	@RemoteMethod
	public boolean updateStandard(String name, String id){
		Standardversion s = new Standardversion();
		s.setStandardVersionName(name);
		return updateEntity(s, "standardVersionID='" +id + "'");
	}
	@RemoteMethod
	public boolean updateEntity(Standardversion data, String condition) {
		if (mapDao.updateTargetSystem(data.getProperties(), condition) == 1) {
			return true;
		}
		return false;
	}

	@RemoteMethod
	public boolean deleteTargetSys(String targetSystemID) {
		if (targetSystemID != null && targetSystemID != "") {
			boolean deleteTargetSys = this.deleteById(targetSystemID);
			if (deleteTargetSys) {
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean saveStandard(String standardVersionId,
			String standardVersionB) {
		// TODO Auto-generated method stub
		return false;
	}
}
