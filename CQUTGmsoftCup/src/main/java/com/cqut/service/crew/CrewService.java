package com.cqut.service.crew;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.service.operator.OperatorService;
import com.cqut.service.team.TeamService;
import com.cqut.service.user.UserService;
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
	@Resource(name = "operatorService")
	private OperatorService operatorService;
	@Resource(name = "teamService")
	private TeamService teamService;
	@Resource(name = "userService")
	private UserService userService;

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
	public boolean saveData(Crew value,String gameId){
		String teamId=value.getTeamId();
		String userId=value.getUserId();
		if(userService.findUser(userId, "0").size()!=0&&userService.findUserNew(userId, gameId, teamId).size()==0&&this.getNumber(teamId)){
			if(!saveEntity(value)){
				return false;
			}
		}
		return true;
	}
	
	@RemoteMethod
	public boolean saveTeacher(Crew value){
		String teamId=value.getTeamId();
		String userId=value.getUserId();
		if(userService.findUser(userId, "2").size()!=0&&userService.findUserTeacher(userId,teamId).size()==0){
			if(!saveEntity(value)){
				return false;
			}
		}
		return true;
	}
	
	@RemoteMethod
	public boolean saveEntity(Crew value) {
		if(null==value.getCrewID()||value.getCrewID().equals("")){
			value.setCrewID(operatorService.getID());
		}
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
	
	@RemoteMethod
	public List<Map<String,Object>> getCrewNew(String teamId){
		String[] properties={"me_crewId","cr_name","cr_userId","me_teamId","me_type","cr_phone","cr_email","cr_QQ","ac_academicName"};
		List<Map<String,Object>> data=this.findMapByPropertiesQuick(properties, "me.teamId='"+teamId+"' order by me.type asc", true);
		return data;
	}
	 
	
	@RemoteMethod
	public boolean getNumber(String teamId){
		String[] properties={"crewId"};
		int number=this.findCountByProperties(properties, "teamId='"+teamId+"' and type!='2'", false);
		if(number==5){
			return false;
		}
		return true;
	}
}
