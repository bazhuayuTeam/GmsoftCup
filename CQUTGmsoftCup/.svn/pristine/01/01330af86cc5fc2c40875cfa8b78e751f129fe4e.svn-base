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
			"jqGrid,dwr,dialogNew,tips,dialog,watermark,mask,wordStyle,commonStyle,pageBasic,searchBox");
	String operatorId = (String) request.getSession().getAttribute(
			"operatorId");
	ServiceLoader serviceLoader = new ServiceLoader(
			"OperatorService,RoleService,RoleAssignService");
	if (null == operatorId) {
		out.println("请先登录!");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title>角色管理</title>
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
		<script language='javascript' src='<%=path%>/js/util/WindowManage.js'></script>
		<script language='javascript' src='<%=path%>/js/util/DialogUtil.js'></script>
		<script type="text/javascript">

</script>
	</head>

	<body>
	<!-- <div class="frame">          
      <span id="title">角色管理</span>	     
            <ul style="float: right;"> 
               <li><img src="css/images/line_blue.png"/></li>
               <li><a onclick="deleteSelectedsFun();">多选删除</a></li>
               <li><img src="css/images/line_blue.png"/></li>
               <li><a onclick="addFun();" >新增</a></li>
               <li><img src="css/images/line_blue.png"/></li>
             
            </ul>
       </div> -->
       <div class="topStyle">
		<div class="topTitle">角色管理</div>
		<div class="operateRight">
			<span> | </span>
			<span><a onclick="addFun();">新增</a></span>
			<span> | </span>
			<span><a onclick="deleteSelectedsFun();">多选删除</a></span>
			<span> | </span>
		</div>
	</div>
       <ul class="searchList" style="position: fixed;top:67px;">
		<li><span class="searchName">角色名称：</span></li>
		<li>
			<input type="text" class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}" id="operatorName" />
		</li>
		<li>
			<button id="searchButton" onclick="quickSearch();" class="searchButton">搜索</button>
		</li>
	</ul>
       
       
       
	<!-- 	<div style="float: right; width: 100%;">
		<div id="QueryArea" style="width:97%">
			<ul class="searchList" >
					<li>角色名称：</li>
					<li>
						<input  type="text"  id="operatorName" class="searchInput"/>
					</li>
					<li>
						<button onclick="quickSearch();" class="searchButton">搜索	</button>
					</li>
			</ul>
		</div> -->
	<div id="MainArea" style="width:100%;margin-left:18px;margin-right:20px;margin-top:128px;"> 
		<table id="roleTable" border="0" width="100%"></table>
		<div id="rolePager"></div>
	</div>
    <script type="text/javascript" src="js/module/roleManager/roleManager.js"></script>
	</body>
</html>
