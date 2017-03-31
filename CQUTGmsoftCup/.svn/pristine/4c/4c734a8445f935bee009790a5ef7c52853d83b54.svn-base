<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,userDialogValid,dialog,commonStyle,addAndEdit,dialogNew,dropDownBox");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	ServiceLoader sl = new ServiceLoader("TargetSystemService,StandardVersionService");
	/*if (null == operatorId) {
		out.println("请先登录!");
		return;
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<title>新增版本</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<style type="text/css">
		
.searchSelect {
    background: #f6f6f6 none repeat scroll 0 0;
    border: 1px solid #c5c5c5;
    height: 24px;
    margin-right: 45px;
    width: 203px;
}
		</style>
  </head>
  
  <body>
    	<div id="dialog-modal" title="新增版本">
			    <table  class="inputs">
					<tr>
						<td class="lableWord">版本名称：</td>
						<td >
							<input type="text"   id='targetSystemName'  class="inputEdit"/>
							<span class="starColor">*</span>
						</td>
					</tr>
					<tr>
						<td class="lableWord">引用版本</td>
						<td>
							<select id='targetSystem'  class="searchSelect"></select>
						</td>
					</tr>				
				</table>
		</div>
        <script type="text/javascript" src="js/module/targetSystemManager/targetSystemAdd.js"></script>
  </body>
</html>
