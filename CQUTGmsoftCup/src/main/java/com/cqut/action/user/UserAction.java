package com.cqut.action.user;

import java.util.List;
import java.util.Map;

import com.cqut.service.user.UserService;
import com.cqut.util.security.KaptchaUtil;
import com.cqut.util.struts.Condition;
import com.cqut.util.struts.Permission;
import com.cqut.util.struts.WebUtil;
import com.google.gson.Gson;

public class UserAction {
	private static final Gson gson = new Gson();
	private static UserService userService = new UserService();
	private static final long serialVersionUID = 1L;
	private String condition;
	private String validCode;

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
}
