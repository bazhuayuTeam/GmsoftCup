<%@ page language="java" import="java.util.*,com.cqut.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr,watermark,mask,validate");
	String userId = (String) request.getSession().getAttribute(OperatorUtil.OPERATOR_ID);
 	Integer roleType = (Integer) request.getSession().getAttribute(OperatorUtil.OPERATOR_TYPE);
	if (null == userId) {
		out.println("请先登录!");
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <title>我的信息</title>
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<script>var roleType ='<%=roleType%>';</script>
	<script>var userId ='<%=userId%>';</script>
    <link rel="stylesheet" href="css/common.css"/>
	<script  src="js/module/enroll/jquery-labelauty.js" type="text/javascript" language="JavaScript"></script>
	<script type='text/javascript' src='dwr/interface/UserService.js'></script> 
	<link rel="stylesheet" href="css/jquery-labelauty.css"/>
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
  <body>
    <div class="header">
	  	<h1>基本信息</h1>
	  	<hr class="title"></hr>
	</div>
	<% if(roleType == 0) { %>
  	<div class="center" style="text-align:center;margin:0 auto;padding-top: 10px !important;">
  		<center>
  			<!--学生 -->
  				<div style="width:440px;text-align:center;margin:0 auto;font-size: 14px;">
		        <ul style="width:440px;">
		            <li>
		                <label class="label">学号：</label>
		               	<input name="userID" id="userID" type="text" class="input" disabled="disabled" style="height:35px;"/>
		            </li>
		            <li>
		                <label class="label">姓名：</label>
		                <input name="name" id="name" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(1);" onBlur="mouseOut(1);"/>
		                <span id="name_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">学院：</label>
		                <select name="academy" id="academy" class="select" style="height:35px;" onchange="findDiscipline()">
		                </select>
		            </li>
		            <li>
		                <label class="label">专业：</label>
		                <select name="major" id="major" class="select" style="height:35px;">
		                </select>
		            </li>
		            <li>
		                <label class="label">学历：</label>
		               	<select name="education" id="education" class="select" style="height:35px;">
		               	</select>
		            </li>
		            <li>
		                <label class="label">邮箱：</label>
		                <input name="email" id="email" type="text" class="input" style="height:35px;" onFocus="focusFun(2);" onBlur="mouseOut(2);"/>
		                <span id="email_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">电话号码：</label>
		                <input name="phone" id="phone" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(3);" onBlur="mouseOut(3);"/>
		                <span id="phone_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">QQ：</label>
		                <input name="QQ" id="QQ" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(4);" onBlur="mouseOut(4);"/>
		                <span id="QQ_error" class="error_span"></span>
		            </li>
		        </ul>
	
		        <div>
		            <input type="submit" value="确定" class="submit" style="height:35px;line-height: 0px;" onclick="saveUser()"/>
		        </div>
	        </div> 
	     </center>
  	</div>
  	<!--老师 -->
	<%} else if(roleType == 1) {%>
	   <div class="center" style="height:450px;text-align:center;margin:0 auto;padding-top: 10px !important;">
  		<center>
  				<div style="width:440px;text-align:center;margin:0 auto;font-size: 14px;">
  
		        <ul style="width:440px;">
		            <li>
		                <label class="label">职工号：</label>
		               	<input name="userID" id="userID" type="text" class="input" disabled="disabled" style="height:35px;"/>
		            </li>
		            <li>
		                <label class="label">姓名：</label>
		                <input name="name" id="name" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(1);" onBlur="mouseOut(1);"/>
		            	<span id="name_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">学院(单位)：</label>
		                <select name="academy" id="academy" class="select" style="height:35px;">
		                </select>
		            </li>
		            <li>
		                <label class="label">职称：</label>
		                <select name="profession" id="profession" class="select" style="height:35px;">
		                </select>
		            </li>
		            <li>
		                <label class="label">邮箱：</label>
		                <input name="email" id="email" type="text" class="input" style="height:35px;" onFocus="focusFun(2);" onBlur="mouseOut(2);"/>
		            	<span id="email_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">电话号码：</label>
		                <input name="phone" id="phone" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(3);" onBlur="mouseOut(3);"/>
		            	<span id="phone_error" class="error_span"></span>
		            </li>
		            <li>
		                <label class="label">QQ：</label>
		                <input name="QQ" id="QQ" type="text" class="input" maxlength="16" style="height:35px;" onFocus="focusFun(4);" onBlur="mouseOut(4);"/>
		            	<span id="QQ_error" class="error_span"></span>
		            </li>
		        </ul>
	
		        <div>
		            <input type="submit" value="确定" class="submit" style="height:35px;line-height: 0px;" onclick="saveUser()"/>
		        </div>
	        </div> 
	     </center>
  	</div>
	<%} %>
	 <script type="text/javascript" src="js/module/myInformation/myInformation.js"></script>
	  <script language="JavaScript" type="application/javascript">
	    $(function(){
	        $(':input').labelauty();
	    });
	  </script>
  </body>
 
</html>
