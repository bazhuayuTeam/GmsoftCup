<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String operatorId = (String) request.getSession().getAttribute(
			"operatorId");
	//if(null == operatorId){
	//out.println("请先登录!");
	//return;
	//}
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,dialog,userDialogValid,timepicker,util,commonStyle,addAndEdit,dialogNew,nicescroll");
	ServiceLoader serviceLoader = new ServiceLoader("StandardVersionService,GameStepDetailService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>设置评审时间</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
	</head>
	<body>
		<div id="dialog-modal" title="设置评审时间">
			<table class="inputs">
				<tr>
					<td class="labelWord">
						大赛名称
					</td>
					<td>
						<input class="inputEdit" type="text" id='gameName'
							disabled="disabled" 　readOnly="true" />
					</td>
				</tr>
				<tr>
					<td class="labelWord">
						竞赛阶段
					</td>
					<td>
						<input type="text" id='gameStep' class="inputEdit"
							disabled="disabled" 　 readOnly="true" />
					</td>
				</tr>
				<tr>
					<td class="labelWord">
						竞赛任务
					</td>
					<td>
						<input class="inputEdit" type="text" id='gameMission'
							disabled="disabled" 　readOnly="true" />
					</td>
				</tr>
				<tr>
						<td class="lableWord">指标版本</td>
						<td style="width: 170px;">
							<select style="background-color:#fff;border:1px solid #AFAFAF;" class="inputEdit" type="text" id='version' field='modulename' save='true'></select>
						</td>
					</tr>
				<tr>
					<td class="labelWord">
						评审开始时间
					</td>
					<td>
						<input id="startTime" style="background-color:#fff;border:1px solid #AFAFAF;"
							fieldtype="timepicker" class="inputEdit" type="text"
							field='distributeTime' save='true' format="yyyy-MM-dd mm:ss"
							waterText="请输入开始日期" dateGroup="joinDate"
							 disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
				<tr>
					<td class="labelWord">
						评审结束时间
					</td>
					<td>
						<input id="endTime" style="background-color:#fff;border:1px solid #AFAFAF;"
							fieldtype="timepicker" value="" class="inputEdit" type="text"
							field='distributeTime' save='true' format="yyyy-MM-dd mm:ss"
							waterText="请输入开始日期" dateGroup="joinDate" disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript"
			src='<%=path%>/js/module/reviewManagement/reviewEdit.js'>
		</script>
		<script type="text/javascript" src='js/util/niceScrollFordialog.js'>
</script>
	</body>
</html>