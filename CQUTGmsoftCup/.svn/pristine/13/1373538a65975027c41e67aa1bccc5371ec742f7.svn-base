<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader = new JQueryLoader("jqGrid,dwr,dialog,userDialogValid,tips,file,commonStyle,addAndEdit");
ServiceLoader serviceLoader=new ServiceLoader("OperatorService,SystemFileService");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
	<%=serviceLoader.getServiceFilesStr()%>
    <title>上传头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<style type="text/css">
		.imageTd {
			width: 300px;
		}
		.localImage {
			border:0px;
			width: 90px;
			margin: 0 auto;
		}
		.photo {
			width: 100%;
			height:30px;
			position: relative;
			left:30px;
		}
	</style>
  </head>
  
  <body>
    <div id="mainBody">
			<table class="inputs">
				<tr>
					<td class="imageTd"><div id="localImag" class="localImage"><img id="preview" src="" width="90px" height="90px" /></div></td>
				</tr>
				<tr>
					<td>
						<input id="photo" class="photo" value="上传头像" type="file" accept="image/*" name="file" onchange="previewImage(this)"/>
					</td>
				</tr>
			</table>		
		</div>
		<script type="text/javascript" src="js/module/operatorManager/uploadPhoto.js"></script>
		<script type="text/javascript" src="js/module/manager/uploadHeadPhoto.js"></script>
  </body>
</html>
