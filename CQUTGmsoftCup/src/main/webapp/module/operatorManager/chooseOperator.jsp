<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dialog,dwr,ztree3.0,watermark,util,mask,commonStyle,searchBox");
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService,HandleViewService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>选择人员</title>
		<link type="text/css" rel="stylesheet" href="css/fieldset.css" />
		<link rel="stylesheet" type="text/css" href="css/plugins/jquery-loadmask/jquery.loadmask.css"/>
	</head>
	<body>
		<div id="addCourseComponent" title="选择人员" style="display: block;">
			<ul class="searchList" style="top:0px;">
				<li><span class="searchName">部门</span></li>
				<li>
					<select id="departmentSearch" class="searchSelect" onkeydown="checkkey(event);" onchange="document.getElementById('search').click();">
						<option>全部</option>
					</select>
				</li>
				<li><span class="searchName">职工号</span></li>
				<li>
					<input type="text" id="kcdmSearch" class="searchInput" onkeydown="checkkey(event);"/>
				</li>
				<li><span class="searchName">姓名</span></li>
				<li>
					<input type="text" id="courseSearch" class="searchInput" onkeydown="checkkey(event);"/>
				</li>
				<li><button class="searchButton" onclick="quickSearch();"  id="search">搜索</button></li>
			</ul>
			<div class="contentDiv" style="padding-top:60px;">
				<div id="MainArea" style="overflow:auto;width:97%">
					<table id="courseTable"></table>
					<div id="coursePager"></div>
				</div>
			</div>
		</div>
		<script type='text/javascript' src='js/module/operatorManager/chooseOperator.js'></script>
	</body>
</html>
