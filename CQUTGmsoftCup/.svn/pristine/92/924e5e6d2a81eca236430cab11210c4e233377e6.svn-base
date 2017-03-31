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
			"jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,addAndEdit,dropDownBox,userDialogValid");
	ServiceLoader serviceLoader = new ServiceLoader(
	"GameService,CodeTableService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>新增</title>
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
	</head>
	<body>
		<div id="dialog-modal" title="新增">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">大赛名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='game' field='modulename' save='true'/>
						</td>
					</tr>
					<tr>
						<td class="lableWord">竞赛类别</td>
						<td>
							<select class="inputEdit" id="type"></select>
						</td>
					</tr>			
					</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/gameStepManager/gameAdd.js'></script>
	</body>
	
</html>