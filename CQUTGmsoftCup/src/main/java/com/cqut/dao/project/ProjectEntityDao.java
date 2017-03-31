package com.cqut.dao.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.project.Project;

import com.cqut.dao.project.customInterface.IProjectEntityDao;

@Component
public class ProjectEntityDao extends BaseDao implements IProjectEntityDao {

	@Override
	public Class<?> getEntity() {
		return Project.class;
	}
	
	public List<Project> findProjects(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Project> results = new ArrayList<Project>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Project(item));
		}
		return results;
	}

}
