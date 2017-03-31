<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,dialog,watermark,util,print");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<title>项目申报统计表</title>
		<link  rel="stylesheet" type="text/css" href="<%=basePath%>css/module/tree/myTree.css" />
		<style type="text/css">
		a {
			text-decoration: none;
		}
		.serachDiv { 
			margin-top: 4px;
			float: left; 
		}
		h1 {
			color: #2a2a2a;
			font-size: 18pt;
		}
		</style>
		<script type='text/javascript' src='dwr/interface/BatchService.js'></script>
		<script type='text/javascript' src='dwr/interface/ProjectService.js'></script>
		<script type='text/javascript'>
			var basepath = "<%=basePath%>";
		</script>
  </head>
  
  <body>
	<div id="page">
		<div id="Title_bar" style="margin-top: -1px;">
			<div id="Title_bar_Head">
				<div id="Title_Head"></div>
				<div id="Title">
					<img border="0" width="13" height="13"
						src="images/plugins/components/title_arrow.gif"/>
					<span id="title_title">项目申报统计表</span>
				</div>
				<div id="Title_End"></div>
				<div id="Title_bar_bg"></div>
			</div>
			<div id="Title_bar_Tail">
				<div id="Title_FuncBar">
					<ul id="funBar">
						<li class="line"></li>
						<li class="title">
							<a href="javascript: exportToExcel();">
								导出
							</a>
						</li>
						<li class="line"></li>
						<li class="title">
							<a id="search_print" style="cursor:pointer" fieldType="print" needPrintArea="projectApplyStatTable" tableName="项目申报统计表">
								打印
							</a>
						</li>
						<li class="line"></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 显示树 -->
		<div id="goodsCateManageTreeDiv" style="position:absolute;top:46px;bottom:0;width: 200px;float:left;height: auto;overflow: auto;">
			<ul id="projectPreExamineTree" class="ztree" style="margin-left:8px;"></ul>
			<div id="moreButton">
			</div>
		</div>
		<div id="showMainPage" style="position:absolute;top:46px;bottom:0;left:200px;width:auto;float:left;height: auto;overflow: auto; right:0;">
			<div id="QueryArea" style="width: 100%;">
				<div class="serachDiv">
					<label style="margin-left: 15px;">项目名称</label>
					<input style="width: 150px;" type="text" id="search_projectName" waterText="请输入项目名称" showName="项目名称" />
				</div>
				<div class="serachDiv" style="height: 26px;">
					<label style="margin-left: 15px;">申报单位</label>
					<input type="text" style="width: 150px;" id="search_organizationName" waterText="请输入申报单位" showName="申报单位" />
					<button id="searchButton" onclick="quickSearch();" style="margin-left: 5px;">查询</button>
				</div>
			</div>
			<p style="clear: both;margin-top:0; margin-bottom:0;"></p>
			<div id="MainArea" style="margin-top: 0;width: 100%">
				<div style="margin-top: -10px; width: 100%; height: 30px; text-align: center; "><h1 id="batchNameLabel">所有申报批次</h1></div>
				<table id="projectApplyStatTable" border="0"></table>
				<!--<div id="projectApplyStatPager"></div>-->
			</div>
		</div>
	</div>
	<script type='text/javascript' src='js/module/projectManage/projectApplyStatTable/myTree.js'></script>
	<script type="text/javascript" src="plugins/print/printDemo.js"></script>
  </body>
</html>
