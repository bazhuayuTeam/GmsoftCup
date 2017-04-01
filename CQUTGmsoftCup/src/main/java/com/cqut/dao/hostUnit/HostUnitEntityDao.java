package com.cqut.dao.hostUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.hostUnit.HostUnit;

import com.cqut.dao.hostUnit.customInterface.IHostUnitEntityDao;

@Component
public class HostUnitEntityDao extends BaseDao implements IHostUnitEntityDao {

	@Override
	public Class<?> getEntity() {
		return HostUnit.class;
	}
	
	public List<HostUnit> findHostUnits(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<HostUnit> results = new ArrayList<HostUnit>(list.size());
		for(Map<String, Object> item : list){
			results.add(new HostUnit(item));
		}
		return results;
	}

}
