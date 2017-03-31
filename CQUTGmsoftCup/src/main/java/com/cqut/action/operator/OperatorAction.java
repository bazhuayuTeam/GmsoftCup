package com.cqut.action.operator;

import java.util.List;
import java.util.Map;

import com.cqut.service.operator.OperatorService;
import com.cqut.service.operator.customInterface.IOperatorService;
import com.cqut.util.security.KaptchaUtil;
import com.cqut.util.struts.Condition;
import com.cqut.util.struts.Permission;
import com.cqut.util.struts.WebUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class OperatorAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();

	private String validCode;
	private String condition;

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private static IOperatorService operatorService = new OperatorService();

}
