package com.cqut.service.academic;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.academic.customInterface.IAcademicEntityDao;
import com.cqut.dao.academic.customInterface.IAcademicMapDao;
import com.cqut.entity.academic.Academic;

import com.cqut.service.academic.customInterface.IAcademicService;

@Controller  
@RemoteProxy
public class AcademicService implements IAcademicService {

	@Resource(name = "academicMapDao")
	private IAcademicMapDao mapDao;
	@Resource(name = "academicEntityDao")
	private IAcademicEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findAcademics(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findAcademics(properties, condition, sortField, order, needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findByProperties(String type) {
		String condition = "";
		if(type.equals("0")){
			condition = "academicType = 'BMType0001'";
		}
		
		List<Map<String, Object>> data = mapDao.findAcademics(new String[]{"academicID","academicName"}, condition, "", "", false, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findAcademics(properties, condition, "", "", needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuickNew(String[] properties,
			String condition,String sortField, String order, boolean needLink) {
		String con="ACADEMICID IN (SELECT DISTINCT	AC.ACADEMICID	FROM TaskDetail,	discipline dis,"
			             +"Academic ac,EXPERTTARGETDISTRIBUTE ex	WHERE	taskDetail.majorID = dis.disciplineID AND AC.ACADEMICID = DIS.ACADEMICID and "
			             +"  ex.taskDetailID=taskDetail.taskDetailID and ";
		con+=condition+")";
		List<Map<String, Object>> data = mapDao.findAcademics(properties, con, sortField, order, needLink, -1, -1);		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getAcademic(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findAcademics(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Academic getAcademicEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findAcademics(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Academic(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Academic> findAcademicByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findAcademics(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Academic.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Academic value) {
		return deleteById(value.getAcademicID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Academic.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Academic[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Academic item : values) {
			ids[index++] = item.getAcademicID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Academic(value));
	}

	@RemoteMethod
	public boolean saveEntity(Academic value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Academic(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Academic value) {
		Academic academic = (Academic) commonDao.merge(value);
		if (academic != null) {
			return academic.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Academic data, String condition) {
		if(mapDao.updateAcademic(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
