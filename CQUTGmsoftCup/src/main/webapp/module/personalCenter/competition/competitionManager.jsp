<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	JQueryLoader loader=new JQueryLoader("dwr,dialog,tips,mask");
	ServiceLoader serviceLoader=new ServiceLoader("TeamService,CrewService,ProjectService");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="<%=basePath%>" />
    <title>我的信息</title>
	<%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=serviceLoader.getServiceFilesStr()%>
	<script type="text/javascript">
		var operatorId = "${operatorId}";
	</script>
	<link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/manager.css"/>
		<style type="text/css">
			.submit{
				color: #ffffff;
			    cursor: pointer;
			    height: 40px;
			    line-height: 0px;;
			    margin-top: 20px;
			    width: 160px;
			}
		</style>
  </head>
 	<body ng-controller="controller">
 		<div class="header">
    <h1>团队管理</h1>
    <hr class="title"></hr>
</div>

<div class="center">

	<div id="center"></div>
	
  </div>
<script type="text/javascript" src="js/module/personalCenter/competition/competitionManager.js"></script>
	</body>
</html>
