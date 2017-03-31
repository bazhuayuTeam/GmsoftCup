package com.cqut.dao.expertTargetDistribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expertTargetDistribute.ExpertTargetDistribute;

import com.cqut.dao.expertTargetDistribute.customInterface.IExpertTargetDistributeEntityDao;

@Component
public class ExpertTargetDistributeEntityDao extends BaseDao implements IExpertTargetDistributeEntityDao {

	@Override
	public Class<?> getEntity() {
		return ExpertTargetDistribute.class;
	}
	
	public List<ExpertTargetDistribute> findExpertTargetDistributes(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<ExpertTargetDistribute> results = new ArrayList<ExpertTargetDistribute>(list.size());
		for(Map<String, Object> item : list){
			results.add(new ExpertTargetDistribute(item));
		}
		return results;
	}

}
