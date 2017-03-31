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
			"jqGrid,dialog,dwr,dialogNew,userDialogValid,verification,util,timepicker,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
			"GameService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>编辑</title>
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
	</head>
	<body>
		<div id="dialog-modal" title="编辑">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">大赛名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='name' field='modulename' save='true'/><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">年份</td>
						<td>
							<input class="inputEdit" type="text" id='year'/><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">报名开始时间</td>
						<td>
							<input id="startTime" style="background-color:#fff" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" waterText="请输入开始日期" dateGroup="joinDate"  style="border: 1px solid #AAAAAA" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">报名截止时间</td>
						<td>
							<input id="endTime" style="background-color:#fff" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" waterText="请输入开始日期" dateGroup="joinDate"  style="border: 1px solid #AAAAAA" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>				
					</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameManager/gameEdit.js'></script>
	</body>
	
</html>