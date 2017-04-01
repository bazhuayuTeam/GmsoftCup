package com.cqut.dao.processResultDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.processResultDetail.ProcessResultDetail;

import com.cqut.dao.processResultDetail.customInterface.IProcessResultDetailEntityDao;

@Component
public class ProcessResultDetailEntityDao extends BaseDao implements IProcessResultDetailEntityDao {

	@Override
	public Class<?> getEntity() {
		return ProcessResultDetail.class;
	}
	
	public List<ProcessResultDetail> findProcessResultDetails(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<ProcessResultDetail> results = new ArrayList<ProcessResultDetail>(list.size());
		for(Map<String, Object> item : list){
			results.add(new ProcessResultDetail(item));
		}
		return results;
	}

}
