<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader=new JQueryLoader("selfTable,jqGrid,dwr,"+
										"dialog,watermark,tips,mask,dialogNew"+
										"jAlert,noData,searchBox,pageBasic,"+
										"dropDownBox,dialogNew,wordStyle,nicescroll,commonStyle");
	ServiceLoader serviceLoader=new ServiceLoader("TargetSystemService,TargetService,StandardVersionService");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=serviceLoader.getServiceFilesStr()%>
    
    <title>指标版本预览</title>
  </head>
  
  <body>
  
  		<div class="topStyle">
	         <div id="title" class="topTitle">指标版本预览</div> 
       	</div>
		<div class="content">
		 <div class="inputTitleDiv" style="top:67px;">
				<div class="line"></div>
		    	<span class="title" id="targetHead">暂无信息</span>
		 </div>
	</div>
    <!-- 内容部分 -->
    	<!-- 数据域 -->
    	<div style="width:98.5%;margin-top:135px;margin-left:0px;position: absolute;padding-bottom: 10px;" id="MainArea">
    		<table  id="table" border="0" width="100%" cellspacing="0px"
					style="font-family:'微软雅黑';font-size:13px;margin-top:10px;" class="table-content">
			</table>
    	</div>
    <script type="text/javascript" src="js/module/targetSystemManager/indexSystemQuery.js"></script>
  </body>
</html>
