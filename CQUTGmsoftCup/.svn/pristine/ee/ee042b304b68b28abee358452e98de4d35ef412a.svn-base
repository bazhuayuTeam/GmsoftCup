
var zTreeObj;
//全局对象
var params={};
params.options={};

var moduletype = null;
var selectedTreeId = null;
var treeSetting={
	nameCol : "modulename",
	async : true,
	asyncUrl : "null",
	dwr:true,
	isMixData:true,//是否使用混合数据
	condition:'',
	sortField:'',
	order:'',
	mixData:{ //混合数据类型		
		//查询使用 ：moduleType 为节点名字，即 flagName
		moduleType:{
	 		treeNodeKey : "codeTableCode",//点击树后，数据库查询的 属性；
			nameCol:"codeTableName",
			properties:['codeTableCode','codeTableName'],
			dwrFun:CodeTableService.findMapByProperties,
			needLink:true,
			condition:'',
			sortField:'',
			order:''
		},
		module:{
	 		treeNodeKey : "modulecode",
			nameCol:"modulename",
			properties:['moduletype','modulecode','modulename','isEndOfModuleLevel','showOrder'],
			dwrFun:ModuleService.findMapByProperties,
			needLink:true,
			condition:'1=1 order by showOrder',
			sortField:'',
			order:''
		}
 	},
 	//树的展开；
	mixDataSelectHandler:function(flagName,node){ //混合数据选择器，选择当前需要使用哪一种数据
 		if(flagName){ //这里会返回点击的节点的类型，这个类型就是mixData中的属性
			return 'module';
		}else{
			return 'moduleType'; //点击根节点的情况
		}
 	 },
		callback:{
 	   		click : function(event, treeId, treeNode){
 	   			var condition="";
 	   			if(treeNode.flagName=="moduleType") {
 	   				condition = "(parent is null or parent ='') and moduleType = '"+treeNode['codeTableCode']+"' order by showOrder";
 	   				moduletype = treeNode['codeTableCode'];
 	   				selectedTreeId = null;
 	   			}else if(treeNode['modulecode'] && treeNode['modulecode'] != null && treeNode['isEndOfModuleLevel'] == 0){
 	   				condition = "parent="+treeNode['modulecode'] + "order by showOrder";
 	   				moduletype = treeNode['moduletype'];
 	   				selectedTreeId = (treeNode['modulecode'].length=12?treeNode['modulecode'].substr(0,8):treeNode['modulecode']);
 	   			} else if(treeNode['modulecode'] && treeNode['modulecode'] != null && treeNode['isEndOfModuleLevel'] == 1) {
 	   				condition = "modulecode="+treeNode['modulecode'] + "order by showOrder";
 	   				moduletype = treeNode['moduletype'];
 	   				selectedTreeId = (treeNode['modulecode'].length=12?treeNode['modulecode'].substr(0,8):treeNode['modulecode']);
 	   			} else {
 	   				condition = "order by showOrder";
 	   				moduletype = null;
 	   				selectedTreeId = null;
 	   			}
	 	   		jQuery("#moduleTable").setGridParam({
	 	   			selectCondition : condition
	 			}).trigger("reloadGrid");
 	   		}
 	   	},
 	   	nameFormatter:function(name,node){
 	   		return name;
 	   	},
 	   	loadConditionFun:function(node){//加载时的判断条件，每次加载前会调用
 			if(node.flagName){
				if(node.flagName=="moduleType"){
					return "(parent is null or parent ='') and moduleType = '"+node['codeTableCode']+"'";
				} else if (node.flagName=="module") {
					return 'parent='+node['modulecode'];
				}
			} 
			else{//根节点 
				return "codeTableCode like 'ModuleType00%'";
			}
        },
 	   	isLeaf:function(node){//是否是叶节点，不写则所有的节点都为非叶节点
        	if(node['isEndOfModuleLevel']&&node['isEndOfModuleLevel'] != 0) {
        		return true;
        	} else {
        		return false;
        	}
        }
	};
	var zNodes = [{
		modulecode : null,
		modulename:"所有模块",
		isParent : true
	}];

	var gridSetting={
			url : 'http://127.0.0.1:8080/WebConnector/data.jsp',
			height:'auto',
			datatype : "json",
			dwrFun : ModuleService.findMapByPropertiesWithLimit,
			dwrCountFun : ModuleService.findCountByProperties,
			condition : '',
			searchCondition : '',
			selectCondition : '',
			needLink : false,
			dwr : true,
			autowidth:true,
			loadComplete:function(){
				$(window).trigger('resize');
			},
			colNames : ['代号','父级','图标','名称','url', '等级','顺序','操作' ],
			colModel : [{
				key : true,
				name : 'modulecode',
				index : 'modulecode',
				sortable : false,
				width : 40
			},{
				name : 'parent',
				index : 'parent',
				width : 15,
				hidden:true,
				sortable : false
			},{
				name : 'icon',
				index : 'icon',
				width : 15,
				hidden:true,
				sortable : false
			}, {
				
				name : 'modulename',
				index : 'modulename',
				sortable : false,
				width : 45
			}, {
				name : 'url',
				index : 'url',
				sortable : false,
				width : 100
			}, {
				name : 'level0',
				index : 'level0',
				hidden:true,
				sortable : false
			},{
				name : 'showOrder',
				index : 'showOrder',
				hidden:true,
				sortable : false
			},{
				name : 'moduleColumn',
				index : null,
				align: 'center',
				formatter : moduleColumn_formater,
				title : false,
				width : 90,
				sortable : false
			} ],
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#modulePager',
			viewrecords : true,
			rownumbers : false,
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records", // 总记录数
				repeatitems : false
			},
			multiselect:true,
		};

//页面加载执行----------------------------------------------------------------------
	$(document).ready(function() {
		$("#moduleSearch").keyup(function(event){
			if(event.keyCode == 13){
				quickSearch();
			}
		});
		
		$("#moduleSearch").Watermark("请输入模块名称","#8b8b8b");
		//合并用户的配置对象
// 		UserOperator.documentReady();
		FormUtil.createComponent(["moduleSearch"],{});
		jQuery("#moduleTable").jqGrid(gridSetting);
		jQuery("#moduleTable").jqGrid('navGrid', '#modulePager', {
			edit : false,
			add : false,
			del : false,
			search:false
		});
		$(window).bind('resize', function() { 
		    $("#moduleTable").setGridWidth($("#MainArea").width()-2); 
		}).trigger('resize'); 
		
		//加载树
		zTreeObj = $("#moduleTree").zTree(treeSetting,zNodes);
		zTreeObj.expandNode(zNodes[0], true, false);
		$(".ui-pg-selbox").bind("change",function(){
			
		});
	});
	
	function quickSearch() {
		var curValue = $("#moduleSearch")[0].value;
		if ($.trim(curValue) != '请输入模块名称') {
			curValue = "modulename LIKE '%" + $.trim(curValue) + "%'";
		} else {
			curValue = "";
		}
		jQuery("#moduleTable").setGridParam({
			searchCondition : curValue,
			page : 1
		}).trigger("reloadGrid");
	}
	function addFun(){
		ModuleService.getMaxShowOrder(selectedTreeId, function(length){
			console.info(length);
			if(length != -1){
				DialogUtil.openFloatWindow("module/module/moduleEdit.jsp",{modulecode:null,parent:selectedTreeId,moduletype:moduletype,showOrder:length+1},{EVENT_OK:function(param){
				console.info(param.showOrder);
				ModuleService.saveWithEntity(param,function(data){
  	   			if(data){
  	   				mWin.ok('新增成功！');
					jQuery("#moduleTable").trigger("reloadGrid");
					$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
				}else{
					jAlert('新增失败！');
				}
  	   		});
   	 	}});
			}else{
				jAlert("新增失败！");
			}
		});	
	}
	function editFun(rowId){
		DialogUtil.openFloatWindow("module/module/moduleEdit.jsp",{modulecode:rowId},{EVENT_OK:function(param){
   		 	ModuleService.updateModule(param,"modulecode='"+rowId+"'",function(data){
				if(data){
  	   				mWin.ok('修改成功！');
					jQuery("#moduleTable").trigger("reloadGrid");
					var row = zTreeObj.getNodeByParam("modulecode", rowId.length!=4?rowId.substr(0, rowId.length-4):null);
					if(row){
						$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(row, "refresh");
					}
				}else{
					jAlert('修改失败！');
				}
				dwr.engine.setAsync(true);
  	   		});
   	 	}});
	}
	
	//上移
	function moveUp(rowId){
		ModuleService.moveUp(rowId,function(data){
			if(data == 1){
				jQuery("#moduleTable").trigger("reloadGrid");
				$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
				mWin.ok("操作成功！");
			}else if(data == -1){
				jAlert("已经在最上面，不能再上移！");
			}else{
				jAlert("操作失败！");
			}
		});
	}
	
	//下移
	function moveDown(rowId){
		ModuleService.moveDown(rowId,function(data){
			if(data == 1){
				jQuery("#moduleTable").trigger("reloadGrid");
				$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
				mWin.ok("操作成功！");
			}else if(data == -1){
				jAlert("已经在最下面，不能再下移！");
			}else{
				jAlert("操作失败");
			}
		});
	}
	
	function deleteFun(rowId,parent) {
		if(confirm("删除该模块将同时删除其所有从属模块，确认要删除吗？")) {
			ModuleService.deleteByIdAndChild(rowId,parent,function(data){
				if(data){
					jQuery("#moduleTable").trigger("reloadGrid");
					$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
					mWin.ok("删除成功！");
					zTreeObj = $("#moduleTree").zTree(treeSetting,zNodes);
				}else{
					jAlert("删除失败！");
				}
			});
		}
	}
	
	function deleteSelectedsFun(){
		var selrow = getSelectedRowIDs();
		var parents = [];
		if(selrow != null && selrow.length > 0){
			if(confirm("删除这些模块将同时删除其所有从属模块，确认要删除吗？")) {
				for(var i = 0; i < selrow.length; i++) {
					parents.push($("#moduleTable").jqGrid('getCell',selrow[i],'parent'));
				}
				ModuleService.deleteByIdsAndChild(selrow,parents,function(data){
						if(data){
							jQuery("#moduleTable").trigger("reloadGrid");
							$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
							mWin.ok("删除成功");
							zTreeObj = $("#moduleTree").zTree(treeSetting,zNodes);
						}else{
							jAlert("删除失败！");
						}
					});
			}
		} else {
			jAlert("请选择数据");
		}
		
/*			var selrow = getSelectedRowIDs();
			if(selrow != null && selrow.length > 0){
				if(confirm("确认要删除模块信息吗？")) {
					ModuleService.deleteByIds(selrow,function(data){
						if(data){
							jQuery("#moduleTable").trigger("reloadGrid");
							$("#moduleTree").zTree(treeSetting,zNodes).reAsyncChildNodes(zTreeObj.getNodeByParam("modulecode", selectedTreeId), "refresh");
							jAlert("删除成功");
						}else{
							jAlert("删除失败！");
						}
					});
				}
			}else{
				jAlert("请选择数据");
			}*/
		return false;
	}
	function showFun(rowid){
		var selrow = rowid ? rowid : getSelectedRowID();
		if(selrow != null){
			DialogUtil.openFloatWindow("module/module/moduleEdit.jsp",{modulecode:selrow,showFlag:true},{});
		}else{
			jAlert("请选择数据");
		}
   	 	return false;
	}
	function getSelectedRowIDs(){
		return jQuery('#moduleTable').getGridParam('selarrrow');//多选
	}
	function getSelectedRowID(){
 		return jQuery('#moduleTable').getGridParam('selrow');//单选
	}
	function getDataIds(){
		return jQuery('#moduleTable').getDataIDs();
	}
	function moduleColumn_formater(data,d2,d3,d4){
		params.options.dwrService=ModuleService;
		params.options.gridName="moduleTable";
		var alink = "<a class='operatorColumn_a' href='javascript:void(0)' onclick=\"editFun('"+d2.rowId+"')\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;" +
				   	"<a class='operatorColumn_a' href='javascript:void(0)' onclick=\"showFun('"+d2.rowId+"')\"><span class='sheetWord'>查看</span></a>&nbsp;&nbsp;" +
				   	"<a class='operatorColumn_a' href='javascript:void(0)' onclick=\"deleteFun('"+d2.rowId+"','"+d3.parent+"')\"><span class='sheetWord'>删除</span></a>";
		if(d3.parent != null){
			alink = "<a class='operatorColumn_a' href='javascript:void(0)' onclick=\"moveUp('"+d2.rowId+"')\"><span class='sheetWord'>上移</span></a>&nbsp;&nbsp;"+
					"<a class='operatorColumn_a' href='javascript:void(0)' onclick=\"moveDown('"+d2.rowId+"')\"><span class='sheetWord'>下移</span></a>&nbsp;&nbsp;"+alink;
		}
		return alink;
		
	} 
	