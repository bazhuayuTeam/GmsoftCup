<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="utf-8"%>
<%@ page import="com.cqut.util.JQueryLoader"%>
<%@page import="com.cqut.util.ServiceLoader"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String operatorId = (String) request.getSession().getAttribute("operatorId");
	if(null == operatorId){
		out.println("<script type='text/javascript'>");   
	    out.println("window.open('"+request.getContextPath()+"/login.jsp','_top')");   
	    out.println("</script>");
		return;
	}
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,watermark,dialog,singleselect,commonStyle,dialogNew,pageBasic");
ServiceLoader sl = new ServiceLoader("OperatorService,RoleService,RoleAssignService");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="<%=basePath%>"/>
		<%=loader.getCssFilesStr()%>
		<%=loader.getJsFilesStr()%>
		<%=sl.getServiceFilesStr()%>
		<title>人员角色分配管理</title>
		<script type="text/javascript">
	        var basePath="<%=basePath%>";
	        var operatorId = "${operatorId}";
        </script>
		<style type="text/css">
			a{ text-decoration: none; }			
			.contentDiv {
				position: absolute;
				top:43px;
				width: 97%;
				margin-left: 20px;
				
			}
			
			.selectDiv {
				position:absolute;
				bottom:-89px;
				width: 20%;
				height: auto;
				overflow: hidden; 
			}
			
			.showMainDiv {
				position:absolute; 
				top:69px; 
				bottom:0; 
				left:20%; 
				width:80%;
			}
			
			.currentPerson {
				color:red;
				margin-right:5px;
			}
		</style>
  	</head>
  
  <body>
     <div class="topStyle">
	      <div class="topTitle"  id="title">角色分配</div>
     </div>
     <div class="contentDiv">
		 <div class="selectDiv">
			  <label for="personSelect">当前人员：</label>
			  <input type="text" name="createPerson" id="personSelect" 
								field="createPerson" save="true" 
								refer="zgh" referText="xm" 
								referClass="personSelect" linkType="0" 
								showName="人员" style="width:120px;" />
		</div>
	<div id="showMainDiv"  class="showMainDiv">
		<div class="QueryArea">
		      <div style="width: 300px;">
					<label id="currentLabel" >当前选择人员：</label>
					<label id="currentPerson"  class="currentPerson"></label>			
			    	<div id="department" ></div>
			 	</div>
		</div>
		<div id="MainArea">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr valign="top">
					<td width="40%" >
						<div id="selectedArea">
							<table id="selectedTable" border="0"></table>
							<div id="selectedPager"></div>
						</div>
					</td>
					<td width="4%"><p>&nbsp;</p></td>
					<td width="40%">
						<div id="spareArea">
							<table id="spareTable" border="0"></table>
							<div id="sparePager"></div>
						</div>
					</td>
				</tr>			
			</table>
		</div>
		</div>
	</div>
	<script type="text/javascript">var operatorId=<%=operatorId %>;</script>
	<script type="text/javascript" src="js/module/roleManager/roleAssign.js"></script>
  </body>
</html>
