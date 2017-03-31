<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	//if (null == operatorId) {
		//out.println("请先登录!");
		//return;
	//}
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,dialog,watermark,commonStyle,pageBasic,searchBox,util,wordStyle,dropDownBox,dialogNew,nicescroll");
	ServiceLoader serviceLoader=new ServiceLoader("GameService,GameStepService,reviewManagementService,StandardVersionService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>评审管理</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
	</head>

	<body>
		<div class="topStyle">
        	<div class="topTitle">评审管理</div>
          	<div class="operateRight">
   
          	</div>
        </div>
        <ul class="searchList">
			<li><span class="searchName">大赛名称</span></li>
			<li>
				<select id='champion'  class="searchSelect" onchange="initStep();">
				</select>
			</li>
			<li><span class="searchName">竞赛类别</span></li>
			<li>
				<select id='rate'  class="searchSelect" onchange="quickSearch();">
				</select>
			</li>
			<li><span class="searchName">是否设置评审时间</span></li>
			<li>
				<select id='review-time'  class="searchSelect" onchange="quickSearch();">
					<option value=0>全部</option>
					<option value=1>是</option>
					<option value=2>否</option>
				</select>
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
    <script type="text/javascript" src='<%=path%>/js/module/reviewManagement/reviewManagement.js'></script>
	</body>
</html>
