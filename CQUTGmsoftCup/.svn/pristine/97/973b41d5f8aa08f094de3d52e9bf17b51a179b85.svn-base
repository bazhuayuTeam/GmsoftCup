<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,watermarkask,watermark");
	String userId = "1";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <title>账号安全</title>
    <link rel="stylesheet" href="css/common.css">
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<script>var userId ='<%=userId%>';</script>
	<!-- <script src="js/jquery/jquery-1.8.3.min.js" type="text/javascript" language="JavaScript"></script> -->
	<script src="js/module/enroll/jquery-labelauty.js" type="text/javascript" language="JavaScript"></script>
	<script type='text/javascript' src='dwr/interface/UserService.js'></script> 
	<link rel="stylesheet" href="css/jquery-labelauty.css">
	<link rel="stylesheet" type="text/css" href="css/module/myInformation/myInformation.css">

  </head>
  
  <body>
  	<div class="center" style="height:400px;text-align:center;margin:0 auto;padding-top: 100px !important;">
  	<center>
  			<!--学生 -->
  				<div style="width:440px;text-align:center;margin:0 auto;font-size: 14px;">
		        <ul style="width:440px;">
		            <li>
		                <label class="label">当前密码：</label>
		                <input name="nowPassword" id="nowPassword" type="password" class="input" style="height:35px;">
		            </li>
		            <li>
		                <label class="label">新密码：</label>
		                <input name="newPassword" id="newPassword" type="password" class="input" style="height:35px;">
		            </li>
		            <li>
		                <label class="label">确认密码：</label>
		                <input name="secondPassword" id="secondPassword" type="password" class="input" style="height:35px;">
		            </li>
		        </ul>
	
		        <div>
		            <input type="submit" value="保存" class="submit" style="height:35px;line-height: 0px;" onclick="updatePassword()">
		        </div>
	        </div> 
	     </center>
  	</div>
  </body>
  <script type="text/javascript" src="js/module/accountSafety/accountSafety.js"></script>
  <script language="JavaScript" type="application/javascript">
    $(function(){
        $(':input').labelauty();
    });
  </script>
</html>
