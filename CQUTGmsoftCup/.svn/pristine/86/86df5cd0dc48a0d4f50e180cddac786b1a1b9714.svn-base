<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if (null == operatorId) {
		out.println("请先登录!");
		return;
	}
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,dialog,watermark,commonStyle,pageBasic,searchBox,wordStyle,dropDownBox,dialogNew,nicescroll");
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService,AcademicService,DisciplineService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>专业管理</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
	</head>

	<body>
		<div class="topStyle">
        	<div class="topTitle">专业管理</div>
          	<div class="operateRight">
          		<span>|</span>
          		<span><a onclick="addFun();">新增</a></span>
          		<span>|</span>
          		<span><a onclick="deleteSelectedsFun();">多选删除</a></span>
          		<span>|</span>
          	</div>
        </div>
        <ul class="searchList">
			<li><span class="searchName">学院</span></li>
			<li>
				<select id='Academic'  class="searchSelect">
					<option value=''>全部学院</option>
				</select>
			</li>
			<li><span class="searchName">专业</span></li>
			<li>
				<input  type="text"  id="disciplineName" class="searchInput"/>
			</li>
			<li>
				<button onclick="quickSearch();" class="searchButton">搜索</button>
			</li>
		</ul>
        <div class="contentDiv">
			<div id="MainArea" style="width:97%">
				<table id="roleTable" border="0" width="100%"></table>
				<div id="rolePager"></div>
			</div>
		</div>    
    <script type="text/javascript" src='<%=path%>/js/module/discipline/disciplineManager.js'></script>
	</body>
</html>
