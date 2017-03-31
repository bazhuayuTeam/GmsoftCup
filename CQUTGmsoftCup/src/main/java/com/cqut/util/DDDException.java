package com.cqut.util;

import java.util.HashMap;
import java.util.Map;

import flex.messaging.messages.ErrorMessage;

public class DDDException extends RuntimeException {
    protected String code;
    protected Map extendedData;
    
	public DDDException(String code,String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public DDDException( String code,String message) {
		super(message);
		this.code = code;
	}

	public DDDException(String message, Throwable cause) {
		super(message, cause);
	}

	public DDDException(Throwable cause) {
		super(cause); 
	}

	public DDDException(String message) {
		super(message);
	}
	/**
	 * @return the extendedData
	 */
	public Map getExtendedData() {
		return extendedData;
		 
	}

	/**
	 * @param extendedData the extendedData to set
	 */
	public void setExtendedData(Map extendedData) {
		this.extendedData = extendedData;
	}
	/*
	 * 增加扩展数据
	 */
	public void putExtendedData(String key,Object value)
	{
		if(this.extendedData==null)
		{
			this.extendedData=new HashMap(); 
		}
		this.extendedData.put(key, value);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
