package com.cqut.filter.loginFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private static final String LOGIN_JSP_NAME = "/login.jsp";
	private static final String REQUEST_KEYWORD = ".jsp";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest servletRequset = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			HttpSession session = servletRequset.getSession();
			
			// 获得请求的URI
			String path = servletRequset.getRequestURL().toString();
			
			// 获取操作员ID
			Object operatorId = session.getAttribute("operatorId");

			if(path.indexOf(REQUEST_KEYWORD) == -1 || path.indexOf(LOGIN_JSP_NAME) != -1) {	// 如果路径中不包含.jsp的话则不过滤。登录页面不需要过滤
				chain.doFilter(request, response);
				return;
			}
			
			// 说明没哟登录
			if(operatorId == null || operatorId.toString().equals("")) {
				servletResponse.sendRedirect(getBasePath(servletRequset) + "login.jsp");	//	 跳转到登录页面
			}else {
				chain.doFilter(request, response);	// 已经登录继续请求
			}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	private String getBasePath(HttpServletRequest request) {
		return  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
	}
}


















