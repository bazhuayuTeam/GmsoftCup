<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@page import="com.cqut.util.SessionUtil"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
SessionUtil sessionUtil=new SessionUtil();
String operatorId=sessionUtil.getOperatorId(request);
if(operatorId == ""){
	out.println("请登录!");
	return;
}

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,userDialogValid,suggest,commonStyle,addAndEdit,dialogNew");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
    <title>修改密码</title>
	
	<script type="text/javascript">
	var operatorId="<%=operatorId%>";
	var passWord="",d_height = 330;
	</script>
  </head>
  
  <body>

	<div class="mainBody" id="changePassWord" title="修改个人密码">
		<table class="inputs">
			<tr>
				<td class="labelWord">旧密码</td>
				<td>
					<input class="inputEdit"  type="password"  id="oldPassWord" maxlength="15" /><span class="starColor">*</span>
				</td>
			</tr>
			<tr>
				<td class="labelWord">新密码</td>
				<td>
					<input class="inputEdit" type="password" id="newPassWord_one"  maxlength="15"  onchange="checkNewPassWord();"/>
					<span class="starColor">*&nbsp;&nbsp;&nbsp;(注意4-15位)</span>
				</td>
			</tr>
			<tr>
				<td class="labelWord">确认密码</td>
				<td>
					<input class="inputEdit" type="password"  id="newPassWord_two" maxlength="15" onchange="checkNewPassWord();" /><span class="starColor">*</span>
				</td>
			</tr>
			<tr><td colspan="2"><font id="warning" color="red"></font></td></tr>
		</table>
	</div>
	<script type="text/javascript" src="js/module/manager/changePawwWord.js"></script>
  </body>
</html>
