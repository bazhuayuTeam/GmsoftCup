package com.cqut.util;

import java.io.IOException;
import java.io.PrintWriter;

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

	public void destroy() {

	}
	//用户访问的filter
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterchain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)req).getSession();
		HttpServletRequest request = ((HttpServletRequest)req);
		HttpServletResponse response = (HttpServletResponse)res;
		String sp=request.getRequestURI();
		if(sp.indexOf("module") > 0){
			if(session.getAttribute(OperatorUtil.OPERATOR_ID)==null||session.getAttribute(OperatorUtil.OPERATOR_NAME)==null){
				//
				//response.sendRedirect(request.getContextPath()+"/login.jsp");
				//response.setHeader("refresh", "1;URL="+request.getContextPath()+"/login.jsp");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>");   
			    out.println("window.open('"+request.getContextPath()+"/login.jsp','_top')");   
			    out.println("</script>");  
				return ;
			}
		}
		filterchain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
