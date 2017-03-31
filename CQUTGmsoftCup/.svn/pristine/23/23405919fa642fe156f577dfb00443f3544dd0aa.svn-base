<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,addAndEdit,dropDownBox,userDialogValid");
ServiceLoader serviceLoader = new ServiceLoader(
		"RoleService");
String operatorId = (String) request.getSession().getAttribute("operatorId");
if(null == operatorId){
	out.println("请先登录!");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
	    <%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新增角色</title>
		<style>
.propertyName {
	width: 100px;
	text-align: right;
	font-size:12px;
}
</style>
	</head>
	<body>
		<div id="dialog-modal" title="角色信息"
			style='min-height: 90%; margin: 0 auto; overflow-x: hidden; overflow-y: auto'>
			<br/>
			<div style="padding: 12px;font-size:12px;">
				<table width="90%">
					<tr>
						<td class="lableWord">角色名称:</td>
						<td>
							<input type="text" id='field_roleName' style="width: 150px;font-size:12px;" class="inputEdit"></input>
						</td>
					</tr>
				</table>
			</div>
		</div>
	<script type="text/javascript" src="js/module/roleManager/roleAdd.js"></script>
	</body>
</html>
