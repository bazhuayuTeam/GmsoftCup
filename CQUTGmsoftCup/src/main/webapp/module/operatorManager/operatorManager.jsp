<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,watermark,tips,mask,commonStyle,dialogNew,pageBasic,searchBox,wordStyle,nicescroll");
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService,SystemFileService");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="<%=basePath%>" />
    <title>操作员管理</title>
	<%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=serviceLoader.getServiceFilesStr()%>
	<script type="text/javascript">
		var operatorId = "${operatorId}";
	</script>
	
  </head>
 	<body style="overflow-x:hidden">
		<div class="topStyle">
        	<div class="topTitle">操作员管理</div>
          	<div class="operateRight">
          		<span>|</span>
          		<span><a onclick="addFun();">新增</a></span>
          		<span>|</span>
          		<span><a onclick="deleteSelectedsFun();">多选删除</a></span>
          		<span>|</span>
          	</div>
        </div>
		<ul class="searchList">
			<li><span class="searchName">账号</span></li>
			<li>
				<input type="text" id="ZGHSearch" class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}"/>
			</li>
			<li><span class="searchName">姓名</span></li>
			<li>
				<input type="text" id="nameSearch" class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}"/>
			</li>
			<li><span class="searchName">是否禁用</span></li>
			<li>
				<select  id="isOpen" class="searchSelect" onchange="quickSearch()">
					<option value="">全部</option>
					<option value="0">是</option>
					<option value="1">否</option>
				</select>
			</li>
			<li><button id="searchButton" class="searchButton" onclick="quickSearch();" >搜索</button></li>
		</ul>
		<div class="contentDiv">
			<div id="MainArea" style="width: 97%;">
				<table id="operatorTable" border="0" width="100%"></table> 
				<div id="operatorPager"></div> 
			</div>
		</div>
		<script type="text/javascript" src="js/module/operatorManager/operatorManager.js"></script>
		<script type="text/javascript" src="js/module/operatorManager/uploadPhoto.js"></script>
	</body>
</html>
