<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@ page import="com.cqut.util.ServiceLoader"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	JQueryLoader loader = new JQueryLoader("jqGrid,dwr,tips,dialog,watermark,nicescroll,selfTable,mask,commonStyle,searchBox,wordStyle,pageBasic");
	ServiceLoader serviceLoader=new ServiceLoader("ProjectService,SystemFileService,TeamService,GameStepDetailService,TargetService,TargetScoreService,ExpertTargetDistributeService");
	String operatorId = (String) request.getSession().getAttribute("operatorId");
	String targetCode=request.getParameter("targetCode");
	String gameStepDetailID=request.getParameter("gameStepDetailID");	
	String targetScore=request.getParameter("targetScore");	
	String targetExplain=request.getParameter("targetExplain");	
	if(targetExplain!=null){
		targetExplain = new String(targetExplain.getBytes("ISO-8859-1"),"utf-8");
	}
	String targetName=request.getParameter("targetName");	
	if(targetName!=null){
		targetName = new String(targetName.getBytes("ISO-8859-1"),"utf-8");
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
		<title>评委评审管理</title>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=serviceLoader.getServiceFilesStr()%>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<link rel="stylesheet" href="css/module/expertScore/expertScoreManager.css" />
	</head>

	<body >
	     <div>
		    <div class="topStyle">
		         <div id="title" class="topTitle">查看其他团队情况</div>   
	        </div>        
	        <div class="content">
				 <div class="inputTitleDiv" style="top:70px;">
						<div class="line"></div>
				    	<span class="title" id="targetHead">暂无信息</span>
				 </div>
				 <ul class="inputSearchList" style="top:120px;height:35px">
					<li><span id="targetInfo" >暂无信息</span></li>
				</ul>
			</div>
			<div style="height:155px;"></div>
		  </div>
		   <div id="main">
					<div id="scoreTable" class='table-content' style="float: left;width:75%"></div>					
					<div id="showData" style="display: block;">
				       <div class='titleHead'>					            
				            <span class='titleWord' id="infoTitle">文档</span>
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
            var gameStepDetailID="<%=gameStepDetailID%>";
            var targetCode="<%=targetCode%>";
            var targetExplain="<%=targetExplain%>";
            var targetScore="<%=targetScore%>";
            var targetName="<%=targetName%>";
            var basePath = "<%=basePath%>";
        </script>
        <script type="text/javascript" src='js/module/exprtScore/grade/otherDisciplineScore.js'></script>   
	</body>		
</html>
