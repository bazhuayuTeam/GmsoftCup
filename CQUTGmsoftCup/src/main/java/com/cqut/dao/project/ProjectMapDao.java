package com.cqut.dao.project;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.project.Project;

import com.cqut.dao.project.customInterface.IProjectMapDao;

@Component
public class ProjectMapDao extends BaseDao implements IProjectMapDao {

	
	public Class<?> getEntity() {
		return Project.class;
	}

	public List<Map<String, Object>> findProjects(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateProject(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}