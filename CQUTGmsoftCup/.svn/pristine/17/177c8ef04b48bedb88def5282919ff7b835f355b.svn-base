<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String userId = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_ID);
	if (null == userId) {
		out.println("请先登录!");
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
  	<base href="<%=basePath%>" />
    <title>账号安全</title>
    <link rel="stylesheet" href="css/common.css"/>
	
  </head>
 	<body >
 	<div class="center" style="height:400px;text-align:center;margin:0 auto;padding-top: 100px !important;">
  	<center>
  			<input type="button" class="submit bule" style="width:330px;" value="我要参赛" onclick="myHerf();"></a>
	     </center>
  	</div>
  	<script language="JavaScript">
    function myHerf(){
	
		location.href = basePath + "module/common.jsp?info=enroll";
	}
  </script>
	</body>
</html>
