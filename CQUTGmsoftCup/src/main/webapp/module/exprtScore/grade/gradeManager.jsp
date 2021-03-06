<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader(
			"jqGrid,dwr,tips,dialog,watermark,mask,noData,selfTable,nicescroll,dropDownBox,dialogNew,searchBox,wordStyle,commonStyle,pageBasic");
	ServiceLoader serviceLoader=new ServiceLoader("ProjectService,SystemFileService,CodeTableService,GameService,TeamService,GameStepDetailService,TargetService,TargetScoreService,ExpertTargetDistributeService");
	String operatorId = (String) request.getSession().getAttribute(
			"operatorId");
	String expertID = (String) request.getSession().getAttribute(
			"expertID");
	String teamId = request.getParameter("teamId");
	String gameStepID = request.getParameter("gameStepID");
	String gameStepDetailID = request.getParameter("gameStepDetailID");
	String teamName = request.getParameter("teamName");
	if(teamName!=null){
		teamName = new String(teamName.getBytes("ISO-8859-1"),"utf-8");
	}
	/* if (null == operatorId) {
		out.println("请先登录!");
		return;  
	} */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>

		<title>评委评审</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<link rel="stylesheet" href="css/module/expertScore/expertScoreManager.css" />
		<style type="text/css">
			#back{
				text-decoration: none;
			}
			#back hover{
				text-decoration: underline;
			}
			.table-content tr{
				line-height:auto;
			}
			.table-content tr td{
				padding-left: 3px;
				padding-right: 3px;
				text-indent: 0px;
			}
		</style>
	</head>

	<body>
	    <div class="topStyle">
	        <div id="title" class="topTitle">评委评审</div>   
        </div>        
        <ul class="searchList">
			<li><span class="searchName">当前位置：</span></li>
			<li style="margin-top: 20px;">
				<span><a id="back" href="module/exprtScore/expertScoreManager.jsp">评委评审</a></span>-&gt;<span id="teanName"></span>
			</li>
			<div class="operationDiv" id='save'>
				    <span>|</span>
				  	<a href="javascript:void(0);" onclick="saveScore();">保存分数</a>
				  	<span>|</span>
			</div>
		</ul>	
		<div style="padding-bottom:30px;position: fixed;width:100%;padding-top: 128px;background: #ffffff;"><div class="drawLineDiv"></div><div class="deadDateDiv" style="width:17%;"><span id="deadLine" ></span></div><div class="drawLineDiv"></div></div>        					      		    
        <div class="contentDiv" style="position:relative;margin-left:0px;padding-top: 173px;">
				<div id="move" style="position: absolute;z-index:-10;width:100%;margin-right: 20px;" >
			        <div id="mainTable" class="table-content"  style="float: left;margin-left: 18px;" ></div>				
				</div>
				<div id="floatIcon">
					<a id="icon" onclick="openWin();">
				        <div class="backgroundIcon"></div>
					</a>
				</div>
				<div id="showData" >
				       <div class='titleHead'>					            
				            <span class='titleWord'>团队：<%=teamName%></span>
				            <a href="javascript:void(0);" onclick="closeWin();"><img class='close' src="images/grade/close.png" alt="关闭" /></a>
				       </div>
				       <div class="dataHead">
				       		  <div class='titleLine'></div>
				              <span class="word">相关文档</span>					         
				       </div>
		               <ul class="relatedData">
		               		<li><span>暂无数据</span></li>		
		               </ul>
			   	</div>
			
        </div>     
        <script type="text/javascript"> 
	        var teamId = '<%=teamId%>';
	        var gameStepID = '<%=gameStepID%>';
	        var gameStepDetailID = '<%=gameStepDetailID%>';
	        var teamName = '<%=teamName%>';
	        var basePath = "<%=basePath%>";
	        $("#teanName").html(teamName);
        </script>
        <script type="text/javascript" src='js/module/exprtScore/grade/gradeManager.js'></script>   
	</body>		
</html>
