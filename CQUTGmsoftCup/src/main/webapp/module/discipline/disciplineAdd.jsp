<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if(null == operatorId){
		out.println("请先登录!");
		return;
	}
	JQueryLoader loader=new JQueryLoader("jqGrid,dwr,dialog,userDialogValid,timepicker,commonStyle,addAndEdit,dialogNew,nicescroll");
	ServiceLoader serviceLoader=new ServiceLoader("CodeTableService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新增专业</title>
		<%=loader.getCssFilesStr()%>
	    <%=loader.getJsFilesStr()%>
	    <%=serviceLoader.getServiceFilesStr()%>
	</head>
	<body>
		<div id="dialog-modal" title='新增'>
			<table class="inputs">
				<tr>
					<td class="labelWord">部门名称</td>
					<td >
						<input class="inputEdit"  type="text"  id='academicName'  disabled="true"　readOnly="true" />
					</td>
				</tr>
				<tr>
					<td class="labelWord">专业名称</td>
					<td>
						<input type="text" id='disciplineName'  class="inputEdit"/>
						<span class="starColor">*</span>
					</td>
				</tr>
			</table>		
		</div>
	<script type="text/javascript" src='<%=path%>/js/module/discipline/disciplineAdd.js'></script>
	<script type="text/javascript" src='js/util/niceScrollFordialog.js'></script>
	</body>
</html>
