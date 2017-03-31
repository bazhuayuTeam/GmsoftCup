<%@ page language="java" import="java.util.*,com.cqut.util.JQueryLoader,com.cqut.util.ServiceLoader" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("dwr,jqGrid,nicescroll");
ServiceLoader serviceLoader=new ServiceLoader("MessageReceiveService,MessageService,QuestionService,RoleService,TaskDetailService,TaskService,DesktopService");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%=loader.getJsFilesStr() %>
    <%=loader.getCssFilesStr() %>
    <%=serviceLoader.getServiceFilesStr()%>
    <title>桌面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/module/desktop/desktop.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/module/desktop/theme.min.css"/>
  </head> 
  <body>
	<div class="mainContent">
	   <span class="area_left area">
	       <div  class='area_left_prompt'>
	          <div class='area_left_currentStatus'></div>
	          <div class='area_left_recentFestival'></div>
	          <div class='area_left_finishStatus'></div>
	       </div>
	       <div id="calender" class="area_left_calender ">
	          <jsp:include page="calendar.jsp"/>          
	       </div>
	       <div id="schedule" class="area_left_schedule area_left_common" ></div>       
	       <div id="question" class="area_left_question area_left_common"> </div>
	   </span>
	   <span class="area_right area">
	       <div id="inbox" class="area_right_inbox area_right_common"></div>
	       <div id="outbox" class="area_right_outbox area_right_common"></div>
	   </span>
	</div>
	<script type="text/javascript">
	    var basePath="<%=basePath%>";
	    var ZGH = "${ZGH}";
	    var roleID = "${roleID}";
		var academicID="${academicID}";
		var disciplineID="${disciplineID}";
	</script>
	<script  type="text/javascript" src="js/module/desktop/desktop.js"></script>
	<script  type="text/javascript" src="js/module/desktop/lunarDay.js"></script>	
  </body>
</html>
