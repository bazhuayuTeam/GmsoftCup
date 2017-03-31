<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	JQueryLoader loader = new JQueryLoader("dwr,watermark");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="<%=basePath%>" />
<title>个人中心</title>
<%=loader.getCssFilesStr()%>
<%=loader.getJsFilesStr()%>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<script type='text/javascript' src='<%=path%>/dwr/interface/UserService.js'></script>
<style type="text/css">
.labelWord{
	width:29%;
	text-align: right;
	height: 60px;
	padding-right:5px; 
	font-size: 15px;
}
.inputEdit{
	width:41%;
	height: 60px;
	text-align: left;
}
.warn{
	color:red;
	font-size: 14px;
	padding-left: 25px;
}
.warn_img{
	height:15px;
	margin-top: 4px;
 			position: absolute;
}
#log{
	height:30px;
	font-size: 17px;
	font-family: 'Microsoft YaHei';
	border-bottom: 2px solid #52acea;
	width:-moz-max-content;
	color:#52acea;
	position: absolute;
	float: left;
	margin-top: 30px;
}
</style>
</head>
<body style="width:100%">

	<div class="header" style="border-bottom: 1px solid #afafaf;height:60px;padding-top: 0px;">
	    <div id="log"><h1>用户登陆</h1></div>
	    <div id="tixing" style="position: absolute;margin-left: 800px;">
		    <div style="height:150px;z-index:10;position: relative;">
		    	<div style="width:190px;height:50px;line-height: 70px;text-align: center;">没有账号？</div>
		    	<div style="width:190px;text-align: center;"><a style="color:#52acea;text-decoration: underline;cursor: pointer;" onclick="goRegister();">前往注册&gt;&gt;</a></div>
		    </div>
		    <img style="margin-top: -150px;position: absolute;z-index: 5;" style="height:150px;" src="images/login/tishi.png" />
	    </div>
	</div>

	<!-- <div class="header" style="border-bottom: 1px solid #afafaf;margin-top: 20px;">
		    <h1 style="font-size: 17px;font-family: 'Microsoft YaHei';">用户登录</h1>
		    <hr class="title" style="border: 1px solid #52acea;"></hr>
		</div> -->
	<div class="center" style="margin-bottom: 30px;">
		<table>
			<tr>
				<td class="labelWord">账号：</td>
				<td class="inputEdit">
					<input type="text" class="input" id="userName" onFocus="focusFun(1);"/>
				</td>
				<td class="error"><span id="username_error" class="error_span"></span></td>
			</tr>
			<tr>
				<td class="labelWord">密码：</td>
				<td class="inputEdit">
					 <input type="password" class="input" id="password" onFocus="focusFun(2);"/>
				</td>
				<td class="error"><span id="password_error" class="error_span"></span></td>
			</tr>
			<tr>
				<td class="labelWord">验证码：</td>
				<td class="inputEdit">
					 <input type="text" class="input" id="validCode" style="width:150px;" onFocus="focusFun(3);"/>
					 <img src="kaptcha.jpg" style="margin-left:20px;height:40px;width:150px;position: absolute;" id="kaptcha"/>
				</td>
				<td class="error"><span id="validCode_error" class="error_span"></span></td>
			</tr>
			<tr>
				<td class="labelWord">&nbsp;</td>
				<td class="inputEdit">
					<input type="button" value="登录" class="submit bule" onclick="login();" style="width:330px;"/>
				</td>
				<td class="error"></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#kaptcha").click(function(event){
				$("#kaptcha").attr("src","kaptcha.jpg?t=" + event.timeStamp);
			});
		});
		function link() {
			window.parent.childLink("login");
		}

		function login() {
			var userName = $("#userName").val();
			var password = $("#password").val();
			var validCode = $("#validCode").val();
			var param = new Array();
			param[0] = validCode;
			param[1] = userName;
			param[2] = password;
			if(userName==""||userName==null){
				setWran("#username_error","请输入账号");
				return;
			}
			if(password==""||password==null||password.length<8){
				setWran("#password_error","密码错误");
				return;
			}
			if(validCode==""||validCode==null){
				setWran("#validCode_error","请输入验证码");
				return;
			}
			UserService.loginValidate(param, function(data) {
				if(data == "userError"){
					setWran("#username_error","用户不存在");
					$("#kaptcha").attr("src","kaptcha.jpg?t=" + event.timeStamp);
				}else if(data == "upasswordError"){
					setWran("#password_error","密码错误");
					$("#kaptcha").attr("src","kaptcha.jpg?t=" + event.timeStamp);
				}else if(data == "validCodeError"){
					setWran("#validCode_error","验证码错误");
					$("#kaptcha").attr("src","kaptcha.jpg?t=" + event.timeStamp);
				}else if(data == "success"){
					link();
				}
			});
		}
		
		function focusFun(data){
			switch (data) {
				case 1:
					$("#username_error").hide();
					break;
				case 2:
					$("#password_error").hide();
					break;
				case 3:
					$("#validCode_error").hide();
					break;
			}
		}
		
		function goRegister(){
			location.href = basePath + "module/common.jsp?info=register";
		}
		
		function setWran(selecter,data){
			$(selecter).show();
			$(selecter).html("<img src=\"images/common/warn.png\" class=\"warn_img\"/><label class=\"warn\">" + data + "</label>");
		}
	</script>
</body>
</html>
