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
			"jqGrid,dwr,dialog,watermark,tips,mask,commonStyle,searchBox,pageBasic,nicescroll,wordStyle,navigate,dropDownBox,dialogNew,dialogUtil");
	ServiceLoader sl = new ServiceLoader("TargetSystemService,TargetService");
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
		<title>指标标准设置</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
  </head>
  
  <body>
       <div class="topStyle">       
       		<div class="topTitle" id="title">评审标准管理</div>
            <div class="operateRight">
             	<span>|</span>             	
             	<span><a onclick="addTargetSys();" id="add">新增版本</a></span>
             	<span>|</span>
            </div>
        </div>
        <ul class="searchList" >
			<li><span class="searchName">版本名称</span></li>
			<li>
				<input id='targetSystem'  class="searchInput"   onchange="quickSearch();"/>
			</li>
			<li>
				<button onclick="quickSearch();" class="searchButton" id="searchButton">搜索</button>
			</li>
		</ul>
        
        <div class="contentDiv" >
			<div id="MainArea" style="width: 96.8%;">
				<!-- 体系表格 -->
				<div id="targetSystemDiv">
					<table id="targetSystemTable" border="0" width="100%"></table>
					<div id="targetSystemPager"></div>
				</div>
				<!-- 版本表格 -->
				<div id="targetSysVersion">
					<table id="targetSysVersionTable" border="0" width="100%"></table>
					<div id="targetSysVersionTablePager"></div>
				</div>
			</div>
        </div>
        <script type="text/javascript" >var operatorId="${operatorId}";var basePath='<%=basePath%>';</script>
        <script type="text/javascript" src="js/module/targetSystemManager/targetSystemManager.js"></script>
  </body>
</html>
