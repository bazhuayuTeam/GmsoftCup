package com.cqut.dao.crew.customInterface;

import java.util.List;

import com.cqut.entity.crew.Crew;

public interface ICrewEntityDao {

	public List<Crew> findCrews (String[] property,
			String condition, boolean needLink, int index, int limit);
}
