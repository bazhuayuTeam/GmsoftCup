<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,watermark,dwr,userDialogValid,commonStyle,addAndEdit,dropDownBox,dialogNew");
	ServiceLoader sl = new ServiceLoader("ExpertService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<title>编辑</title>

		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
	</head>

	<body>
		<div id="dialog-modal" class="mainBody">

			<table class="inputs">
				<tr>
					<td class="lableWord">
						登录名
					</td>
					<td>
						<input class="inputEdit" type="text" id='ZGH'
							disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						姓名
					</td>
					<td>
						<input type="text" id='name' class="inputEdit" />
						<span class="starColor">*</span>
					</td>
				</tr>

				<tr>
					<td class="lableWord">
						出生年月
					</td>
					<td>
						<input id="major" style="background-color:#fff;border:1px solid #AFAFAF" fieldtype="date" class="inputEdit" type="text" field='distributeTime' save='true' format="yyyy-MM-dd mm:ss" waterText="请输入开始日期" dateGroup="joinDate"  style="border: 1px solid #AAAAAA" disabled="disabled" />
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						专业领域
					</td>
					<td>
						<input type="text" class="inputEdit" id='education' />
					</td>
				</tr>
				
				<tr>
					<td class="lableWord">
						电话
					</td>
					<td>
						<input type="text" class="inputEdit" id='phone' />
					</td>
				</tr>
			</table>
		</div>

		<script type="text/javascript"
			src="js/module/expertManager/expertEdit.js">
</script>
	</body>
</html>