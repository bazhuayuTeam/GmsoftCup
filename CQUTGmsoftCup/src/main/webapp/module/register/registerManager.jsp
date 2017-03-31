<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr,watermark");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="<%=basePath%>" />
    <title>2016年重庆理工大学“大家杯”软件创新、创意大赛</title>
	<%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/jquery-labelauty.css"/>
	<script src="js/module/enroll/jquery-labelauty.js" type="text/javascript" language="JavaScript"></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/UserService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/AcademicService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/DisciplineService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/CodeTableService.js'></script>
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
		.error{
			height: 60px;
			width:30%;
			padding-left: 5px;
		}
		.center li {
			margin-top: 0px;
		}
		.remind,.remind1{
			padding-top: 18px;
			position: absolute;
			padding-left: 10px;
			color:red;
		}
		#profession_tr{
			display: none;
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
		.input{
			font-size: 15px;
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
		    <div id="log"><h1>用户注册</h1></div>
		    <div id="tixing" style="position: absolute;margin-left: 800px;">
			    <div style="height:150px;z-index:10;position: relative;">
			    	<div style="width:190px;height:50px;line-height: 70px;text-align: center;">已有账号？</div>
			    	<div style="width:190px;text-align: center;"><a style="color:#52acea;text-decoration: underline;cursor: pointer;" onclick="toLogin();">直接登录&gt;&gt;</a></div>
			    </div>
			    <img style="margin-top: -150px;position: absolute;z-index: 5;" style="height:150px;" src="images/login/tishi.png" />
		    </div>
		</div>
		<div class="center" style="margin-bottom: 30px;">
			<table>
				<tr>
					<td class="labelWord">角色：</td>
					<td class="inputEdit">
						 <ul class="dowebok">
		                    <li><input type="radio" value="0" name="type" data-labelauty="学生" Checked="checked" onchange="changeToStudent();"/></li>
		                    <li><input type="radio" value="1" name="type" data-labelauty="教师" onchange="changeToTeacher();"/></li>
		                </ul>
					</td>
					<td class="error"></td>
				</tr>
				<tr>
					<td class="labelWord">账号：</td>
					<td class="inputEdit">
						<input type="text" style="width:325px;" class="input" id="userId" maxlength="20" onFocus="focusFun(1);" onBlur="mouseOut(1);"/>
		                <label class="remind">*</label>
					</td>
					<td class="error"><span id="userId_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">姓名：</td>
					<td class="inputEdit">
						<input type="text" style="width:325px;" class="input" id="name" maxlength="20" onFocus="focusFun(2);" onBlur="mouseOut(2);"/>
		                <label class="remind">*</label>
					</td>
					<td class="error"><span id="name_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord" id="acadmic">学院：</td>
					<td class="inputEdit">
						<select id="academy" onchange="initDisciplineSelect();" style="width: 337px;">
	                	</select>
					</td>
					<td class="error"></td>
				</tr>
				<tr id="major_tr">
					<td class="labelWord">专业：</td>
					<td class="inputEdit">
						<select id="major" style="width: 337px;">
	                	</select>
					</td>
					<td class="error"></td>
				</tr>
				<tr id="profession_tr">
					<td class="labelWord">职称：</td>
					<td class="inputEdit">
						<select id="profession" style="width: 337px;">
	                	</select>
					</td>
					<td class="error"></td>
				</tr>
				<tr id="education_tr">
					<td class="labelWord">学历：</td>
					<td class="inputEdit">
						<select id="education" style="width: 337px;">
	                	</select>
					</td>
					<td class="error"></td>
				</tr>
				<tr>
					<td class="labelWord">密码：</td>
					<td>
						<input type="password" style="width:325px;" class="input" id="password1" maxlength="16"  onFocus="focusFun(3);" onBlur="mouseOut(3);"/>
						<label class="remind">*</label>
					</td>
					<td class="error"><span id="password1_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">确认密码：</td>
					<td>
						<input type="password" style="width:325px;" class="input" id="password2" maxlength="16"  onFocus="focusFun(4);" onBlur="mouseOut(4);"/>
						<label class="remind">*</label>
					</td>
					<td class="error"><span id="password2_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">邮箱：</td>
					<td>
						<input type="text" style="width:325px;" class="input" id="email"  onFocus="focusFun(5);" onBlur="mouseOut(5);"/>
						<label class="remind1">*</label>
					</td>
					<td class="error"><span id="email_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">电话号码：</td>
					<td>
						<input type="text" style="width:325px;" class="input" id="phone" maxlength="16"  onFocus="focusFun(6);" onBlur="mouseOut(6);"/>
						<label class="remind1">*</label>
					</td>
					<td class="error"><span id="phone_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">QQ：</td>
					<td>
						<input type="text" style="width:325px;" class="input" id="QQ" maxlength="16"  onFocus="focusFun(7);" onBlur="mouseOut(7);"/>
						<label class="remind1">*</label>
					</td>
					<td class="error"><span id="QQ_error" class="error_span"></span></td>
				</tr>
				<tr>
					<td class="labelWord">&nbsp;</td>
					<td>
						<input type="button" value="注册" class="submit bule" onclick="register();" style="width:335px;"/>
					</td>
					<td class="error"></td>
				</tr>
			</table>
		</div>
		<script type="text/javascript" src="js/module/register/registerManager.js"></script>
	</body>
</html>
