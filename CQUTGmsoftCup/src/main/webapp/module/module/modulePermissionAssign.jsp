<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cqut.util.JQueryLoader" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JQueryLoader loader=new JQueryLoader("jqGrid,dwr,tree");
%>
<html style="width: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>"/>
<%=loader.getCssFilesStr()%>
<%=loader.getJsFilesStr()%>

<script type='text/javascript' src='dwr/interface/ModuleService.js'></script> 
<script type='text/javascript' src='dwr/interface/PermissionModuleService.js'></script>
<script type='text/javascript' src='dwr/interface/PermissionService.js'></script>

<style type="text/css">
	<!--
		a{ text-decoration: none;}
		
	--> 
</style>

<title>模块权限管理</title>

<script type="text/javascript">
//zTree数据对象---------------------------------------------

	var selectedTreeId = null;
	var zTreeObj;
	var treeSetting={
		nameCol : "modulename",
		async : true,
 		asyncUrl : "null",
 		dwr:true,
 		dwrFun:ModuleService.findMapByProperties,
 		needLink:true,
 		properties:['modulecode','modulename'],
 		condition:'',
 		sortField:'',
 		order:'',
 	    treeNodeKey : 'modulecode',
 	   	callback:{
 	   		click : function(event, treeId, treeNode){
 	   			var condition="";
 	   			var nodeId = treeNode['modulecode'];
 	   			var spareSelectCondition = "";
				selectedTreeId = nodeId;
 	   			condition = "moduleCodes = '"+nodeId+"'";
				
 	   			if(nodeId && nodeId != null){
 	   				spareSelectCondition = 'permissioncode NOT IN (SELECT permissionCodes FROM PermissionModule WHERE moduleCodes = "'+nodeId+'")';
 	   			}
 	   			//刷新表格数据
	 	   		$("#slectedTable").setGridParam({
	 	   			selectCondition : condition
	 			}).trigger("reloadGrid");
 	   			
	 	   		$("#spareTable").setGridParam({
	 	   			selectCondition : spareSelectCondition
	 			}).trigger("reloadGrid");
 	   		}
 	   	},
 	   	nameFormatter:function(name,node){//处理显示数据，暂时不支持html
 	   		return name;
 	   	},
 	   	loadConditionFun:function(node){//加载时的判断条件，每次加载前会调用
 	 	   	//默认处理的是过滤ID
 	 	   	if(node["modulecode"] == null){
 	 	   		return 'parent IS NULL';
 	 	   	}else{
 	 	   		return 'parent=' + node["modulecode"];
 	 	   	}
 	   	},
 	   	isLeaf:function(node){//是否是叶节点，不写则所有的节点都为非叶节点
 	   		return false;
 	   	}    	    	   	
	};

	var zNodes = [{
		modulecode : null,
		modulename:"全部模块",
		isParent : true
	}];

//jqGrid的数据对象------------------------------------------------
	var selectedGridSetting={
			url : 'null',//这里必须不为空
			height:'auto',
			width:'auto',
			datatype : "json",
			dwrFun : PermissionModuleService.findMapByPropertiesWithLimit,
			dwrCountFun : PermissionModuleService.findCountByProperties,
			condition : '',
			searchCondition : '',
			selectCondition : 'id = -1',
			needLink : true,
			dwr : true,
			autowidth:true,
			colNames : [ 'id', '编号', '权限名称','操作' ],
			colModel : [ 
				{
					key:true,
					name : 'id',
					index : 'id',
					hidden:true
				},
				{
					name : 'permissioncode',
					index : 'permissioncode',
					hidden:true
				},
				{
					name : 'per_permissionname',
					index : 'per_permissionname'
				},
				{
					name : 'operateColumn',
					index : null,
					align: 'center',
					formatter : selectedFormatter,
					title : false,
					width : 50,
					sortable : false
				}
			],
			rowNum : 10,
			rowList : [ 10, 15, 30 , 50],
			pager : '#selectedPager',
			viewrecords : false,
			rownumbers : false,
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records", // 总记录数
				repeatitems : false
			// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
			},
			ondblClickRow:function(rowid,iRow,iCol,e){//双击表格的行
				deleteFun(rowid);
			},
			multiselect:true,
			multiboxonly:false,
			caption:'已有权限',
			hidegrid:false
		};

		//备选表格
		var spareGridSetting={
			url : 'null',//这里必须不为空
			height:'auto',
			width:'auto',
			datatype : "json",
			dwrFun : PermissionService.findMapByPropertiesWithLimit,
			dwrCountFun : PermissionService.findCountByProperties,
			condition : '',
			searchCondition : '',
			selectCondition : '',
			needLink : true,
			dwr : true,
			autowidth:true,
			colNames : [ '编号', '权限名称','操作' ],
			colModel : [ 
				{
					key:true,
					name : 'permissioncode',
					index : 'permissioncode',
					hidden:true
				},
				{
					name : 'permissionname',
					index : 'permissionname'
				},
				{
					name : 'operateColumn',
					index : null,
					align: 'center',
					formatter : spareFormatter,
					title : false,
					width : 50,
					sortable : false
				}
			],
			rowNum : 10,
			rowList : [ 10, 15, 30 , 50],
			pager : '#sparePager',
			viewrecords : false,
			rownumbers : false,
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records", // 总记录数
				repeatitems : false
			// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
			},
			ondblClickRow:function(rowid,iRow,iCol,e){//双击表格的行
				addSelectedsFun();
			},
			multiselect:true,
			multiboxonly:false,
			caption:'可选权限',
			hidegrid:false
		};

//页面加载执行----------------------------------------------------------------------
	$(document).ready(function() {
		//初始化已有数据
		jQuery("#slectedTable").jqGrid(selectedGridSetting);
		jQuery("#slectedTable").jqGrid('navGrid', '#selectedPager', {
			edit : false,
			add : false,
			del : false,
			search:false
		});
		var treeWidth = 215;
		$(window).bind('resize', function() { 
			$("#showMainDiv").width(document.documentElement.clientWidth - treeWidth);
			var curWidth = (document.documentElement.clientWidth - treeWidth - 40)/2;
			$("#slectedTable").setGridWidth(curWidth); 
			$("#spareTable").setGridWidth(curWidth-1);
		});
		
		//初始化备选表格
		jQuery("#spareTable").jqGrid(spareGridSetting);
		jQuery("#spareTable").jqGrid('navGrid', '#sparePager', {
			edit : false,
			add : false,
			del : false,
			search:false
		});
		
		//加载树
		zTreeObj = $("#permissionModuleTree").zTree(treeSetting,zNodes);
		zTreeObj.expandNode(zNodes[0], true, false);
		$(window).bind("resize",function(){
			var curWidth=document.body.clientWidth-161;
			var tableWidth=(curWidth-38)/2-2;
			var slectedTable=$("#slectedTable");
			var spareTable=$("#spareTable");
				$("#showMainDiv").width(curWidth);
				slectedTable.setGridWidth(tableWidth);
				spareTable.setGridWidth(tableWidth);
			});
	});
	
	
//功能方法---------------------------------------------------------
	
	function quickSearch() {//快速搜索
		var curValue = $("#permissionModuleSearch")[0].value;
		if (curValue != '') {
			curValue = $("#searchField").val() + " LIKE '%" + curValue + "%'";
		}
		jQuery("#spareTable").setGridParam({
			searchCondition : curValue
		}).trigger("reloadGrid");
	}
	function deleteFun(rowId){
		if(rowId != null){
			PermissionModuleService.deleteById(rowId,function(data){
				if(data){
					jQuery("#slectedTable").trigger("reloadGrid");
					jQuery("#spareTable").trigger("reloadGrid");
				}else{
					alert('删除分配信息失败');
				}
			})
		}
	}
	//批量删除
	function deleteSelectedsFun(){
		var selrow = getSelectedRowIDs();
		if(selrow != null && selrow.length > 0){
			PermissionModuleService.deleteByIds(selrow,function(data){
				if(data){
					jQuery("#slectedTable").trigger("reloadGrid");
					jQuery("#spareTable").trigger("reloadGrid");
				}else{
					alert('批量删除分配信息失败');
				}
			})
		}
	}
	//批量添加
	function addSelectedsFun(){
		if(selectedTreeId && selectedTreeId!=null){
			var selrow = getSpareRowIDs();
			if(selrow != null && selrow.length > 0){
				PermissionModuleService.batchAddAssigns(selectedTreeId, selrow,function(data){
					if(data){
						jQuery("#slectedTable").trigger("reloadGrid");
						jQuery("#spareTable").trigger("reloadGrid");
					}else{
						alert('批量添加分配信息失败');
					}
				})
			}
		}
	}
	//单个添加
	function addSelectFun(id){
		if(selectedTreeId && selectedTreeId!=null){
			PermissionModuleService.batchAddAssigns(selectedTreeId, [id],function(data){
				if(data){
					jQuery("#slectedTable").trigger("reloadGrid");
					jQuery("#spareTable").trigger("reloadGrid");
				}else{
					alert('添加分配信息失败');
				}
			})
		}
	}
	
	function getSelectedRowIDs(){
		return jQuery('#slectedTable').getGridParam('selarrrow');//多选
	}
	function getSpareRowIDs(){
		return jQuery('#spareTable').getGridParam('selarrrow');//多选
	}
	
	//操作列生成Button
	function spareFormatter(data,d2,d3,d4){
		var alink = "<a href=\"javascript:addSelectFun('"+d2.rowId+"');\">选择</a>";
		return alink;
		
	} 
	function selectedFormatter(data,d2,d3,d4){
		var alink = "<a href=\"javascript:deleteFun('"+d2.rowId+"');\">移除</a>";
		return alink;
		
	} 
</script>

</head>
<body style="padding:0;margin: -1px 0 0 0;width: 100%">
<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<img border="0" width="13" height="13" src="images/plugins/components/title_arrow.gif">
				模块权限管理
			</div>
			<div id="Title_End"></div>
			<div id="Title_bar_bg"></div>
		</div>
		<div id="Title_bar_Tail">
			<div id="Title_FuncBar">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	<div style="float: left; width: 160px;overflow: auto;position: absolute;top: 46px;bottom: 0;height: auto;">
		<ul id="permissionModuleTree" class="tree"></ul>
	</div>
	<div id="showMainDiv" style="position: absolute;left: 161px;right: 0px;width: auto;">
		<div id="QueryArea" style="width: 100%">
			&nbsp;快速搜索可选权限：
			<select id="searchField">
				<option value ="permissionName">权限名称</option>
			</select>
			<input type="text" id="permissionModuleSearch">
			<button onclick="quickSearch();" >搜索</button>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="50%">
					<div class="MainArea" style="width: 100%">
						<table id="slectedTable" border="0"></table> 
						<div id="selectedPager"></div> 
					</div>
				</td>
				<td width="38" height="100%" valign="middle">
					<button onclick="addSelectedsFun();return false;">&lt;&lt;</button>
					<div style="height:20px;">
					</div>
					<button onclick="deleteSelectedsFun();return false;" style="width:36px;">&gt;&gt;</button>
				</td>
				<td width="50%">
					<div class="MainArea" style="width: 100%">
						<table id="spareTable" border="0"></table> 
						<div id="sparePager"></div> 
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>