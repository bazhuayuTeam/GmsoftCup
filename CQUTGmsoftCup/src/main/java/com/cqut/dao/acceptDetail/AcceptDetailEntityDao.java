package com.cqut.dao.acceptDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.acceptDetail.AcceptDetail;

import com.cqut.dao.acceptDetail.customInterface.IAcceptDetailEntityDao;

@Component
public class AcceptDetailEntityDao extends BaseDao implements IAcceptDetailEntityDao {

	@Override
	public Class<?> getEntity() {
		return AcceptDetail.class;
	}
	
	public List<AcceptDetail> findAcceptDetails(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<AcceptDetail> results = new ArrayList<AcceptDetail>(list.size());
		for(Map<String, Object> item : list){
			results.add(new AcceptDetail(item));
		}
		return results;
	}

}
