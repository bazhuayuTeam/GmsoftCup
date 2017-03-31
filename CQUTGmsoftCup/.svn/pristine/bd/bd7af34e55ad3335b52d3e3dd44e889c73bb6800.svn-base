<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr,watermark,tips");
	String userId = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_ID);
	if (null == userId) {
		out.println("请先登录!");
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>账号安全</title>
    <link rel="stylesheet" href="css/common.css"/>
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<script>var userId ='<%=userId%>';</script>
	<script type='text/javascript' src='dwr/interface/UserService.js'></script> 
	<link rel="stylesheet" type="text/css" href="css/module/myInformation/myInformation.css"/>
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<style type="text/css">
		.lableWord{
			text-align: right;
			width:80px;
			height:30px;
		}
		.inputEdit{
			width:210px;
			height:25px;
			
		}
		.selectEdit{
			width:210px;
			height:25px;
		}
		a{
			text-decoration: none;
		}
		
		.error_span {
			position: relative;
			top:-31px;
			left:450px;
			float: left;
		}
		
		.warn_img {
			position: relative;
			height : 15px;
			top: 2px;
		}
		
		.warn {
			padding-left: 4px;
			line-height: 0px !important;
			color:red;
		}
	</style>
  </head>
 	<body >
 	<div class="center" style="height:400px;text-align:center;margin:0 auto;padding-top: 100px !important;">
  	<center>
  			<!--学生 -->
  				<div style="width:440px;text-align:center;margin:0 auto;font-size: 14px;">
		        <ul style="width:440px;">
		            <li>
		                <label class="label">当前密码：</label>
		                <input name="nowPassword" id="nowPassword" type="password" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(1);" onBlur="mouseOut(1);"/>
		                <span id="nowPassword_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">新密码：</label>
		                <input name="newPassword" id="newPassword" type="password" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(2);" onBlur="mouseOut(2);"/>
		                <span id="newPassword_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">确认密码：</label>
		                <input name="secondPassword" id="secondPassword" type="password" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(3);" onBlur="mouseOut(3);"/>
		                <span id="secondPassword_error" class="error_span"></span>
		            </li>
		        </ul>
	
		        <div>
		            <input type="submit" value="保存" class="submit" style="height:35px;line-height: 0px;" onclick="updatePassword()" />
		        </div>
	        </div> 
	     </center>
  	</div>
  <script type="text/javascript" src="js/module/accountSafety/accountSafety.js"></script>
	</body>
</html>
