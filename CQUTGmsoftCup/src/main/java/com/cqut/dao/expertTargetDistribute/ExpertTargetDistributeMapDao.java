package com.cqut.dao.expertTargetDistribute;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.expertTargetDistribute.ExpertTargetDistribute;

import com.cqut.dao.expertTargetDistribute.customInterface.IExpertTargetDistributeMapDao;

@Component
public class ExpertTargetDistributeMapDao extends BaseDao implements IExpertTargetDistributeMapDao {

	
	public Class<?> getEntity() {
		return ExpertTargetDistribute.class;
	}

	public List<Map<String, Object>> findExpertTargetDistributes(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateExpertTargetDistribute(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}