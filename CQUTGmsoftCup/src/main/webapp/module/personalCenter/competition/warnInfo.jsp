<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			JQueryLoader loader = new JQueryLoader(
			"jqGrid,dialog,dwr,dialogNew,verification,addAndEdit,dropDownBox");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<title>提示</title>

	</head>
	<body>
		<div id="dialog-modal" title="提示">
		<div style='border: solid 1 #AAAAAA; padding: 20px'>
				<p class="title">报名信息已提交，请耐心等待审核……</p>
				<p class="info">查询审核进度、获取分组编码请用微信扫描下方二维码并关注，在菜单栏【大家杯！】进行查询。更多参赛指导资讯请关注近期推送！</p>
			</div>
			<img src="images/qr-code.png" alt="二維碼" style="margin-left: 37%" />
		</div>
		
		<script type="text/javascript" src='<%=path%>/js/module/personalCenter/competition/warnInfo.js'></script>
	</body>
	
</html>