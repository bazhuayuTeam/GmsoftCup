<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr,watermark");
	String info = request.getParameter("info");
	String userName = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_NAME);
	String operatorId = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_ID);
	/* if (null == operatorId) {
		out.println("请先登录!");
		return;
	} */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="<%=basePath%>" />
    <title>个人中心</title>
	<%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<link rel="stylesheet" href="css/jquery-labelauty.css"/>
	<script type="text/javascript" src="js/module/enroll/jquery-labelauty.js"></script>
	<script type="text/javascript" src="demo/lib/angularjs/angular.min.js"></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/UserService.js'></script>
	<style type="text/css">
		.head-link{
			color:#fff;
			text-decoration: none;
			font-family: "Microsoft YaHei";
		}
		.head-link:hover {
			color:#fff;
			text-decoration:underline;
		}
	</style>
  </head>
 	<body style="background-color: #e6edf1; ">
		<div id="container">
			<div id="header">
				<div class="login" style="position: relative;margin: 0 auto;width: 1000px;height:115px;line-height:115px;z-index:1;">
					<table style="width:1000px;height:115px;">
						<tr>
							<td style="width:30%;"><img onclick="goHome();" style="margin-left: 40px;height:50px;cursor:pointer;" src="images/common/gmsoft.png"/></td>
							<td style="width:70%;text-align: right;">
								<span  style="padding-right:40px;">
									<a href="javascript:void(0);" class="head-link" id="link_one">登录</a>
									<span style="color:#fff">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
									<span id="hidden_span">
										<a href="javascript:void(0);" class="head-link" id="link_two">注册</a>
										<span style="color:#fff">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
									</span>
									<a href="javascript:void(0);" class="head-link" onclick="goHome();">返回首页</a>
								</span>
							</td>
						</tr>
					</table>
				</div>
				<div style="position: absolute;top:0px;z-index:0;">
					<img src="images/common/header.png" style="width:100%;" id="header_img"/>
				</div>
			</div>
			<div id="contentDiv" style="width:1000px;height:auto;margin:0 auto;background-color: #fff;border:1px solid #dedede;position:relative;z-index:10;overflow: hidden;"></div>
			<div id="foot" style="width:1000px;height:80px;margin:0 auto;text-align: center;">
				<label style="font-family: 'Microsoft YaHei';font-size:18px;height:80px;line-height:80px;color:#afafaf;">重庆理工大学版权所有</label>
			</div>
		</div>
		<script type="text/javascript">
			var modeInfo = '<%=info%>';
			var userName = '<%=userName%>';
			var operatorId = '<%=operatorId%>';
			var basePath = '<%=basePath%>';
		</script>
		<script type="text/javascript" src="js/common.js"></script>
	</body>
</html>
