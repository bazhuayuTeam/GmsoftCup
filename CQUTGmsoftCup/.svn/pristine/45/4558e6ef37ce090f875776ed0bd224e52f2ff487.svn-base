<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.SessionUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader = new JQueryLoader("dwr,dialog,util,date,validate,watermark,mask");
SessionUtil sessionUtil=new SessionUtil();
String operatorKind=sessionUtil.getOperatorKind(request);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow-x;hidden">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>"/>
    <%=loader.getCssFilesStr()%>
    <%=loader.getJsFilesStr()%>
  </head>
  <style type="text/css">
  	#Main{
  		margin: 10px 15px;
  	}
  	#promptDiv{
  		float:left;
  		margin-top: 3px;
  	}
  	#messageContentDiv,#receiverPersonDiv{
  		float:left;
  		clear:both;
  		margin-top: 10px;
  	}
  	#messageContent,#receiverPersonNames,#sendWay,#receiverPersonDiv,#addMoreButton{
  		float:left;
  	}
  	#receiverPersonNames{
  		margin-top:10px;
  	}
  	.teacherNameHref{
  		cursor: pointer;
  	}
  	.teacherNameHref:HOVER {
  		text-decoration:underline;
  		color:blue;
  	}
  </style>
  <script type='text/javascript'>
  var base = "<%=basePath%>";
  var operatorId = '${operatorId}';
  </script>
  <script type='text/javascript' src='dwr/interface/TeacherGroupDetailService.js'></script>
  <script type='text/javascript' src='dwr/interface/MessageService.js'></script>
  <script type="text/javascript" src="plugins/kindeditor/kindeditor.js"></script>
  <body>
    <div id="dialog-modal" title="消息发送" style=" width:100%;text-align:center;workflow:hidden;margin:0 auto">
    	<div id = "Main">
	    	<div id = "promptDiv">请选择消息发送方式:</div>
			<div id = "sendWay">
				<input type="checkbox" id='xt_open_de' onchange='xtChangeDe();' checked='checked'/>系统信息
				<input type="checkbox" id='sm_open_de' onchange='smChangeDe();' style='margin-left:10px;'/>短信发送<span id="xxsj">[<a href='javascript:void(0)' onclick='smShowDe();'style="color:rgb(0,102,204);">详细设置</a>]</span>
			</div>
			<div id = 'messageAlertDiv' style = "display:none;float:left;clear:both;color:red;font-size:13px;">(您输入的短信内容超过70个字，多余的内容将会被系统自动省略)</div>
			<div id = "receiverPersonDiv">接收人:</div>
			<div id = "receiverPersonNames" style = "width:400px;text-align:left;">
				<table id="receiverTable" width="100%" style="margin-top:-5px;border: 1px solid black;" >
					<tr height="20px"></tr>
				</table>
			</div>
			<div id = "addMoreButton"><button id="addreceiver" style="height:23px;width:60px;font-size:12px;margin-top:6px; margin-left: 5px" title="添加接收人">添加</button></div>
			<div id = "messageContentDiv">消息内容:</div>
			<div id = "messageContent">
				<textarea id="Editor"  style = "width:540px;height: 230px;"></textarea>
			</div>
		</div>
    </div>
    <script type='text/javascript' src='plugins/sendMessage/sendMessageTool.js'></script>
  </body>
</html>
