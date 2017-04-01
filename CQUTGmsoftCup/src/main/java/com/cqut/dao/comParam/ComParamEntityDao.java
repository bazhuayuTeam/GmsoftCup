package com.cqut.dao.comParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.comParam.ComParam;

import com.cqut.dao.comParam.customInterface.IComParamEntityDao;

@Component
public class ComParamEntityDao extends BaseDao implements IComParamEntityDao {

	@Override
	public Class<?> getEntity() {
		return ComParam.class;
	}
	
	public List<ComParam> findComParams(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<ComParam> results = new ArrayList<ComParam>(list.size());
		for(Map<String, Object> item : list){
			results.add(new ComParam(item));
		}
		return results;
	}

}
