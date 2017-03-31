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
			"selfTable,jqGrid,dwr,"+
					"dialog,watermark,tips,mask,dialogNew"+
					"jAlert,noData,searchBox,pageBasic,"+
					"dropDownBox,dialogNew,wordStyle,nicescroll,commonStyle");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	ServiceLoader sl = new ServiceLoader("GameStepDetailService");
	String id = request.getParameter("id");
	/*if (null == operatorId) {
		out.println("请先登录!");
		return;
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<title>指标版本使用情况</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>      
		<style type="text/css">
			.showSelectorArea {
				float : left;
				width: 814px;
				margin-top: 10px;
			}
			body{
				overflow:hidden;
			}
		</style>
  </head>
  
  <body>
	<div class="topStyle">
      <div id="title" class="topTitle">指标版本使用情况</div> 
   	</div>
   	<div class="content">
		 <div class="inputTitleDiv" style="top:70px;">
				<div class="line"></div>
		    	<span class="title" id="targetHead">暂无信息</span>
		 </div>
	</div>
   <!-- 内容部分 -->
   	<!-- 数据域 -->
   	<div style="width:98.5%;margin-top:135px;margin-left:18px;position: absolute;padding-bottom: 10px;" id="MainArea">
   		<div id="targetSystemDiv">
			<table id="targetSystemTable" border="0" width="100%"></table>
			<div id="targetSystemPager"></div>
		</div>
   	</div>
   	<script type="text/javascript">
   		var id = '<%=id%>';
   	</script>
    <script type="text/javascript"  src="js/module/targetSystemManager/targetGame.js"></script>
  </body>
</html>
