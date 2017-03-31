<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.SessionUtil"%>
<%@ page import="com.cqut.util.ServiceLoader"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	SessionUtil sessionUtil=new SessionUtil();
	String operatorId=sessionUtil.getOperatorId(request);
	String getOperatorName=sessionUtil.getOperatorName(request);
	String operatorCode = (String) request.getSession().getAttribute("operatorId");
	
	ServiceLoader serviceLoader=new ServiceLoader("ExpertService,ExpertTargetDistributeService");
	JQueryLoader loader=new JQueryLoader("selfTable,jqGrid,dwr,"+
										"dialog,watermark,tips,dialogNew"+
										"jAlert,noData,searchBox,pageBasic,"+
										"dropDownBox,dialogNew,wordStyle,userDialogValid");
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		
		<title>指定评委</title>
		<link rel="stylesheet" href="css/module/designExpert/designExpert.css" />
		<meta http-equiv="X-UA-Compatible" content="IE=100"/> <!-- IE8 mode -->
		<script type="text/javascript">
			var basePath ="<%=basePath%>";
    	</script>

	</head>
	<body style="background: transparent;overflow:auto">
		<div id="dialog-modal" title="指定评委" style="display: block;width:810px;margin:0px;padding:0px;overflow-x:hidden;">
			<table style="width: 100%;">
			<tr>
					<td>
						<div class="common">
							<div style="font-size: 12px;margin:0px 0 10px 5px;">
								<label>评委姓名:</label>
								<input type="text" id="teacherSearch" onkeydown="enterEvent(event,id)"/>
								<button  id="op_search" onclick="quickSearch();" style="border: none;">
									搜索
								</button>
							</div>
						</div>
					</td>
				</tr>
			
				<tr>
					<td>
							<div id="MainArea" style="width: 94%;margin-left:3%;">
								<table id="teacherTable" border="0" width="100%"></table>
								<div id="teacherPager"></div>
							</div>
					</td>
				</tr>
				
				
				<!-- 选择后的专业和专家 -->
				<tr>
					<td>
						<div class="common">
							<div class="selectName"></div>
							<div class="selectTitleName"><span style="margin-left: 10px;float:left;">已选择评委</span></div>
							<div style="float:right;margin-top:5px;font-family: Microsoft YaHei;font-size: 13px;color: #333333;cursor:pointer;" onclick="deleteAll()">|清空|</div>
						</div>
						
					</td>
				</tr>
				<tr>
					<td>
						<div class="selectArea common" id="selectedPeople"></div>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript" src='js/module/arrangeExpertManager/arrange.js'></script>
	</body>
</html>
