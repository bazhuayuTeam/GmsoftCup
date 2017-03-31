<%@page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@page import="com.cqut.util.SessionUtil"%>
<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SessionUtil sessionUtil=new SessionUtil();
	String operatorId=sessionUtil.getOperatorId(request);
	String getOperatorName=sessionUtil.getOperatorName(request);
	String operatorID = (String) request.getSession().getAttribute("operatorId");
	String roleID = (String) request.getSession().getAttribute("roleID");
	String operatorName = (String) request.getSession().getAttribute("operatorName");
	String BM = (String) request.getSession().getAttribute("BM");
	String ZGH = (String) request.getSession().getAttribute("ZGH");

	session.setMaxInactiveInterval(3600 * 12);
	JQueryLoader jq = new JQueryLoader("jqGrid,dwr,dialog,commonStyle,dialogNew,tips,nicescroll");
	ServiceLoader sl = new ServiceLoader("ModuleService,OperatorService");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%=jq.getCssFilesStr() %>
		<%=jq.getJsFilesStr() %>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>重庆理工"大家杯"创新、创意大赛</title>
		<meta http-equiv="expires" content="0"/>
	    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
	    <link type="text/css" rel="stylesheet" href="css/module/manager/manager.css" />
		<script type="text/javascript">
		var managerPageData = {
				basepath : '<%=basePath%>',
				roleId : '<%=roleID%>',
				operatorId : '<%=operatorID%>',
				operatorName : '<%=operatorName%>',
				path : '<%=path%>',
				ZGH : '<%=ZGH%>',
		};
		</script>
		
</head>
<body id="body"> 
	<!-- 侧边栏开始 -->
	<div class="sidebarDiv">
		<!-- 菜单以上开始 -->
		<div class="header">
			<!-- 头像操作开始 -->
			<div class="operationsDiv">
				<div class="headPicDiv">
						<img src="images/manager/user-pic.png"  class="headPicCircle" id="headPicCircle"/>
						<div class="userName" id="userName"></div>
				</div>
			</div>
		</div>
		<!-- 头像操作结束 -->
		<!-- 菜单以上结束 -->
		<!-- 菜单开始 -->
		<div class="outerMenuDiv">
			<div class="innerMenuDiv" id="menu"></div>
		</div>
		<!-- 菜单结束 -->
		<!-- 底部版权开始 -->
		<div class="footer">©重庆理工大学</div>
		<!-- 底部版权结束 -->
	</div>
	<!-- 二级菜单结束 -->
	<!-- 侧边栏结束 -->
	<!-- 右侧iframe开始 -->
	<div class="iframeDiv">
		<header class="index-info-container">
			<div class="index-info">
				<img src="images/manager/menu-email.png">您有3条新消息
			</div>
			<div class="index-time">
				<img src="images/manager/menu-time.png">2016年10月11日 星期二 16：57
			</div>
			<div class="index-name">
				<img src="images/manager/menu-name.png">用户名啦啦啦
			</div>
		</header>
		<iframe class="iframe" id="showContent"></iframe>
	</div>
	<!-- 右侧iframe结束 -->
	<script type="text/javascript" src="js/module/manager/manager.js"></script>
</body>
</html>
















