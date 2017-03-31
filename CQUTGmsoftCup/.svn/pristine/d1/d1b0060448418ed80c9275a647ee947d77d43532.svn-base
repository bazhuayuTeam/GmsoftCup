package com.cqut.dao.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cqut.dao.BaseDao;
import com.cqut.entity.desktop.Desktop;

import com.cqut.dao.desktop.customInterface.IDesktopEntityDao;

@Component
public class DesktopEntityDao extends BaseDao implements IDesktopEntityDao {

	@Override
	public Class<?> getEntity() {
		return Desktop.class;
	}
	
	public List<Desktop> findDesktops(String[] property, String condition,
			boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = get(property, condition, needLink, index, limit);
		List<Desktop> results = new ArrayList<Desktop>(list.size());
		for(Map<String, Object> item : list){
			results.add(new Desktop(item));
		}
		return results;
	}

}
