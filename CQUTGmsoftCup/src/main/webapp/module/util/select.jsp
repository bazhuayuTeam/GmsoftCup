<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
JQueryLoader loader=new JQueryLoader("jquery-plugin,jqGrid,dwr,dialog,singleselect,mask,commonStyle,searchBox,dialogNew,watermark,nicescroll");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<base href="<%=basePath%>"/>
<%=loader.getCssFilesStr()%>
<%=loader.getJsFilesStr()%>
<title></title>
<style type="text/css">
	body{
		overflow: hidden;
	}
</style>
</head>
<body>
	<div id="dialog-modal">
		<ul class="searchList"  style="top:0px; ">
				<li><span class="searchName">查询范围</span></li>
				<li>
					<select id='searchfrom'  class="searchSelect" >
					</select>
				</li>
				<li>
					<input type="text" id="operatorCode"  class="searchInput" onkeydown="keyEventEnter(event);"/>
				</li>
				<li>
					<button id="searchButton" onclick="quickSearch();" class="searchButton">查询</button>
				</li>
		</ul>
		<div class="contentDiv" style="padding-top: 73px;">
			<div id="MainArea" >
				<table id="operatorTable" border="0" ></table> 
				<div id="operatorPager"></div> 
			</div>
		</div>
	</div>
<script type="text/javascript">
	var parentData = ChildDialogUtil.getExchangeData();
    var tablenameStr = parentData.tablename;//获得父窗口中传入的tablename值
    var columnName = parentData.columnName;//获取父窗体传入的columnName的值
    var condition = parentData.condition;//获取父窗体传入的condition值
    var colModelStr = parentData.colModelStr;//获取父窗体传入的json数组columnStr
    var searchListStr = parentData.searchList;//获取父窗体传入的json数组searchList
    var isMuti = parentData.isMuti;
    var _isMuti = false;//是否是多选
    if(isMuti){
    	_isMuti = true;
    }
    //根据所需table，产生对应的所需json配置对象
    var jsonString=selectTable[tablenameStr];
    
    var compoIndexArr=new Array('operatorCode','searchBtn','operatorTable');
	Page.initComponent=function(){//初始化控件数据    	
    	if(Page.CustomFlag.disableInitComponent)return;
     	Page.Util.callOver=true;     	
    };		

   Page.loadData=function(){//加载页面数据
		if(Page.CustomFlag.disableLoadData)return;
	};
		
	$(document).ready(function() {
		//alert("在ready中 this 是"+this);
		$.plugin('servers',{
	        files: [
	        	'<%=path %>/dwr/interface/'+jsonString.jsFile],
	        selectors: ['head'] 
		});
		
		//获得父窗口中传入值
		Page.Data.exchangeData = parentData;
		if(Page.Data.exchangeData.muti){
			_isMuti = true;//设置成多选
		}
		
		$.plugin('servers',function(){
			//alert("在plugin中this是"+this);
			//jqGrid的数据对象------------------------------------------------
		    var myDwrFun=window[jsonString.dwrClass][jsonString.dwrFun];//获取dwrFun
			var myDwrCountFun=window[jsonString.dwrClass][jsonString.dwrCountFun];//获取DwrCountFun
			var mycondition=(condition==undefined||condition=='')? jsonString.condition : condition;
			var myColNames=columnName==undefined ? jsonString.colNames : columnName;//获取的ColNames
			var myColModel=colModelStr==undefined?jsonString.colModel:colModelStr;//获取ColModel
	   		var mySearchList=searchListStr==undefined?jsonString.searchList:searchListStr;//用于生成查询框的json数组
			
	   		myColModel = addWidthForColModel(myColModel);	// 添加width属性

	   		//grid的变量设置
			var gridSetting={
					height:'auto',
					width:700,
					datatype : "json",
					dwrFun : myDwrFun,
					dwrCountFun : myDwrCountFun,
					condition : mycondition,
					searchCondition : '',
					selectCondition : '',
					needLink : true,
					dwr : true,
					autowidth:true,
					colNames:myColNames,
					colModel :myColModel,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					pager : '#operatorPager',
					viewrecords : true,
					rownumbers : false,
					jsonReader : {
						root : "rows",
						page : "page",
						total : "total",
						records : "records", // 总记录数
						repeatitems : false// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
					},
					multiselect : _isMuti,
					multiboxonly : true,
	   				ondblClickRow:function(e){$("button:contains('确定')").click();},
					loadComplete:function()
					{
					    $("#MainArea").unmask();	
						$(window).trigger('resize');
					}
				};
			//调用服务		
			if(UserPackage.PageCall.beginReady){
			  UserPackage.PageCall.beginReady();
			}
			//合并用户的配置对象*******************
			//生成模态框
			
			$("#dialog-modal").createDialog({
				height:310,	// 310
				width:730,		// 730
				resizable:false,
				modal: true,
				winMode:ChildDialogUtil.getWinMode(),
				close: function(event) {
				alert("2222")
					if(!event.data){
						DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
						return;
					}
					if(event.data.type == DialogUtil.EVENT_OK){
						alert("11111")
							if(!_isMuti){
								var returnID=getSelectedRowID();
								var returnRowData=getRow(returnID);
								if(returnID && returnRowData){
									returnRowData=$.extend(returnRowData,{IDs:returnID});
									alert(returnRowData)
									ChildDialogUtil.doClose(event.data.type,returnRowData);
								}
							}else{
								var ids = getSelectedRowIDs();
								var datas = [];
								for(var i = 0;i < ids.length;i++){
									datas.push(getRow(ids[i]));
								}
								if(ids && datas[0]){
									var result = {ids:ids,datas:datas};
									ChildDialogUtil.doClose(event.data.type,result);
								}
							}
		  				}
					else if(event.data.type == DialogUtil.EVENT_CANCEL){
						DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					}
				}
			});
			//生成查询
			for(var i = 0;i<mySearchList.length;i++){
				
				var $opt = $("<option value='"+mySearchList[i].value+"'>"+mySearchList[i].text+"</option>");
	            $("#searchfrom").append($opt);
			}
			//生成grid
			$("#MainArea").mask("正在加载中，请耐心等候......");
			$("#operatorTable").jqGrid(gridSetting);
			$("#operatorPager").jqGrid('navGrid', '#operatorPager', {
				edit : false,
				add : false,
				del : false,
				search:false
			});
			$(window).bind('resize', function()
					{
						$("#operatorTable").setGridWidth($("body").width()*0.9);
					}).trigger('resize');
			
			if(UserPackage.PageCall.beginInitComponents){
				UserPackage.PageCall.beginInitComponents();
			}
	        //控件初始化							
			if(Page.initComponent){
				Page.initComponent();
			}        	
			//检查所有调用是否返回并开始加载数据
	     	Page.Util.checkCallFinish();
			//处理焦点
			$("#"+compoIndexArr[0]).focus();
		    document.onkeydown=doKeyDown;
		});
		
		$("#operatorCode").Watermark("", "#8b8b8b");
		$("body").niceScroll({scrollspeed : 20,horizrailenabled: false});
	});
		
		
		
	
	
//功能方法---------------------------------------------------------
	function keyEventEnter(ev) {
		var e = ev || window.event;
		if(e.keyCode==13) {
			quickSearch();
		}
	}
	function quickSearch() {
		var curValue = $("#operatorCode")[0].value;
		var colValue=$("#searchfrom")[0].value;
		if (curValue != '') {
			curValue = colValue+" LIKE '%" + curValue + "%'";
		}
		$("#operatorTable").setGridParam({
			page:1,
			searchCondition : curValue
		}).trigger("reloadGrid");
	}
	function getSelectedRowID(){
 		return $('#operatorTable').getGridParam('selrow');//单选
	}
	
	function getSelectedRowIDs(){//多选
		return $("#operatorTable").getGridParam('selarrrow');
	}
	
	function getRow(id){
		return $('#operatorTable').jqGrid("getRowData", id);//获取选中行的行数据
	}
	
	function doKeyDown(event){
		if(!Array.indexOf) 
		{ 
		    Array.prototype.indexOf = function(obj) 
		    {                
		        for(var i=0; i<this.length; i++) 
		        { 
		            if(this[i]==obj) 
		            { 
		                return i; 
		            } 
		        } 
		        return -1; 
		    }; 
		} 
		
		event = getEvent(event);
		if (event.keyCode==13){//点击回车
			//在查询按钮上
	        if (getEventSrcElement(event).tagName.toLowerCase()=='button'&&$("#operatorCode").val()){
	        	quickSearch();
	        }
		    //在jqGrid中
			else if(getEventSrcElement(event).tagName.toLowerCase()=='table'||getEventSrcElement(event).tagName.toLowerCase()=='tr'){
				$("button:contains('确定')").click();
			}
		    else{
		    	toNext();
		    }
	    }
		else if(event.keyCode==40){//点击向下
			//在jqGrid中
			if(getEventSrcElement(event).tagName.toLowerCase()=='table'||getEventSrcElement(event).tagName.toLowerCase()=='tr'){
				var id=getSelectedRowID();
				var nextIdIndex=dataIds.indexOf(id)+1;
				if(nextIdIndex==dataIds.length){
					toNext();
				}
				else{
					$("#operatorTable").setSelection(dataIds[nextIdIndex],true);
				}
			}
		    else{
		    	toNext();
		    }
			
		}
		else if(event.keyCode==38){//点击向上
			//在jqGrid中
			if(getEventSrcElement(event).tagName.toLowerCase()=='table'||getEventSrcElement(event).tagName.toLowerCase()=='tr'){
				var id=getSelectedRowID();
				var nextIdIndex=dataIds.indexOf(id)-1;
				if(nextIdIndex==-1){
					toFormer();
				}
				else{
					$("#operatorTable").setSelection(dataIds[nextIdIndex],true);
				}
			}
		    else{
		    	toFormer();
		    }
			
		}
		else if(event.keyCode==39){//点击向右
			//在jqGrid中
			if(getEventSrcElement(event).tagName.toLowerCase()=='table'||getEventSrcElement(event).tagName.toLowerCase()=='tr'){
				var currentPage=$("#"+getEventSrcElement(event).id).getGridParam("page");
				var toPage=currentPage+1;
				$("#operatorTable").setGridParam({page:toPage}).trigger('reloadGrid');
			}
		}
		else if(event.keyCode==37){//点击向右
			//在jqGrid中
			if(getEventSrcElement(event).tagName.toLowerCase()=='table'||getEventSrcElement(event).tagName.toLowerCase()=='tr'){
				var currentPage=$("#"+getEventSrcElement(event).id).getGridParam("page");
				var toPage=currentPage-1;
				$("#operatorTable").setGridParam({page:toPage}).trigger('reloadGrid');
			}
		}
		else if(event.keyCode==81&&event.ctrlKey){//点击Ctrl+q
			focusOne();
		}
		else if(event.keyCode==13&&event.ctrlKey){//点击Ctrl+enter
			$("button:contains('确定')").click();
		}
		else if(event.keyCode==27){//点击Esc
	    	window.close();
	    }
		//焦点定位到第一个组件
		function focusOne(){
			for(var i in compoIndexArr){
				var obj=$('#'+compoIndexArr[i]);
				//有readonly属性的标签或者textArea不放置焦点
				if(obj.attr("readonly")||obj[0].tagName.toLowerCase()=="textArea"){
					continue;
				}
				else{
					obj.focus();
					break;
				}
			}
		}
		
			//跳转到下一个控件
		function toNext(){
			var currentId=getEventSrcElement(event).id;
			var currentIndex=compoIndexArr.indexOf(currentId);
			var nextObj;
			if(currentIndex<compoIndexArr.length){
				nextObj=$("#"+compoIndexArr[currentIndex+1]);
			}
			else{
				nextObj=$("#"+compoIndexArr[currentIndex]);
			}
			//var nextObj=$("#operatorTable");
			//下一个对象是表格
			if(nextObj[0]&&nextObj[0].tagName.toLowerCase()=="table"){
				dataIds=nextObj.jqGrid('getDataIDs');
				//var id=nextObj.children("tbody").children("[class!='jqgfirstrow']:first").attr("id");
				//首行选定
				nextObj.setSelection(dataIds[0],true);
				nextObj.focus();
			}
			//跳转焦点
			else{
				nextObj.focus();
			}
			
		}
		//跳转到上一个控件
		function toFormer(){
			var currentId=getEventSrcElement(event).id;
			var currentIndex=compoIndexArr.indexOf(currentId);
			var formerObj=$("#"+compoIndexArr[currentIndex-1]);
			if(currentIndex>=0){
				formerObj=$("#"+compoIndexArr[currentIndex-1]);
			}
			else{
				formerObj=$("#"+compoIndexArr[currentIndex]);
			}
			//下一个对象是表格
			if(formerObj[0]&&formerObj[0].tagName.toLowerCase()=="table"){
				dataIds=formerObj.jqGrid('getDataIDs');
				//var id=nextObj.children("tbody").children("[class!='jqgfirstrow']:first").attr("id");
				formerObj.setSelection(dataIds[0],true);
				formerObj.focus();
			}
			else{
				formerObj.focus();
			}
		}
	}
	
	
	// 给colModel添加width属性
	function addWidthForColModel(colModels){
		for(var i =0;i<colModels.length;i++) {
			if(!colModels[i].width)
				colModels[i].width = 60;
		}
			
		return colModels;
	}
	// 获取事件
	function getEvent(event) {
		return  event || window.event || arguments.callee.caller.arguments[0];
	}
	
	// 获取事件源
	function getEventSrcElement(event) {
		return event.srcElement ? event.srcElement : event.target; 
	}
</script>

</body>
</html>