<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
			"UserService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>添加指导老师</title>
		
	</head>
	<body>
		<div id="dialog-modal" title="添加指导老师">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">教师姓名</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='forid' field='modulename' save='true'/>
						</td>
					</tr>
					<tr>
					<label id="warn" style="color:red;display:none">该教师未注册，请确定注册后再添加!</label>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/personalCenter/competition/TeacherAddNew.js'></script>
	</body>
	
</html>