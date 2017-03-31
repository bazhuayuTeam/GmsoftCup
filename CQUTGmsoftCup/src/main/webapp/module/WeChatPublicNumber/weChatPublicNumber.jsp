<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("dwr");
ServiceLoader serviceLoader=new ServiceLoader("GameStepService");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=serviceLoader.getServiceFilesStr()%>
    <title>微信公众号</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body style="background-color: #A0CAED;">
  	<div>
  		<center><img src="images/wechat.png" style="width: 60%;"></center>
  	</div>
  	<div style="text-align: center;margin-top: 50px;padding-bottom:50px;">
  		<input type="text" class="username" id="username" style="height: 30px;width: 230px;padding-left: 10px;" placeholder="请输入学号进行查询">
  		<br>
  		<input type="button" class="query" value="查询" onclick="query()" style="height: 40px;width: 230px;margin-top: 30px;background: #2ea0ef;color:#ffffff;font-size: 16px">
  	</div>
  	<script type="text/javascript" src="js/WeChatPublicNumber/weChatPublicNumber.js"></script>
  </body>
</html>
