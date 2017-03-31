<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.SessionUtil"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	JQueryLoader loader=new JQueryLoader("jquery-plugin,jqGrid,dwr,dialog,tree,watermark,date,util,mask,tips,commonStyle,searchBox,pageBasic,selfTable,wordStyle");
	ServiceLoader serviceLoader=new ServiceLoader("RoleService,ColumnsService,DesktopService");
	SessionUtil sessionUtil=new SessionUtil();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
	  	<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<title>角色桌面分配</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0"/>
		<link rel="stylesheet" type="text/css" media="screen" href="css/module/desktop/deskTopSetting.css" />
	</head>
	<body style="margin-top: -1px;padding :0;height: 100%">
		<div class="topStyle">
	   		<div class="topTitle">角色桌面分配</div>
	   		<div class="operateRight">
	   			<span>| </span>
	   			<span><a onclick="savePersonDeskSetting();">保存</a></span>
		   		<span>| </span>
	   		</div>
		</div>	
		
	<div id="content">
		<div id="contentList">
			<div id="roleList" class="list" style="position:relative;width:200px;height:348px;overflow:hidden;">
				<div class="listBar"><img style="margin-right: 7px" src="images/module/office/schedule/item_point.gif"/>待选角色</div>
				<div style="position:absolute;width:200px;top:25px;;height:328px;float:left;background:#fff;overflow:auto;">
					<ul id="roleTree" class="tree" style=""></ul>
				</div>
			</div>
			<div id="cover">
				<div id="listLeft" class="list">
					<div class="listBar"><img style="margin-right: 7px" src="images/module/office/schedule/item_point.gif"/>待选功能</div>
					<select size="5" class="columnList">
					</select>
				</div>
				<div id="middleTool" class="list" style="background:none;">
					<ul class="ul">
						<li class="li"><img onclick="add();" src="images/module/person/btn_to_right.gif" title="增加" alt="增加"/></li><li class="li"><img onclick="deleteFun()" title="删除" src="images/module/person/btn_to_left.gif" alt="删除"/></li><li class="li"><img onclick="addAllOrdeleteAll('add')" title="增加全部" src="images/module/person/btn_all_right.gif" alt="增加全部"/></li><li class="li"><img onclick="addAllOrdeleteAll('delete')" title="删除全部" src="images/module/person/btn_all_left.gif" alt="删除全部"/></li>
					</ul>
				</div>
				<div id="listRight" class="list">
					<div class="listBar"><img style="margin-right: 7px" src="images/module/office/schedule/item_point.gif"/>已选功能</div>
					<select size="5" class="columnList">
					</select>
				</div>
				<div id="rightTool" class="list" style="background:none;">
					<ul class="ul">
						<li class="li"><img onclick="toTopOrToBottom('top')" src="images/module/person/btn_to_top.png" title="置顶" alt="置顶"/></li><li class="li"><img title="向上" onclick="upOrDown('up')" src="images/module/person/btn_up.gif" alt="向上"/></li><li class="li"><img onclick="upOrDown('down')" title="向下" src="images/module/person/btn_down.gif" alt="向下"/></li><li class="li"><img onclick="toTopOrToBottom('bottom')" title="置尾" src="images/module/person/btn_to_bottom.png" alt="置尾"/></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript' src='js/module/desktop/deskTopSetting.js'></script>
	</body>
</html>
