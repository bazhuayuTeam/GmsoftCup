<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	JQueryLoader loader = new JQueryLoader("jqGrid,dialog,watermark,dwr,date,userDialogValid,tips,singleselect,util,file,dialogNew");
	ServiceLoader serviceLoader=new ServiceLoader("SystemFileService,CrewService,UserService,OperatorService,ProjectService");

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<base href="<%=basePath%>" />
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
        <title>编辑</title>
		<script type='text/javascript'>
		var basePath = "<%=basePath%>"; 
		</script>
		
		<script type="text/javascript" src="js/jquery/jquery.blockUI.js"></script>
		<style type="text/css">
	   	.borderInput {
			border: none;
			width:250px;
			margin-left:-80px;
		}
		</style>
</head>
<body>
	<%--<div style="background-image: url('images/module/hide.png');background-repeat:repeat; position:absolute;width:100%;height:100%;z-index: 10;display:none;" id="picDiv">
		<center><img id="imgLarge"  style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale'); border:thin ridge powderblue;" />
		<img id="exitButton" src="images/module/exitButton.png" onclick="exitClick();" style="position:absolute;margin-left:-35px;margin-top:5px;cursor: pointer;"/>
		</center>
	</div>
	--%><div title="上传文件" id="dialog-modal" style='min-height: 100%; margin: 0 auto; overflow-x: hidden; overflow-y: auto'>
				<div style="margin-top:15px;">
					<label style="color:#0779c6;float:left;">上传文件:</label>
					<div id="upload_info"  style="margin-top: 15px;"></div>		
				</div>
				<div style="margin-top:50px;">
					<label style="color: red;">提示：<br /><span id="warn">文件命名形如：创意-学号+姓名（姓名填组长的）</span><br/>文档格式支持：zip,rar,doc,pdf;另外命名请勿有空格</label>
				</div>
			</div>
		</div>
	<script type="text/javascript"  src="js/module/personalCenter/competition/leadInXls.js"></script>		
</body>
</html>     