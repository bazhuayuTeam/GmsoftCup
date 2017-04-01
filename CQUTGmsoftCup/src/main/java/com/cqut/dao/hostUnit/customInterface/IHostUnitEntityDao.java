package com.cqut.dao.hostUnit.customInterface;

import java.util.List;

import com.cqut.entity.hostUnit.HostUnit;

public interface IHostUnitEntityDao {

	public List<HostUnit> findHostUnits (String[] property,
			String condition, boolean needLink, int index, int limit);
}
