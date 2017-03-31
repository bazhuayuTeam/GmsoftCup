package com.cqut.service.discipline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.discipline.customInterface.IDisciplineEntityDao;
import com.cqut.dao.discipline.customInterface.IDisciplineMapDao;
import com.cqut.entity.discipline.Discipline;
import com.cqut.service.discipline.customInterface.IDisciplineService;

@Controller  
@RemoteProxy
public class DisciplineService implements IDisciplineService {

	@Resource(name = "disciplineMapDao")
	private IDisciplineMapDao mapDao;
	@Resource(name = "disciplineEntityDao")
	private IDisciplineEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, sortField, order, needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findByProperties(String properties) {
		List<Map<String, Object>> data = mapDao.findDisciplines(new String[]{"disciplineID","disciplineName"}, " academicID = '"+properties+"' ", "", "", false, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, "", "", needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getDiscipline(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Discipline getDisciplineEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Discipline(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Discipline> findDisciplineByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findDisciplines(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Discipline.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Discipline value) {
		return deleteById(value.getDisciplineID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Discipline.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Discipline[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Discipline item : values) {
			ids[index++] = item.getDisciplineID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Discipline(value));
	}

	@RemoteMethod
	public boolean saveEntity(Discipline value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Discipline(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Discipline value) {
		Discipline discipline = (Discipline) commonDao.merge(value);
		if (discipline != null) {
			return discipline.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Discipline data, String condition) {
		if(mapDao.updateDiscipline(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findDisciplines(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	//-----------------展示专业分页--------------yuanding---------------------------------------------------
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesLimitForPage(String[] properties,
			String condition, boolean needLink, int index, int limit,String[] groupByName){
		String sql="SELECT * FROM (SELECT A .*, ROWNUM re FROM (SELECT DISTINCT ";
		int beginNumber=1+(index-1)*limit;
		int endNumber=limit*index;
		
		for(int i=0;i<properties.length;i++){
			if(i!=properties.length-1){
				sql+=properties[i]+",";
			}else{
				sql+=properties[i];
			}
		}
		
		sql+=" FROM Academic aca, task ta,TaskDetail taskd,Discipline where"
				+ " Discipline.academicID = aca.academicID AND "
				+ "taskd.taskID = ta.taskID AND "
				+ "Discipline.disciplineID = taskd.majorID";
		
		sql+=condition;
		
		sql+=" GROUP BY ";
		for(int j=0;j<groupByName.length;j++){
			if(j!=groupByName.length-1){
				sql+=groupByName[j]+",";
			}else{
				sql+=groupByName[j];
			}
		}
		
		sql+=" ORDER BY aca.academicShort DESC ) A ) WHERE"
				+ " re >= "+beginNumber+" AND "
				+ "re <= "+endNumber+"";
		List<Object> list = this.commonDao.executeAndReturn(sql);
//		System.out.println(sortData(list));
		return sortData(list);
	}
	
	//对于数据的处理
	private List<Map<String, Object>> sortData(List<Object> data){
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		for(int i=0;i<data.size();i++){
			Object[] lists = (Object[]) data.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("disciplineName", lists[0]);
			map.put("disciplineID", lists[1]);
			map.put("aca_academicID", lists[2]);
			map.put("aca_academicShort", lists[3]);
			map.put("ta_taskID", lists[4]);
			result.add(map);
		}
		
		return result;
	}
	
	//----------------------------------end---------------------------------------
}
