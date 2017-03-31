package com.cqut.util;

import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
@Controller
@RemoteProxy
public class WebUtil {
	/**
	 * 获取当前登录操作员的ID
	 * */
	@RemoteMethod
	public static String getOperatorId(HttpServletRequest request) {
		Object operatorId=request.getSession().getAttribute("operatorId");
		if(operatorId!=null){
			return operatorId.toString();
		}else{
			return "";
		}
		
	}
	
	/**
	 * 获取当前登录操作员的名称
	 * */
	@RemoteMethod
	public static String getOperatorName(HttpServletRequest request) {
		Object operatorName=request.getSession().getAttribute("operatorName");
		if(operatorName!=null){
			return operatorName.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登录操作员所属单位的ID
	 * */
	@RemoteMethod
	public static String getOrganizationCode(HttpServletRequest request) {
		Object organizationCode=request.getSession().getAttribute("organizationCode").toString();
		if(organizationCode!=null){
			return organizationCode.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登录操作员所属单位的Name
	 * */
	@RemoteMethod
	public static String getOrganizationName(HttpServletRequest request) {
		Object organizationName=request.getSession().getAttribute("organizationName").toString();
		if(organizationName!=null){
			return organizationName.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登录员所属单位类别 小学、 点小、初中、高中
	 * */
	@RemoteMethod
	public static String getBussinessType(HttpServletRequest request) {
		Object businessType=request.getSession().getAttribute("businessType");
		if(businessType!=null){
			return businessType.toString();
		}else{
			return "";
		}
	}
}
