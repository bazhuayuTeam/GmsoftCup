package com.cqut.dao.module;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.module.Module;

import com.cqut.dao.module.customInterface.IModuleMapDao;

@Component
public class ModuleMapDao extends BaseDao implements IModuleMapDao {

	
	public Class<?> getEntity() {
		return Module.class;
	}

	public List<Map<String, Object>> findModules(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateModule(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}