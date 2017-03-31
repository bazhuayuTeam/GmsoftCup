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
		.menu{
			margin-top: 35px;
		}
		.menu_item{
			height:40px;
			text-align:center;
			line-height: 40px;
			cursor:pointer;
			font-family: "Microsoft YaHei";
		}
		.menu_title{
			height:40px;
			line-height: 40px;
			font-family: "Microsoft YaHei";
		}
		a{
			text-decoration: none;
			font-size: 15px;
			font-family: "黑体";
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
								<a href="javascript:void(0);" class="head-link" id="userName"></a>
								<span style="color:#fff">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
								<a href="javascript:void(0);" class="head-link" id="pass">注销</a>
								<span style="color:#fff">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
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
			<div id="contentDiv" style="width:1000px;height:500px;margin:0 auto;background-color: #fff;border:1px solid #dedede;position:relative;z-index:10;overflow-y: none;">
				<div id="contentDiv">
					<div id="col_side" style="width:170px;float:left;margin:0px;">
						<div style="margin-bottom: 30px;" class="menu">
							<div class="menu_title">
								<span style="position: relative;margin-left: 25px;top:3px;"><img src="images/common/user.png" style="height:23px;"/></span>
								<span style="position: relative;margin-left: 5px;"><label style="height:40px;line-height: 40px;font-size:18px;align:center;color:#2ea0ef;">个人中心</label></span>
							</div>
							<div class="menu_item" id="info" style="background-color: #52acea;color: #fff;" onclick="showClick('module/personalCenter/userInfo/userInfoManager.jsp','info');">
								<label  id="infoLink" style="color:#fff;">我的信息</label>
							</div>
							<%--<div class="menu_item" id="message" onclick="showClick('module/personalCenter/message/messageManager.jsp','message');">
								<label  id="messageLink" style="color:#555;">我的消息</label>
							</div>
							--%><div class="menu_item" id="security" onclick="showClick('module/personalCenter/security/securityManager.jsp','security');">
								<label id="securityLink" style="color:#555;">账户安全</label>
							</div>
						</div>
						<div style="margin-bottom: 30px;" class="menu">
							<div class="menu_title">
								<span style="position: relative;margin-left: 25px;top:3px;"><img src="images/common/game.png" style="height:23px;"/></span>
								<span style="position: relative;margin-left: 5px;"><label style="height:40px;line-height: 40px;font-size:18px;align:center;color:#2ea0ef;">我的竞赛</label></span>
							</div>
							<div class="menu_item" id="game" onclick="showClick('module/personalCenter/competition/mycompetition.jsp','game');"><label id="gameLink" style="color:#555;">我要参赛</label></div>
							<div class="menu_item" id="team" onclick="showClick('module/personalCenter/competition/competitionManager.jsp','team');"><label id="teamLink" style="color:#555;">团队管理</label></div>
						</div>
					</div>
					<div id="frame_div" style="width:829px;height:500px;float:right;border-left:1px solid #dedede;position:relative;overflow: auto;"></div>
				</div>
			</div>
			<div id="foot" style="width:1000px;height:80px;margin:0 auto;text-align: center;">
				<label style="font-family: 'Microsoft YaHei';font-size:18px;height:80px;line-height:80px;color:#afafaf;">重庆理工大学版权所有</label>
			</div>
		</div>
		<script type="text/javascript">
			var info = '<%=info%>';
			var userName = '<%=userName%>';
			var operatorId = '<%=operatorId%>';
			var basePath = '<%=basePath%>';
		</script>
		<script type="text/javascript" src="js/module/personalCenter/personalManager.js"></script>
	</body>
</html>
