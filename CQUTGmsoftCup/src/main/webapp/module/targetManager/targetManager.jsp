<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,tips,dialog,watermark,mask,commonStyle,searchBox,pageBasic,nicescroll,wordStyle,navigate,dropDownBox,dialogNew");
	ServiceLoader sl = new ServiceLoader("TargetSystemService,TargetService,StandardVersionService");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	/*if (null == operatorId) {
		out.println("请先登录!");
		return;
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<title>评审指标设置</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<link rel="stylesheet" type="text/css" href="css/module/target/targetManager.css"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
  </head>
  <body>
       <div class="topStyle">
	      <div class="topTitle"  id="title">评审指标设置</div>
	      <div class="operateRight">
	           	<span>|</span>             	
	           	<span><a onclick="addTarget();">新增指标</a></span>
	           	<span>|</span>
	      </div>             
        </div>
        <ul class="searchList" >
			<li><span class="searchName">指标版本</span></li>
			<li>
				<select id='targetSysVersion'  class="searchSelect"   onchange="quickSearch();">
				</select>
			</li>
			<li><span class="searchName">指标名称</span></li>
			<li>
				<input id='targetName'  class="searchInput"  maxlength="30"/>
			</li>
			<li>
				<button id="searchButton" onclick="quickSearch();" class="searchButton">搜索	</button>
			</li>					
		</ul>
        <div class="contentDiv">
			<!-- 当前位置开始 -->
			<div class="navigate" id="currentPosition"></div>
			<div id="MainArea" style="width: 96.8%;">
				<table id="targetTable" border="0" width="100%"></table>
				<div id="targetPager"></div>
			</div>
        </div>
        <script type="text/javascript" >var operatorId="${operatorId}";</script>
        <script type="text/javascript" src="js/module/targetManager/targetManager.js"></script>
  </body>
</html>
