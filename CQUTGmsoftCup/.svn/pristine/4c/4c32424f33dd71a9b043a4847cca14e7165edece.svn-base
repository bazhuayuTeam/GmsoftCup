<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,dialog,dialogNew,watermark,commonStyle,searchBox,wordStyle,pageBasic,dropDownBox");//jqGrid是一个用来显示网格数据的jQuery插件，dwr是一个用于改善web页面与Java类交互的远程服务器端Ajax开源框架，dialog提示对话框，watermark水印，
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService,AcademicService,");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if (null == operatorId) {//判断ID是否为空，来确定是否登录
		out.println("请先登录!");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">

		<title>部门管理</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<style type="text/css">
			a {
				text-decoration: none;
			}
		</style>
	</head>

	<body>
		 <div class="topStyle">
	   		<div class="topTitle">部门管理</div>
	   		<div class="operateRight">
	   			<span>| </span>
	   			<span><a onclick="addFun();"> 新增  </a></span>
		   		<span>| </span>
		   		<span><a onclick="deleteSelectedsFun();"> 多选删除  </a></span>
		   		<span>| </span>
	   		</div>
		</div>
	
		<ul class="searchList">
				<li><span class="searchName">部门名称</span></li>
				<li><input class="searchInput" id="operatorName"  ></li>
				<li><button id="searchButton" class="searchButton" onclick="quickSearch();" >搜索</button></li>
		</ul>
	   		<div id="MainArea" style="margin-top:130px;margin-left:18px;width:97%;">
				<table id="roleTable" border="0" width="100%"></table><!-- 主要的表格框架 -->
				<div id="rolePager"></div><!--表格下面的展示信息条数下拉框及前一页后一页箭头  -->
			</div>
	   <script language='javascript' src='<%=path%>/js/module/academic/academicManager.js'></script>
	</body>
</html>
