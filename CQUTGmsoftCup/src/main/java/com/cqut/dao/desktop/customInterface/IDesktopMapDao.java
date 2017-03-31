package com.cqut.dao.desktop.customInterface;

import java.util.List;
import java.util.Map;

public interface IDesktopMapDao {

	public List<Map<String, Object>> findDesktops(String[] property,
			String condition, String sortField, String order, 
			boolean needLink, int index, int limit);
	
	public int findCount(String[] properties, String condition, boolean needLink);
	
	public int updateDesktop(Map<String, Object> properties, String condition);
}
