<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
JQueryLoader loader = new JQueryLoader("dwr,print");

String param = "";
if (request.getParameter("param") != null){
	param = request.getParameter ("param");
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>print page</title>
    <script language='JavaScript' type='text/javascript' src='js/jquery/jquery-1.6.2.min.js'></script>
    <script language='JavaScript' type='text/javascript' src='plugins/print/jquery.PrintArea.js'></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
			a {
				text-decoration : none;
			}
			p {
				margin-bottom : 2px;
			}
			table {
				border-collapse : collapse;
				font-size : 16px;
				border : 1px solid black; 
				height : 20px;
				padding-right : 3px;
				padding-left : 3px;
			}
			td, th {
				border : 1px solid black; 
				height : 20px;
				padding-right : 3px;
				padding-left : 3px;
			
			}	  
			h1 {
			
				margin-top: 70px;
				page-break-after: always;
				font:14pt ;
				font-family:"宋体";
				font-weight:bold;
				text-align:center;
				margin-bottom:30px;
			
			}
			
			.reviewExpertSumaryReport{	
				border:1px solid #000;
				font:12pt "宋体";
				padding:4px 0 3px;
				background:#FFF;
				padding-left:2px;
				min-width: 30px;
			}
			
			
			<!--lijun 拨款打印样式 allocationPre.jsp-->
			.allocationPre table{
				border-collapse:collapse;
				font-size: 16px;
				border:1px solid black; 
				
				padding-right:3px;
				padding-left:3px;
			}
			
			.allocationPre td{
				border:1px solid black; height: 30px;padding-right:3px;padding-left:3px;
				word-break:break-all;
			}
			
			
			.allocationPre th{border:1px solid black; height: 30px;padding-right:3px;padding-left:3px;}	  
		
		</style>
<object id="factory" viewastext style="display:none"
   classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
   codebase="<%=basePath%>/plugins/print/smsx.cab#Version=7,2,0,36">
</object>
	<script>
		var printValue = {};
		var param = "<%=param%>";
		$(document).ready(function(){
			printValue = window.opener.transferValue();
		    if(param == "grid"){
		    	$("#div").css("display","none");
		    	printForJqgrid();
		    }else if(param == "div"){
		    	$("#grid").css("display","none");
		    	printForDiv();
		    }
		});
		
		function printForJqgrid(){
		    var colNames = printValue.title;
		    var colModles = printValue.modle;
		    var setting = printValue.setting;
		    var notPrintNum = printValue.num;
		    var datas = printValue.data;
		    var table = $("#printTable")[0];
		    var topRow = $("#topRow")[0];
		    var topCell = topRow.insertCell();
		    topCell.align = "center";
		    topCell.colSpan = colNames.length-2-notPrintNum;
		    var h2 = document.createElement("h2");
		    h2.innerHTML = printValue.tableName;
		    topCell.appendChild(h2);
		    
		    var titleRow = $("#titleRow")[0];
		    var cell = {};
		    for(var i = 2;i<colNames.length;i++){
		    	if(colNames[i] != ""){
		    		cell = titleRow.insertCell();
				    cell.width = "150";
				    cell.align = "center";
				    cell.innerHTML = colNames[i]; 
		    	}
		    }
		    var row = {};
		    var modlename = "";
		    for(var i = 0;i<datas.length;i++){
		    	row = table.insertRow(-1);
		    	for(var j = 2;j<colModles.length;j++){
		    		if(colModles[j].name != ""){
		    			modlename = colModles[j].name;
			    		cell = row.insertCell();
		     			cell.align = "center";
						cell.innerHTML = datas[i][modlename];
		    		}
		    	}
		    }
		    printFun(setting);
		}
		
		function printForDiv(){
			var html = printValue.html;
			var setting = printValue.setting;
			$("#div")[0].innerHTML = html;
			printFun(setting);
		}
		
		function printFun(setting){
			if (!factory.object) {
			    alert("打印控件没有正确安装!");
			    return;
			}
			factory.printing.header = setting.header;    //页眉
			factory.printing.footer = setting.footer;    //页脚 
			factory.printing.leftMargin = setting.leftMargin;   //左边距 
			factory.printing.topMargin = setting.topMargin;    //上边距 
			factory.printing.rightMargin = setting.rightMargin;   //右边距 
			factory.printing.bottomMargin = setting.bottomMargin;    //下边距 
			factory.printing.portrait = setting.portrait; //1为纵向打，0为横向打
			factory.printing.Preview();
			//factory.DoPrint(true,window);
			window.close();
		}
	</script>  
  </head>
  
  <body>
	<div id="grid">
		<table id="printTable">
			<tr id="topRow"></tr>
			<tr id="titleRow"></tr>
		</table>
	</div>
	<div id="div" style="text-align:center;margin-top:20px;">
	</div>
  </body>
</html>
