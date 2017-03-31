package com.cqut.dao.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.module.Module;

import com.cqut.dao.module.customInterface.IModuleEntityDao;

@Component
public class ModuleEntityDao extends BaseDao implements IModuleEntityDao {

	@Override
	public Class<?> getEntity() {
		return Module.class;
	}
	
	public List<Module> findModules(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Module> results = new ArrayList<Module>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Module(item));
		}
		return results;
	}

}
