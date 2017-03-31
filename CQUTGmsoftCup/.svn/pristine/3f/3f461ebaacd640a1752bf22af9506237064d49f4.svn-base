<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,watermark,dwr,userDialogValid,commonStyle,addAndEdit,dropDownBox,dialogNew");
	ServiceLoader sl = new ServiceLoader(
			"ExpertService,CodeTableService");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<title>编辑评委</title>

		<script type="text/javascript"
			src="js/module/managerEdit/managerEdit.js">
</script>
	</head>

	<body>
		<div id="dialog-modal">

			<table class="inputs">
				<tr>
					<td class="lableWord">
						姓名
					</td>
					<td>
						<input type="text" id='name' class="log" disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						出生年月
					</td>
					<td>
						<input type="text" id='birth' class="bir" disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						电话
					</td>
					<td>
						<input type="text" id='phone' class="inputEdit"
							disabled="disabled" />
						<span class="starColor">*</span>
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						学历
					</td>
					<td>
						<select id='education' class="select"></select>
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						研究领域
					</td>
					<td>
						<input type="text" class="inputEdit" id='major' />
					</td>
				</tr>
				<tr>
					<td class="lableWord">
						所属单位
					</td>
					<td>
						<input type="text" class="inputEdit" id='department' />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>