<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("dwr,dialogNew");
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		
		<title>重庆理工大学"大家杯"软件创意、创新大赛</title>
		
		<meta name=”renderer” content=”webkit”>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		
		<link rel="stylesheet" type="text/css" href="css/login/login.css" />	
		<script type="text/javascript" src="plugins/placeholder/placeholder.js"></script>	
		<script type='text/javascript' src='dwr/interface/OperatorService.js'></script>
	</head>
	<body class="allBody">	
		<div>
			<!-- 背景 -->
			<div class="bodyImg">
				<img alt="login" src="images/login/cqut.png"  class="bodyImg"/>
			</div>
		</div>	
		
		<!-- 登陆块 -->
		<div class="mask" style="width: 100%;height: 15%;"></div>
		<div style="min-height:450px;">
			<div class="login">
				<div class="mask loginMask"></div>
				<!-- 左边 -->
				<div class="leftLogin leftFilter">
					<div class="cqutLogin">
						<img alt="login" src="images/login/login.png" style="margin-top:17%;"/>
					</div>
					<div class="loginOfThe">
						<img alt="login" src="images/login/title.png" style="margin-top:5%;"/>
					</div>
				</div>
				
				<!-- 右边 -->
				<div class="leftLogin" style="background-color: #ffffff;">
					<div class="loginTitle">
						<span class="loginFontStyle">login</span>
					</div>
					
					<div class="inputOfLogin">
						<input type="text" id="user" class="inputStyle" placeholder="请输入用户名"/>
						<img alt="login" src="images/login/user.png" class="imgInput"/>
						<input type="password" id="password" class="inputStyle" placeholder="请输入密码"/>
						<img alt="login" src="images/login/password.png" class="imgInput"/>
						<!--显示登陆失败  -->
						<div id="error" class="loginError"></div>
					</div>
					
					<div class="loginBotton">
						<img alt="login" id="button" onclick="loginJudge()" src="images/login/loginBotton.png" style="cursor:pointer;"/>
					</div>
					
					<div class="loginError" style="margin-top: 25px;"><span style="color:#555">建议使用IE9以上及主流浏览器</span></div>
				</div>
				<div class="mask loginMask"></div>
			</div>
		</div>
		<div class="mask" style="width: 100%;height: 15%;"></div>
		<script type="text/javascript" src="js/login/login.js"></script>
	</body>
</html>
