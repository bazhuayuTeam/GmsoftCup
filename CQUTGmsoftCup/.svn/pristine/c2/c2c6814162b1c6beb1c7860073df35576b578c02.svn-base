package com.cqut.util;

import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
@Controller
@RemoteProxy
public class SessionUtil {
	/**
	 * 获取当前登录操作员的ID
	 * */
	@RemoteMethod
	public String getOperatorId(HttpServletRequest request) {
		Object operatorId=request.getSession().getAttribute("operatorId");
		if(operatorId!=null){
			return operatorId.toString();
		}else{
			return "";
		}
		
	}
	/**
	 * 获取当前登录操作员的角色ID
	 * */
	@RemoteMethod
	public String getRoleId(HttpServletRequest request) {
		Object roleId=request.getSession().getAttribute("roleID");
		if(roleId!=null){
			return roleId.toString();
		}else{
			return "";
		}
		
	}
	/**
	 * 获取当前登录操作员的名称
	 * */
	@RemoteMethod
	public String getOperatorName(HttpServletRequest request) {
		//
		Object operatorName=request.getSession().getAttribute("operatorName");
		if(operatorName!=null){
			return operatorName.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登录操作员所属企业的ID
	 * */
	@RemoteMethod
	public String getEnterpriseID(HttpServletRequest request) {
		Object organizationCode=request.getSession().getAttribute("enterpriseID");
		if(organizationCode!=null){
			return organizationCode.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登录操作员所属企业的Name
	 * */
	@RemoteMethod
	public String getEnterpriseName(HttpServletRequest request) {
		Object organizationName=request.getSession().getAttribute("enterpriseName");
		if(organizationName!=null){
			return organizationName.toString();
		}else{
			return "";
		}
	}
	/**
	 * 获取当前登录操作员所属企业的类型ID
	 * */
	@RemoteMethod
	public String getEnterpriseType(HttpServletRequest request) {
		Object organizationType=request.getSession().getAttribute("enterpriseType");
		if(organizationType!=null){
			return organizationType.toString();
		}else{
			return "";
		}
	}
	/**
	 * 获取当前登录操作员所属人员的类型
	 * */
	@RemoteMethod
	public String getOperatorKind(HttpServletRequest request) {
		Object organizationName=request.getSession().getAttribute("operatorKind");
		if(organizationName!=null){
			return organizationName.toString();
		}else{
			return "";
		}
	}
	
}
