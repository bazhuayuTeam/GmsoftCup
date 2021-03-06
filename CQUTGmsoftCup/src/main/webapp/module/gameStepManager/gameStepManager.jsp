<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if (null == operatorId) {
		out.println("<script type='text/javascript'>");
		out.println("window.open('" + request.getContextPath() + "/login.jsp','_top')");
		out.println("</script>");
		return;
	}
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,dialogNew,tips,mask,watermark,wordStyle,commonStyle,util,pageBasic,searchBox,dialog");
	ServiceLoader serviceLoader = new ServiceLoader(
			"GameStepService,GameService,GameStepDetailService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>竞赛阶段管理</title>
		<script type='text/javascript' src='js/plugins/deriveExcel/deriveExcel.js'></script>
		<style type="text/css">
a {
	text-decoration: none;
}

.currentAddress:HOVER {
	color: orange;
}
</style>
	</head>
	<body style="margin-top: 0px; padding: 0;">
		<div class="topStyle">
			<div class="topTitle">竞赛阶段管理</div>
			<div class="operateRight">
				<span> | </span>
				<span><a onclick="addNew();">新增</a></span>
				<span> | </span>
			</div>
		</div>
		<div class="navigate" style="position: fixed;top:125px;left:18px;">
			<span><img src="images/navigate/location.png"/></span>
			<span id="currentPosition">当前位置：</span>
		</div>
		<ul class="searchList" style="position: fixed;top:67px;">
			<li><span class="searchName">大赛名称：</span></li>
			<li>
				<select id="game" class="searchInput" onchange="quickSearch();"></select>
			</li>
			<li>
				<button id="searchButton" onclick="quickSearch();" class="searchButton">搜索</button>
			</li>
		</ul>
		<div style="position: absolute; top: 150px; bottom: 0; left: 18px; width: auto; float: left; height: auto; right: 20px;">
			<div id="MainArea" style="width: 100%;">
				<table id="gameTable" border="0" width="100%"></table>
				<div id="gamePager"></div>
			</div>
		</div>
		<script type="text/javascript" src="js/module/gameStepManager/gameStepManager.js">
</script>
	</body>
</html>