<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日历</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="css/module/desktop/theme.min.css" type="text/css" rel="stylesheet" />
    <link href="css/module/desktop/calendar.css" type="text/css" rel="stylesheet" />    
  </head>
  <div class="aboluo-w-700">
		<div class="aboluo-leftdiv">
			<div class="aboluo-tools">
			    <div class="aboluo-calendar-title" >Calendar</div>
			    <div class="aboluo-calendar-month"></div>
				<div class="aboluo-calendar-select-year"></div>	
			</div>
			<div id="monthOfEnglish"></div>
			<div class="aboluo-rilidiv">
				<table class="aboluo-rilitable" cellspacing="0" cellpadding="0" >
					<thead class="aboluo-rilithead">
						<tr>
							<th>一</th>
							<th>二</th>
							<th>三</th>
							<th>四</th>
							<th>五</th>
							<th>六</th>
							<th>日</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/module/desktop/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/module/desktop/script.js"></script>
</html>
