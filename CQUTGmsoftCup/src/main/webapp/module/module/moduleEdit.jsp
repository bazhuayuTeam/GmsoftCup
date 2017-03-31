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
			"ModuleService,CodeTableService,SystemFileService");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>模块编辑</title>
		<script type="text/javascript" src="js/util/StringUtil.js"></script>
		<style type="text/css">
			body {
				padding: 0;
				margin: 0;
			}
			
			.table_head {
				height: 20px;
			}
			
			.table_input{
				text-align:left;
			}
			
			.propertyName {
				width: 105px;
				text-align: right;
			}
			
			.input {
				width: 150px;
			}
			
			.select {
				width: 156px;
			}
		</style>

	</head>
	<body>
		<div id="dialog-modal" title="编辑模块">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<table width="90%">
					<tr>
						<td class="lableWord">名称</td>
						<td style="width: 170px;">
							<input class="inputEdit" type="text" id='field_modulename' field='modulename' save='true'/>
						</td>
					</tr>
					<tr>
						<td class="lableWord">URL</td>
						<td>
							<input class="inputEdit" type="text" id='field_url' field='url' save='true'/><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">模块类型</td>
						<td>
							<select class="inputEdit"  id='field_moduletype' showName="所属模块" field='moduletype' save="true" referText='ct_codetablename' refer='ct_codeType' referClass='codetable' referCondition="codetype like '%ModuleType%'"></select><span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">是否展示</td>
						<td>
							<select class="inputEdit" id='field_showMenuPage' field='showMenuPage' save='true'>
							<option value='true'>
								展示
							</option>
							<option value='false'>
								不展示
							</option>
						</select>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript" src='<%=path%>/js/module/module/moduleEdit.js'></script>
	</body>
	
</html>