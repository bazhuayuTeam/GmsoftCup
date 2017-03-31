package com.cqut.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.cqut.dao.BaseViewDao;

@Component
public class CommonViewDao extends BaseViewDao {
    String viewName ;
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public String getEntity() {
		return viewName;
	}

	public List<Map<String, Object>> findView(String viewName,String[] property,
			String condition, String sortField, String order, boolean needLink,
			int index, int limit) {
		setViewName(viewName);
		return get(property, condition, sortField, order, needLink, index, limit);
	}

	public int findCount(String viewName, String[] properties, String condition, boolean needLink) {
		setViewName(viewName);
		return getCount(properties, condition, needLink);
	}
	

	@Override
	public int updateProperties(Map<String, Object> data, String condition) {
		// TODO Auto-generated method stub
		return 0;
	}

}