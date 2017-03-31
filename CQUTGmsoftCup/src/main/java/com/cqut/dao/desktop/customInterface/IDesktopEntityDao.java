package com.cqut.dao.desktop.customInterface;

import java.util.List;

import com.cqut.entity.desktop.Desktop;

public interface IDesktopEntityDao {

	public List<Desktop> findDesktops (String[] property,
			String condition, boolean needLink, int index, int limit);
}
