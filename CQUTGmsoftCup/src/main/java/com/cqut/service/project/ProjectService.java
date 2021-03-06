package com.cqut.service.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.project.customInterface.IProjectEntityDao;
import com.cqut.dao.project.customInterface.IProjectMapDao;
import com.cqut.entity.project.Project;

import com.cqut.service.operator.OperatorService;
import com.cqut.service.project.customInterface.IProjectService;

@Controller  
@RemoteProxy
public class ProjectService implements IProjectService {

	@Resource(name = "projectMapDao")
	private IProjectMapDao mapDao;
	@Resource(name = "projectEntityDao")
	private IProjectEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "operatorService")
	private OperatorService operatorService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findProjects(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProjects(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findProjects(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getFile(String teamId){
		List<Map<String, Object>> data = mapDao.findProjects(new String[]{"fileId","ps_fileName"}, "teamId='"+teamId+"'", "", "", true, -1, -1);
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	@RemoteMethod
	public Map<String, Object> getProject(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findProjects(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Project getProjectEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findProjects(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Project(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Project> findProjectByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findProjects(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Project.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Project value) {
		return deleteById(value.getProjectID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Project.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Project[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Project item : values) {
			ids[index++] = item.getProjectID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Project(value));
	}

	@RemoteMethod
	public boolean saveEntity(Project value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Project(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Project value) {
		Project project = (Project) commonDao.merge(value);
		if (project != null) {
			return project.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Project data, String condition) {
		if(mapDao.updateProject(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public boolean saveData(String teamId,String fileid,String type){
		String[] properties={"projectID"};
		Project project=new Project();
		
		project.setTeamId(teamId);
		project.setFileId(fileid);
		project.setType(type);
		List<Map<String, Object>> data=this.findMapByPropertiesQuick(properties, "teamId='"+teamId+"' and type='"+type+"'", false);
		if(data.size()==0){
			project.setProjectID(operatorService.getID());
			if(this.saveEntity(project)){
				return true;
			}
		}
		else{
			if(this.updateEntity(project, "projectId='"+data.get(0).get("projectID")+"'")){
				return true;
			}
		}
		return false;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> getData(String id,String type){
		String[] proper={"fileId"};
		List<Map<String, Object>> data=this.findMapByPropertiesQuick(proper, "teamId='"+id+"' and type='"+type+"'", false);
		return data;
	}
}
