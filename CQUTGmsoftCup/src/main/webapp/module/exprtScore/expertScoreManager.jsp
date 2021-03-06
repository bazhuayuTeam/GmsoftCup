<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,tips,dialog,watermark,mask,noData,selfTable,nicescroll,dropDownBox,searchBox,wordStyle,commonStyle,pageBasic");
	ServiceLoader serviceLoader=new ServiceLoader("CodeTableService,GameService,TeamService,GameStepService");
	String operatorId = (String) request.getSession().getAttribute(
			"operatorId");
	String expertID = (String) request.getSession().getAttribute(
			"expertID");
	/* if (null == operatorId) {
		out.println("请先登录!");
		return;  
	} */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>

		<title>评委评审</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	</head>

	<body>
	    <div class="topStyle">
        	<div class="topTitle" id="title">评委评审ccc</div>
        </div>
        <ul class="searchList" id="listOperation">
        	<li><span class="searchName">竞赛名称</span></li>
			<li>
				<select id="name" class="searchSelect" onchange="quickSearch()">
				</select>
			</li>
			<li><span class="searchName">竞赛阶段</span></li>
			<li>
				<select id="clazz" class="searchSelect" onchange="quickSearch()">
				</select>
			</li>
			<li><span class="searchName">赛制流程</span></li>
			<li>
				<select id="task" class="searchSelect" onchange="quickSearch()">
				</select>
			</li>
		</ul>
		<div class="contentDiv">
			<div id="tablePager" style="width: 97%;margin-top:5px;">
				<table id="table" border="0" width="100%"></table> 
				<div id="pager"></div> 
			</div>
		</div> 
        <script type="text/javascript" src='js/module/exprtScore/exprtScoreManager.js'></script>   
	</body>		
</html>
