<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,dialogNew,userDialogValid,addAndEdit,dropDownBox,verification");
ServiceLoader serviceLoader=new ServiceLoader("CodeTableService");
String operatorId = (String) request.getSession().getAttribute("operatorId");
if(null == operatorId){
	out.println("请先登录!");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<%=loader.getCssFilesStr()%>
	    <%=loader.getJsFilesStr()%>
	    <%=serviceLoader.getServiceFilesStr()%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>编辑部门</title>
	</head>
	<body>
		<div id="dialog-modal" title="编辑"	style='min-height: 90%; margin: 0 auto; overflow-x: hidden; overflow-y: auto'>
			<div class="hint" id="hint"></div>
				<table class="inputs">
					<tr>
						<td class="lableWord">部门代号</td>
						<td>
							<input class="inputEdit" id="academicCode" disabled="true"/>
						</td>
						<td><span class="starColor">*</span></td>
					</tr>
					<tr>
						<td class="lableWord">部门名称</td>
						<td>
							<input class="inputEdit" type="text" id="academicName"/>
						</td>
						<td><span class="starColor">*</span></td>
					</tr>
					<tr>
						<td class="lableWord">部门简称</td>
						<td>
							<input class="inputEdit" type="text" id="academicShort"/>
						</td>
						<td><span class="starColor">*</span></td>
					</tr>
					<tr>
						<td class="lableWord">部门类型</td>
						<td>
							<select id='academicType' class="select"></select>
						</td>
					</tr>
				</table>
		</div>
	<script type="text/javascript" src='<%=path%>/js/module/academic/academicEdit.js'></script>
	</body>
</html>
