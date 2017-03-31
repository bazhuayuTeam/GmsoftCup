package com.cqut.action.login;


import java.util.List;

import com.cqut.dao.common.CommonDao;
import com.cqut.dao.common.ICommonDao;
import com.cqut.entity.user.User;
import com.cqut.service.user.UserService;
import com.cqut.service.user.customInterface.IUserService;
import com.cqut.util.OperatorUtil;
import com.cqut.util.security.KaptchaUtil;
import com.cqut.util.struts.Condition;
import com.cqut.util.struts.Permission;
import com.cqut.util.struts.WebUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();
	private final ICommonDao commonDao = new CommonDao();

	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private static IUserService userService = new UserService();

}
