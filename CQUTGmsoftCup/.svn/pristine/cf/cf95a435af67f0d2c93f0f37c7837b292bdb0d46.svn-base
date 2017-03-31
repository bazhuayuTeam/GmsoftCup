package com.cqut.dao.desktop;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.desktop.Desktop;

import com.cqut.dao.desktop.customInterface.IDesktopMapDao;

@Component
public class DesktopMapDao extends BaseDao implements IDesktopMapDao {

	
	public Class<?> getEntity() {
		return Desktop.class;
	}

	public List<Map<String, Object>> findDesktops(String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	
	public int findCount(String[] properties, String condition, boolean needLink) {
		return getCount(properties, condition, needLink);
	}
	
	
	public int updateDesktop(Map<String, Object> properties, String condition) {
		return this.updateProperties(properties, condition);
	}

}