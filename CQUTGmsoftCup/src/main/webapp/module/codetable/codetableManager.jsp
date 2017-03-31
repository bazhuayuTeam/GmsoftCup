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
			"jqGrid,dwr,dialogNew,tips,dialog,watermark,wordStyle,commonStyle,pageBasic,searchBox");
	ServiceLoader serviceLoader = new ServiceLoader(
			"CodeTableService,SystemFileService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>码表管理</title>
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
			<div class="topTitle">码表管理</div>
			<div class="operateRight">
				<span> | </span>
				<span><a onclick="addFun();">新增</a></span>
				<span> | </span>
				<span><a onclick="deleteSelectedsFun();">多选删除</a></span>
				<span> | </span>
			</div>
		</div>
		<div class="navigate" style="position: fixed;top:125px;left:18px;">
			<span><img src="images/navigate/location.png"/></span>
			<span id="currentPosition">当前位置：</span>
		</div>
		<ul class="searchList" style="position: fixed;top:67px;">
			<li><span class="searchName">码表类型：</span></li>
			<li>
				<input type="text" class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}" id="codeTableCodeSearch" />
			</li>
			<li><span class="searchName">码表名称：</span></li>
			<li>
				<input  class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}" type="text" id="codeTableNameSearch" />
			</li>
			<li>
				<button id="searchButton" onclick="quickSearch();" class="searchButton">搜索</button>
			</li>
		</ul>
		<!-- 当前位置开始 -->
		<!-- <div class="currentPosition" id="currentPosition"></div> -->
		<!-- 当前位置结束 -->
		<div style="position: absolute; top: 150px; bottom: 0; left: 18px; width: auto; float: left; height: auto; right: 20px;">
			<div id="MainArea" style="width: 100%;">
				<table id="codeTableTable" border="0" width="100%"></table>
				<div id="codeTablePager"></div>
			</div>
		</div>
		<script type="text/javascript" src="js/module/codetable/codeTableManager.js">
</script>
	</body>
</html>