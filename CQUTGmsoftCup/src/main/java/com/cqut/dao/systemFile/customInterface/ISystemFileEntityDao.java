package com.cqut.dao.systemFile.customInterface;

import java.util.List;

import com.cqut.entity.systemFile.SystemFile;

public interface ISystemFileEntityDao {

	public List<SystemFile> findSystemFiles (String[] property,
			String condition, boolean needLink, int index, int limit);
	
	public String findFilePath(String id);
}
