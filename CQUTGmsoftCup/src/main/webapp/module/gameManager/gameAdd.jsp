<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,util,commonStyle,addAndEdit,dropDownBox");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<title>新增</title>
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
		<%--<script src="js/jquery/jquery.js"></script>
		--%>
	</head>
	<body>
		<div id="dialog-modal" title="新增">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">大赛名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='name' field='modulename' save='true'/>
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
							<input id="startTime" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">报名截止时间</td>
						<td>
							<%--<input class="inputEdit"  id='endTime'/><span class="starColor">*</span>
							--%><input id="endTime" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" value="" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" /><span class="starColor">*</span>
						</td>
					</tr>				
					</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameManager/gameAdd.js'></script>
	</body>
	
</html>