<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader=new JQueryLoader("dwr,dialog,jqGrid,userDialogValid,file,commonStyle,addAndEdit,dialogNew,nicescroll");
	ServiceLoader serviceLoader=new ServiceLoader("OperatorService,AcademicService,DisciplineService,SystemFileService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>新增操作人员</title>
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
	</head>
	<body>
		<div id="dialog-modal">
			<table class="inputs">
				<tr>
					<td class="labelWord">账号</td>
					<td>
						<input class="inputEdit"  type="text"  id='personID'/>
					</td>
				</tr>
				<tr>
					<td class="labelWord">姓名</td>
					<td>
						<input class="inputEdit" type="text" id="name"/>
					</td>
				</tr>
				<tr>
					<td class="labelWord">部门</td>
					<td>
						<select id='academic'  class="inputEdit">
						</select>
					</td>
				</tr>
				<tr>
					<td class="labelWord">专业</td>
					<td>
						<select id='discipline'  class="inputEdit">
							<option value="">无</option>
						</select>
					</td>
				</tr>
			</table>		
		</div>
		<script type="text/javascript" src="js/module/operatorManager/operatorAdd.js"></script>
		<script type="text/javascript" src='js/util/niceScrollFordialog.js'></script>
	</body>
</html>