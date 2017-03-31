<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,userDialogValid,dialog,commonStyle,addAndEdit,dialogNew,verification");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	ServiceLoader sl = new ServiceLoader("TargetSystemService,TargetService,CodeTableService");
	/*if (null == operatorId) {
		out.println("请先登录!");
		return;
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<title>新增指标版本</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
  </head>
  
  <body>
    	<div id="dialog-modal" title="新增指标">
			    <table  class="inputs">
					<tr>
						<td class="lableWord">指标名称</td>
						<td >
							<input type="text"   id='targetName'  class="inputEdit" maxlength="100" />
							<span class="starColor">*</span>
						</td>						
					</tr>		
					<tr class="hideScore">
						<td class="lableWord hideScore">指标分值</td>
						<td class="hideScore">
								<input type="text"   id='score'  class="inputEdit"  maxlength="5"/>
								<span class="starColor">*</span>
						</td>
					</tr>		
					<tr>
						<td class="lableWordBig">指标说明</td>
						<td >
								<textarea id='explain'  class="explainInput"></textarea>
						</td>
					</tr>					
				</table>
		</div>
        <script type="text/javascript"  src="js/module/targetManager/targetAdd.js"></script>
  </body>
</html>
