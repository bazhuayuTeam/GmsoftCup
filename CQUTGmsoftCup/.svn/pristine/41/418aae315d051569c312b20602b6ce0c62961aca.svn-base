<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,userDialogValid,dialog,commonStyle,addAndEdit,dropDownBox,dialogNew");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	ServiceLoader sl = new ServiceLoader("TargetSystemService,TargetSysVersionService");
	/*if (null == operatorId) {
		out.println("请先登录!");
		return;
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<title>修改指标版本</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
  </head>
  
  <body>
    	<div id="dialog-modal" title="修改指标版本">
			    <table  class="inputs">
					<tr>
						<td class="lableWord">版本名称:</td>
						<td >
							<input type="text"   id='targetSysVersionName'  class="inputEdit"/>
						</td>
					</tr>					
				</table>
		</div>
        <script type="text/javascript" src="js/module/targetSystemManager/targetSysVersionEdit.js"></script>
  </body>
</html>
