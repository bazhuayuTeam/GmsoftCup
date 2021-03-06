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
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,util,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
	"GameService,CodeTableService,StandardVersionService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>新增竞赛任务</title>
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
	</head>
	<body>
		<div id="dialog-modal" title="新增竞赛任务">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">竞赛任务</td>
						<td style="width: 170px;">
							<select class="inputEdit" type="text" id='type' field='modulename' save='true'></select>
						</td>
					</tr>
					<%--<tr>
						<td class="lableWord">指标版本</td>
						<td style="width: 170px;">
							<select class="inputEdit" type="text" id='version' field='modulename' save='true'></select>
						</td>
					</tr>
					--%><tr>
						<td class="lableWord">提交作品开始时间</td>
						<td>
							<input id="startTime" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" waterText="请输入开始日期" dateGroup="joinDate"  style="border: 1px solid #AAAAAA" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>	
					<tr>
						<td class="lableWord">提交作品结束时间</td>
						<td>
							<input id="endTime" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" value="" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" waterText="请输入开始日期" dateGroup="joinDate" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>			
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameStepManager/taskAdd.js'></script>
	</body>
	
</html>