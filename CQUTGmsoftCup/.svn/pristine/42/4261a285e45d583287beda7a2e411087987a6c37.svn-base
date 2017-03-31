package com.cqut.util;

public class ServiceLoader {
	private String serviceFilesStr="";//拼接service引入
	public ServiceLoader(String name){
		String[] splitNames=name.split(",");
		for(int i=0;i<splitNames.length;i++){
			serviceFilesStr+="<script language='JavaScript' type='text/javascript' src='dwr/interface/"+splitNames[i]+".js'></script>";
		}
	}
	public String getServiceFilesStr() {
		return serviceFilesStr;
	}
	public void setServiceFilesStr(String serviceFilesStr) {
		this.serviceFilesStr = serviceFilesStr;
	}

}
