<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("dwr,dialog,ztree3.0");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%=loader.getCssFilesStr()%>
	<%=loader.getJsFilesStr()%>
    
    <title>树形多选框</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type='text/javascript' src='dwr/interface/OperatorService.js'></script> 
	<script type='text/javascript' src='dwr/interface/OrganizationService.js'></script> 
	<script type='text/javascript' src='dwr/interface/CodeTableService.js'></script> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
Page.initComponent = function() {//初始化控件数据    	
	if(Page.CustomFlag.disableInitComponent)return;
	Page.Util.initCallService(CodeTableService.findMapByPropertiesQuick,[['CodeTableCode','CodeTableName'],"CodeType='UserType' and level_='1'",false],function(datas){    		
   		if(datas){     	
   		}   
   	});
	Page.Util.callOver = true;
}

Page.loadData = function() {//加载页面数据
	if (Page.CustomFlag.disableLoadData) return;
	//加载树
	var zNodes = [ {
		name : "人员名单",
		isParent : true
	} ];
	var treeSetting=Page.Data.exchangeData.treeSetting;
	mytree = $.fn.zTree.init($("#scoreEnterTree"), treeSetting, zNodes);//初始化树
	var otRoot = mytree.getNodes()[0];//获取根节点
	mytree.expandNode(otRoot, true);//展开根节点

	//加载数据完成
	if (UserPackage.PageCall.endLoadData) {
		UserPackage.PageCall.endLoadData(data);
	}

}

$(document).ready(function() {
	var mytree;
	//调用服务		
	if (UserPackage.PageCall.beginReady) {
		UserPackage.PageCall.beginReady();
	}
	//创建控件
	$("#dialog-modal").createDialog({
		height : 450,
		width : 500,
		resizable : false,
		modal : true,
		winMode : ChildDialogUtil.getWinMode(),
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
			if (event.data.type == DialogUtil.EVENT_OK
					&& (Page.Data.exchangeData.showFlag == undefined || !Page.Data.exchangeData.showFlag)) {
	
				var result = [];
				} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
				} else {
					ChildDialogUtil.doClose(event.data.type, {});
				}
			}
		});

		Page.Data.exchangeData = ChildDialogUtil.getExchangeData();//获得父窗口中传入值

		if (UserPackage.PageCall.beginInitComponents) {
			UserPackage.PageCall.beginInitComponents();
		}
		//控件初始化							
		if (Page.initComponent) {
			Page.initComponent();
		}
		//检查所有调用是否返回并开始加载数据
		Page.Util.checkCallFinish();
	});


function doSelect(){
	var selectedNodes=mytree.getCheckedNodes();
	var nodeList=[];
	for(var i in selectedNodes){
		if(selectedNodes[i].operatorCode){
			nodeList.push({value:selectedNodes[i].operatorCode,name:selectedNodes[i].operatorName});
		}
	}
	addToText(nodeList)
}

function addToText(idList){
	for(i=0;i<idList.length;i++){
		if(!$("#myselected option[value="+idList[i].value+"]").length){//避免加入相同的option
			$("<option value="+idList[i].value+">"+idList[i].name+"</option>").appendTo($("#myselected"));//放入select
		}
	}
}

function doDelete (){
		$("#myselected option:selected").remove();
	}
</script>
  </head>
  
  <body>
  
  	<div id="dialog-modal" title=" 编辑区" style='min-height: 100%; margin: 0 auto; overflow-x: hidden; overflow-y: auto'>
		<div id="scoreEnterTreeDiv" style="float: left; width: 150px;position:absolute;top:0px;bottom:0px;height:auto;overflow: auto;">
			<ul id="scoreEnterTree" class="ztree"></ul>
		</div>
		<div id="btnArea" style="float:left;width: 50px;margin-top:0px;margin-bottom:0;margin-left:200px;height:auto;overflow:none;" >
            <button id='btn_select'    title=右移 onclick="doSelect();"  style="margin-bottom:10px" >>></button>
            <br/>
            <button id='btn_del' title=左移 onclick="doDelete();"  style="margin-bottom:10px"><<</button>
            <br/>
         </div>
        
        <div id="selectedArea">
           <select size="10"  id="myselected"  style="float:left; width: 200px;height:300px;position:absolute;top:0px;right:10px;overflow:none;" ondblclick="del();">
            </select>
        </div> 
	</div>
  </body>
</html>
