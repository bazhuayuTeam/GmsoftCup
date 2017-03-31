<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader = new JQueryLoader("dwr,dialog,suggest,timepicker,util,date,validate");
String operator=(String)request.getSession().getAttribute("operatorId");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow-x;hidden">
  <head>
  	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
    <base href="<%=basePath%>"/>
    <title>发送语音</title>
    <%=loader.getCssFilesStr()%>
    <%=loader.getJsFilesStr()%>
   <style type="text/css">
   		form {
   			padding : 10px;
   		}
   		fieldset {
   			min-height : 50px;
   			margin-bottom : 10px;
   			text-align : left;
   		}
   		
   		fieldset legend {
   			color : #2e8bd9;
   		}
   		
   		.buttonDiv {
   			position : absolute;
   			bottom : 10px;
   			right : 10px;
   		}
   		
   		.buttonDiv button {
   			width : 60px;
   			height : 30px;
   		}
	</style>
	<script type="text/javascript">
      var basePath = "<%=basePath%>";
      var operator="<%=operator%>";
    </script>
	 <script type="text/javascript" src="js/util/DateUtil.js"></script>
	 <script type='text/javascript' src='dwr/interface/OperatorService.js'></script>
	 <script type='text/javascript' src='dwr/interface/GraduationProjectInfoService.js'></script>
 	 <script type='text/javascript' src='dwr/interface/TermService.js'></script>
  </head>
  
  <body style='height:90%;'>
    <form id="settingForm" class="entityForm" style='height:100%;'>
      <fieldset id="newsRecord">
      	<legend> 发送语音 </legend>
      	<div>
      			<object id="presentation" width="100%" height="100%"
						classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
						align="middle">
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="movie" value="voicePlay.swf" />
						<param name="quality" value="high" />
						<param name="bgcolor" value="#ffffff" />
						<param name="allowFullScreen" value="false" />
						<param name="wmode" value="transparent" />
						<embed src="<%=basePath %>voicePlay.swf"  width="100%" height="100%" align="middle"
						type="application/x-shockwave-flash">
				</object>
      	</div>
      </fieldset>
	</form>
	<div class="buttonDiv">
		<button id="button_ok" onclick="okAndClose();">确定</button>
		<button id="button_cancel" onclick="cancelAndClose();">取消</button>
	</div>
	<script type="text/javascript">
		
		//点击确定关闭窗口
		function okAndClose(){
				var result = {};
				DialogUtil.getTopWindow().close();
				ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
		}
		
		//点击取消关闭窗口
		function cancelAndClose(){
			DialogUtil.getTopWindow().close();
			ChildDialogUtil.doClose(event.data.type, {});
		}
		
 	</script>
  </body>
</html>
