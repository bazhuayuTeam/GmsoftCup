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
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	if(null == operatorId){
		out.println("<script type='text/javascript'>");   
	    out.println("window.open('"+request.getContextPath()+"/login.jsp','_top')");   
	    out.println("</script>");
		return;
	}
	JQueryLoader loader = new JQueryLoader("jqGrid,dialog,watermark,dwr,dialogNew,userDialogValid,verification,timepicker,date,addAndEdit,dropDownBox,userDialogValid");
	ServiceLoader serviceLoader = new ServiceLoader(
			"CodeTableService,SystemFileService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>码表编辑</title>
		<script type="text/javascript" src="<%=basePath%>js/util/StringUtil.js"></script>
		<style type="text/css">
			.propertyName {width: 100px;text-align: right;font-size:12px;}
			.wid {width: 150px;font-size:12px;}
			#field_codetablename,#field_codetype{font-size:12px}
		</style>
	</head>
	<body>
		<div id="dialog-modal" style='min-height: 100%; margin: 0 auto; overflow-x: hidden; overflow-y: auto'>
			<br />
			<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">码表名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='field_codetablename' field='codetablename' save='true'  maxlength="50"/><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">码表类型</td>
						<td>
							<input class="inputEdit" type="text" id='field_codetype' field='codetype' save='true'  maxlength="100"/><span class="starColor">*</span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	<script type="text/javascript" src='<%=path%>/js/module/codetable/codeTableEdit.js'></script>
	</body>
</html>
