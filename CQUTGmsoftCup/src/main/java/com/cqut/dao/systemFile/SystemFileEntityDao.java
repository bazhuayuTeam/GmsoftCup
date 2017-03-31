package com.cqut.dao.systemFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.systemFile.SystemFile;

import com.cqut.dao.systemFile.customInterface.ISystemFileEntityDao;

@Component
public class SystemFileEntityDao extends BaseDao implements ISystemFileEntityDao {

	@Override
	public Class<?> getEntity() {
		return SystemFile.class;
	}
	
	public List<SystemFile> findSystemFiles(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<SystemFile> results = new ArrayList<SystemFile>(list.size());
		for(Map<String, Object> item : list){
			results.add(new SystemFile(item));
		}
		return results;
	}
	
	@Override
	public String findFilePath(String id) {
		List<Map<String, Object>> results = this.get(new String[]{"savePath"}, "id = '"+id+"'", true);
		if(results.size() == 0){
			return null;
		}
		Map<String, Object> result = results.get(0);
		if(result == null){
			return null;
		}
		Object filePath = result.get("savePath");
		return filePath == null ? null : filePath.toString();
	}

}
