<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if (null == operatorId) {
		out.println("<script type='text/javascript'>");
		out.println("window.open('" + request.getContextPath() + "/login.jsp','_top')");
		out.println("</script>");
		return;
	}
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,dialogNew,tips,mask,watermark,wordStyle,commonStyle,pageBasic,searchBox,dialog,util");
	ServiceLoader serviceLoader = new ServiceLoader(
			"TeamService,GameService,CodeTableService,HostUnitService,GameStepDetailService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>竞赛申请</title>
		<script type='text/javascript' src='js/plugins/deriveExcel/deriveExcel.js'></script>
		<style type="text/css">
a {
	text-decoration: none;
}

.currentAddress:HOVER {
	color: orange;
}
</style>
	</head>
	<body style="margin-top: 0px; padding: 0;">
		<div class="topStyle">
			<div class="topTitle">竞赛申请</div>
			<div class="operateRight">
				<span> | </span>
				<span><a onclick="agreeMulti();">批量通过</a></span>
				<span> | </span>
				<span><a onclick="agreeNotMulti();">批量退回</a></span>
				<span> | </span>
			</div>
		</div>
		<ul class="searchList" style="position: fixed;top:67px;">
			<li><span class="searchName">大赛名称：</span></li>
			<li>
				<select id="game" class="searchInput" onchange="quickSearch();">
					<option value="">请选择大赛</option>
				</select>
			</li>
		</ul>
		<div id="gameAuditTableContainer" style="position: absolute; top: 150px; bottom: 0; left: 18px; width: auto; float: left; height: auto; right: 20px;">
			<div id="MainArea" style="width: 100%;">
				<table id="gameAuditTable" border="0" width="100%"></table>
				<div id="gameAuditPager"></div>
			</div>
		</div>
		<script type="text/javascript" src="js/module/gameManager/gameAudit.js"></script>
	</body>
</html>