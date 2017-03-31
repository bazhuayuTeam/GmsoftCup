<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,watermark,tips,mask,commonStyle,searchBox,pageBasic,nicescroll,wordStyle,dialogNew");
ServiceLoader sl = new ServiceLoader("ExpertService,OperatorService");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    
    <title>评委管理</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=sl.getServiceFilesStr()%>
	<script type="text/javascript">
	
	var operatorId = "${operatorId}"
	
	</script>
	<style type="text/css">
		body{
			overflow: hidden;
		}
	</style>
  </head>
  
 	<body>
		<div class="topStyle">           
	       	<div  class="topTitle" id="title">评委管理</div>
             <div class="operateRight">
             	<span>|</span>             	
             	<span><a onclick="addFun();">新增评委</a></span>
             	<span>|</span>
             </div>
        </div>
		<ul class="searchList">
			<li><span class="searchName">姓名</span></li>
			
			<li>
				<input type="text" id="nameSearch" class="searchInput" onkeydown="javascript:if(event.keyCode==13){document.getElementById('searchButton').click();}"/>
			</li>
			
			<li><button id="searchButton" class="searchButton" onclick="quickSearch();" >搜索</button></li>
		</ul>
		<div class="contentDiv">
			<div id="MainArea"  style="width: 97%;">
				<table id="expertTable" border="0" width="100%"></table> 
				<div id="expertPager"></div> 
			</div>
		</div>
		<script type="text/javascript" src="js/module/expertManager/expertManager.js"></script>
	</body>
</html>














