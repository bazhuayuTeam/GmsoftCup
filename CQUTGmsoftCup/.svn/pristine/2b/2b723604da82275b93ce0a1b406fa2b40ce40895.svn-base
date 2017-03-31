package com.cqut.dao.standardVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.standardVersion.StandardVersion;

import com.cqut.dao.standardVersion.customInterface.IStandardVersionEntityDao;

@Component
public class StandardVersionEntityDao extends BaseDao implements IStandardVersionEntityDao {

	@Override
	public Class<?> getEntity() {
		return StandardVersion.class;
	}
	
	public List<StandardVersion> findStandardVersions(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<StandardVersion> results = new ArrayList<StandardVersion>(list.size());
		for(Map<String, Object> item : list){
			results.add(new StandardVersion(item));
		}
		return results;
	}

}
