<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,tips,dialog,tree,dialogNew,watermark,mask,wordStyle,commonStyle,pageBasic,searchBox");
ServiceLoader serviceLoader=new ServiceLoader("CodeTableService,ModuleService,SystemFileService");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>模块管理</title>
		<style type="text/css">
			a{ text-decoration: none; }
			.line{
				float: none;
				height: auto;
			}
		</style>
	</head>
	<body style="margin-top: 0px;padding :0;height: 100%;">
        <div class="topStyle">
			<div class="topTitle">模块管理</div>
			<div class="operateRight">
				<span> | </span>
				<span><a onclick="addFun();">新增</a></span>
				<span> | </span>
				<span><a onclick="deleteSelectedsFun();">多选删除</a></span>
				<span> | </span>
			</div>
	   </div>
        
       <div style="left: 18px;right: 20px;top: 70px;position: absolute;">
				<div style="width: 17%;height: auto;">
					<ul id="moduleTree" class="tree"></ul>
				</div>
		</div>
	 	<div class="content" style="left: 20%;right: 20px;top: 67px;position: absolute;">
			<div style="width:100%;height: auto;">
			 <ul class="searchList" style="position: fixed;top:67px;margin-left: 0px;">
				<li><span class="searchName">名称：</span></li>
				<li>
					<input type="text" class="searchInput" id="moduleSearch" />
				</li>
				<li>
					<button id="searchButton" onclick="quickSearch(this);" class="searchButton">搜索</button>
				</li>
			</ul>
			<div id="MainArea" style="margin-top: 60px;">
				<table id="moduleTable" border="0" width="100%"></table> 
				<div id="modulePager"></div> 
			</div>
		</div>
       </div>
       <script type="text/javascript" src='<%=path%>/js/module/module/moduleManager.js'></script>
	</body>
</html>