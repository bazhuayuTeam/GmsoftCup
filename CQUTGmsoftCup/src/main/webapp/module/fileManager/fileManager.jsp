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
			"TeamService,SystemFileService,CodeTableService,GameStepService,ProjectService,GameService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>文档管理</title>
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
			<div class="topTitle">文档管理</div>
			
		</div>
		<ul class="searchList" style="position: fixed;top:67px;">
			<li><span class="searchName">竞赛名称：</span></li>
			<li>
				<select id="game" class="searchInput" onchange="quickSearch();"></select>
			</li>
			<li><span class="searchName">竞赛阶段：</span></li>
			<li>
				<select id="gameStep" class="searchSelect">
				</select>
			</li>
			<li><span class="searchName">赛制流程：</span></li>
			<li>
				<select id="type" class="searchSelect">
				<!-- <option value="0">初赛</option>
				<option value="1">决赛</option> -->
				</select>
			</li>
			<%--<li><span class="searchName">是否提交文档：</span></li>
			<li>
				<select id="state" class="searchSelect">
				</select>
			</li>
			--%><li>
				<button id="searchButton" onclick="quickSearch();" class="searchButton">搜索</button>
			</li>
		</ul>
		
		<div style="position: absolute; top: 150px; bottom: 0; left: 18px; width: auto; float: left; height: auto; right: 20px;">
			<div id="MainArea" style="width: 100%;">
				<table id="codeTableTable" border="0" width="100%"></table>
				<div id="codeTablePager"></div>
			</div>
		</div>
		<script type="text/javascript" >var operatorId="${operatorId}";</script>
		<script type="text/javascript" src="js/module/fileManager/fileManager.js">
</script>
	</body>
</html>