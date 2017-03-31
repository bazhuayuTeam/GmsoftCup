package com.cqut.service.derive.deriveInterface;

import java.util.Map;

import org.directwebremoting.io.FileTransfer;

public interface IDeriveWordService {
	public FileTransfer deriveWord(String templateName,Map<String, Object> collection,String wordName);
}
