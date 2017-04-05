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
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,util,file,commonStyle,addAndEdit,dropDownBox");
	ServiceLoader serviceLoader = new ServiceLoader(
	"GameStepService,CodeTableService,StandardVersionService");
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
						<td class="lableWord">赛制流程</td>
						<td style="width: 170px;">
							<select id="processID">
							</select>
						</td>
					</tr>
					<tr>
						<td class="lableWord">时间</td>
						<td>
							<input id="gameTime" placeholder="请输入比赛时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td class="lableWord">评审标准</td>
						<td style="width: 170px;">
							<select id="standardVersionName">
							</select>
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">评审开始时间</td>
						<td>
							<input id="checkStartTime" placeholder="请输入评审开始时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">评审结束时间</td>
						<td>
							<input id="checkEndTime" placeholder="请输入评审结束时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td class="lableWord">是否需要提交作品</td>
						<td style="width: 170px;">
							<select id="works">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">提交作品开始时间</td>
						<td>
							<input id="startTime" placeholder="请输入提交作品开始时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">提交作品结束时间</td>
						<td>
							<input id="endTime" placeholder="请输入提交作品结束时间" style="background-color:#fff;border:1px solid #AFAFAF;" fieldtype="timepicker" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" disabled="disabled" />
						</td>
					</tr>
					<tr class="forStage">
						<td class="lableWord">作品模板</td>
						<td>
							<button id="fileID">点击上传</button>
						</td>
					</tr>		
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameStepManager/gameEdit.js'></script>
	</body>
	
</html>