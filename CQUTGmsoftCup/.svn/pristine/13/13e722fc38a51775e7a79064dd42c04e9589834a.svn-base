<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,mask,dwr,tree,commonStyle,pageBasic,dialogNew");
	ServiceLoader sl = new ServiceLoader("RoleService,RoleAssignService,PermissionAssignService,ModuleService");

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<style type="text/css">
			a{text-decoration: none;}
			.contentDiv {
				position:absolute;
				top: 66px;
				margin-left: 20px;
				height: 80%;
				width: 97%;
			}
			
			.roleTreeDiv,.permissionTreeDiv {
				position:absolute;
				top:46px;
				bottom:30px;
				height: auto;
				overflow: auto;
				float:left;
			}
			.roleTreeDiv {
				width: 25%;
			}
			.permissionTreeDiv {
				
				left:25%;
				width:75%;
			}
		</style>
		<title>角色权限分配</title>
	</head>
	<body>

	        <div class="topStyle">
	    	    <div class="topTitle"  id="title">权限管理</div>
	    	</div>
	        <div class="contentDiv">
		        <div  class="roleTreeDiv">
					<ul id="roleTree" class="tree"></ul>
				</div>
				<div class="permissionTreeDiv">
					<ul id="permissionTree" class="tree"></ul>
				</div>
	        </div>
        
		<script type="text/javascript" src="js/module/rolePermissionAssign/rolePermissionAssign.js"></script>
	</body>
</html>