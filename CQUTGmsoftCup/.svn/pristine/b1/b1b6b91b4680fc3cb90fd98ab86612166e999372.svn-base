package com.cqut.util.struts;

import java.lang.reflect.Method;
import java.util.Map;

import com.cqut.util.OperatorUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 利用注解进行登录方法拦截
 * @author Ming
 * @description 拦截需要登录才能调用的方法
 * @date 2016-04-11
 */
public class LoginInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String currentMethodName = invocation.getProxy().getMethod();
		Class<?> actionClass = invocation.getAction().getClass();
		Method currentMethod = actionClass.getMethod(currentMethodName);
		if (currentMethod.getAnnotation(Permission.class) != null) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			if (session.get(OperatorUtil.OPERATOR_ID) == null) {
				WebUtil.writeText("请先登录！");
				System.out.println("拦截未登录用户操作(Class: " + actionClass.getSimpleName() + ", Method: " + currentMethod.getName() + ")");
				return null;
			}
		}
		return invocation.invoke();
	}
}
