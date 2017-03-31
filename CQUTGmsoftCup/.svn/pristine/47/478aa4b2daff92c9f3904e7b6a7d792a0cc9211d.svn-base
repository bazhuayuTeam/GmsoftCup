var params = {};
params.options = {};
var parentCode = null;//保存parentCode，新增用
var codeType = null;
var level0 = 0;
var _searchCondition="parentCode is null";

var haveSearch = false;//是否已经搜索


$(document).ready(function() {
	//合并用户的配置对象
		// 		UserOperator.documentReady();
	var codeTableTable = $("#codeTableTable");
	codeTableTable.jqGrid(gridSetting);
	codeTableTable.jqGrid('navGrid', '#codeTablePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	$(window).bind('resize', function() {
		codeTableTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	trace.push(node);
	$('#allTrace').html(showTrace(0, 0));
	haveSearch = false;
	
	initPosition();
});

jQuery(function() {
	$("#codeTableCodeSearch").Watermark("请输入代号", "#8b8b8b");
	$("#codeTableNameSearch").Watermark("请输入名称", "#8b8b8b");
});

//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : CodeTableService.findMapByPropertiesWithLimit,
	dwrCountFun : CodeTableService.findCountByProperties,
	condition : "parentCode is null",
	searchCondition : "",
	selectCondition : '',
	needLink : false,
	dwr : true,
	checkable:true,
	autowidth : true,
	loadComplete:function(){
		$(window).trigger('resize');
	},
	colNames : [ '码表编号', '码表类型', '名称', '父节点代号', '是否有子节点', '拼音字母', '等级', '操作' ],
	colModel : [ {
		//代号	
		key : true,
		name : 'codeTableCode',
		index : 'codeTableCode',
		hidden : false,
		width : 25
	}, {
		//代码类型
		name : 'codeType',
		index : 'codeType',
		hidden : false,
		sortable : false,
		width:20
	}, {
		//名称
		name : 'codeTableName',
		index : 'codeTableName',
		sortable : false,
		hidden : false,
		width : 20
	}, {
		//父亲节点
		name : 'parentCode',
		index : 'parentCode',
		align : 'center',
		hidden : true,
		sortable : false
	}, {
		//是否有子节点
		name : 'hasChild',
		index : 'hasChild',
		align : 'center',
		hidden : true,
		sortable : false
	}, {
		//拼音字母
		name : 'chineseChar',
		index : 'chineseChar',
		align : 'center',
		hidden : true,
		sortable : false
	}, {
		//等级
		name : 'level0',
		index : 'level0',
		align : 'center',
		hidden : false,
		sortable : false,
		width : 5
	}, {
		//操作
		name : 'codetableColumn',
		index : null,
		formatter : codetableColumn_formater,
		title : false,
		width : 25,
		sortable : false,
		align:'center'
	} ],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#codeTablePager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false
	// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	},
	multiselect : true,
	multiboxonly : true,
	altRows:true,
	altclass:'altclass'
};

//功能方法---------------------------------------------------------
//search
function quickSearch() {
	var codeTableCodeValue = $("#codeTableCodeSearch").val();   //码表类型
	var codeTableNameValue = $("#codeTableNameSearch").val();   //码表名称
	var currentSearchCondition = "1=1";
	if (codeTableCodeValue!= "请输入代号") {
		currentSearchCondition ="codeTableCode LIKE '%" +codeTableCodeValue + "%'";
	}  
	if (codeTableNameValue != "请输入名称") {
		currentSearchCondition += " and codeTableName LIKE '%" + codeTableNameValue + "%'";
	} 
	jQuery("#codeTableTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}
//编辑 
function editFun(rowId) {
	DialogUtil.openFloatWindow("module/codetable/codeTableEdit.jsp", {
		codeTableCode : rowId,
		parentCode : null,
		codeType : null,
		level0 : 0 ,
		showFlag : false
	}, {
		EVENT_OK : function(param) {
			CodeTableService.updateEntity(param, "codeTableCode='" + rowId + "'", function(data) {
				if (data) {
					mWin.ok('修改成功');
						var searchCondition = "";
						if (parentCode == null) {
							var searchCode = $("#codeTableCodeSearch")[0].value;
							var searchName = $("#codeTableNameSearch")[0].value;
							if(searchCode!="请输入代号"&&searchName!="请输入名称"){
								searchCondition="codeTableCode like '%"+searchCode+"%' and codeTableName like '%"+searchName+"%'";
							}else if(searchCode!="请输入代号"){
								searchCondition="codeTableCode like '%"+searchCode+"%'";
							}else if(searchName!="请输入名称"){
								searchCondition="codeTableName like '%"+searchName+"%'";
							}else{
								searchCondition = "parentCode is null";	
							}
					} else {
						searchCondition = "parentCode='" + parentCode + "'";
					}
					jQuery("#codeTableTable").setGridParam( {
						condition : searchCondition,
						page : 1
					}).trigger("reloadGrid");
					jQuery("#codeTableTable").setGridParam( {
						condition : 'parentCode is null' //默认是找parentCode为空的
					});
					mWin.ok("修改成功!");
				} else {
					jAlert('修改失败!');
				}
			});
		}
	});

	haveSearch = false;
}

//增加
function addFun() {
	if (haveSearch == true) {
		parentCode = null;
		codeType = null;
		level0 = 0;
	}
//mWin.alert(codeType);
	DialogUtil.openFloatWindow("module/codetable/codeTableEdit.jsp", {
		codeTableCode : null,
		parentCode : parentCode,
		codeType : codeType,
		level0 : level0
	}, {
		EVENT_OK : function(param) {
		//printObject(param);
			CodeTableService.saveWithEntity(param, function(data) {
				if (data) {
					mWin.ok('成功');
					var str = "";
					if (parentCode == null) {
						str = "parentCode is null";
					} else {
						str = "parentCode ='" + parentCode + "'";
					}
					jQuery("#codeTableTable").setGridParam( {
						condition : str
					}).trigger("reloadGrid");
					jQuery("#codeTableTable").setGridParam( {
						condition : 'parentCode is null' 
					});
					mWin.ok("新增成功!");
				} else {
					jAlert('新增失败!');
				}
			});
		}
	});

	haveSearch = false;
}

//删除 
function deleteFun(rowId,parent) {
	if (confirm("删除后将影响到其他功能！你确认要删除该数据吗？")) {
		CodeTableService.deleteByIdAndChild(rowId,parent ,function(data) {
			if (data>0) {
				jQuery("#codeTableTable").setGridParam( {
						condition : _searchCondition,
						page:1
				}).trigger("reloadGrid");
				mWin.ok("删除成功");
			}else{
				jAlert("删除失败！");
			}
		});
	}
}

//删除所有选中的选项  
function deleteSelectedsFun() {
	var selrow = getSelectedRowIDs();
	var parents = [];
	if (selrow.length > 0) {
		if (confirm("【警告】:删除后将影响到其他功能！你确认要删除这"+selrow.length+"条数据吗？")) {
			for(var i = 0; i < selrow.length; i++) {
				parents.push($("#codeTableTable").jqGrid('getCell',selrow[i],'parentCode'));
			}
			CodeTableService.deleteByIdsAndChild(selrow,parents,function(data) {
				if (data>0) {
					jQuery("#codeTableTable").trigger("reloadGrid");
					mWin.ok("删除成功!");
				}else{
					jAlert("删除失败！");
				}
			});
		}
	} else {
		jAlert("请选择数据！");
	}
	return false;
}

function showFun(rowId) {
	var selrow = rowId ? rowId : getSelectedRowID();
	if (selrow != null) {
		DialogUtil.openFloatWindow("module/codetable/codeTableEdit.jsp", {
			codeTableCode : rowId,
			showFlag : true
		}, {
			EVENT_OK : function(param) {}
		});
	}else{
		mWin.alert("请选择数据！");
		return false;
	}
	return false;
}

function getSelectedRowIDs() {
	return jQuery('#codeTableTable').getGridParam('selarrrow');//多选
}
function getSelectedRowID() {
	return jQuery('#codeTableTable').getGridParam('selrow');//单选
}
function getDataIds() {
	return jQuery('#codeTableTable').getDataIDs();
}

//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	if(parentCode!=null){
		parentCode = d3.parentCode;
		codeType = d3.codeType;
		level0 = d3.level0;
	}
	//mWin.alert(parentCode+"_"+codeType);
	params.options.dwrService = CodeTableService;
	params.options.gridName = "codeTableTable";
	var alink = "";
	
	alink =   "<a href='javascript:void(0)' onclick=\"showFun('" + d2.rowId + "');\"><span class='sheetWord'>查看</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"editFun('"+ d2.rowId+ "');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"deleteFun('"+d2.rowId+"','"+d3.parentCode+"');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"clickEvent('"+ d3.codeTableName + "','" + d3.codeTableCode + "','" + d3.codeType + "','" + d3.level0+ "');\"><span class='sheetWord'>进入下级</span></a>";
	return alink;
}

//////进入下一级的事件处理/////////////////////////////////////////////////////////////////
var trace = [];
var node = {
	id : "allNode",
	name : "码表管理",
	parentCode : null
//默认的是查出所有的结果
};

var str = "";
var count = 1;

function clickEvent(codeTableName, currentTableCode, _codeType, _level0) {
	var node = {
		id : "000" + (count++), //id
		name : codeTableName, //当前节点的名称
		parentCode : currentTableCode
	//当前一级的代号，作为下一级的父节点
	};
	parentCode = currentTableCode;
	codeType = _codeType;
	//mWin.alert(codeType);
	if($.trim(level0)==""){
		level0=0;
	}
	if($.trim(_level0)==""){
		_level0=0;
	}
	level0 = _level0 -(-1);//下一级

	trace.push(node);
	$('#allTrace').html(showTrace(0, trace.length - 1));

	//查询
	var traceSearchCondition = "";
	traceSearchCondition = "parentCode='" + currentTableCode + "'";
	_searchCondition=traceSearchCondition;
	//console.info(traceSearchCondition);
	jQuery("#codeTableTable").setGridParam( {
		condition : traceSearchCondition,
		searchCondition:""
	}).trigger("reloadGrid");

	haveSearch = false;
	setCurrentPosition(findCurrentPosition($("#currentPosition").html(),parentCode,codeTableName));
}

function openNext(index) {
	var traceSearchCondition = "";
	if (trace[index].parentCode == null) {
		$("#codeTableCodeSearch").val("");
		$("#codeTableCodeSearch").blur();
		$("#codeTableNameSearch").val("");
		$("#codeTableNameSearch").blur();
		traceSearchCondition = "parentCode is null";
	} else {
		traceSearchCondition = "parentCode='" + trace[index].parentCode + "'";
	}
	jQuery("#codeTableTable").setGridParam( {
		condition : traceSearchCondition,
		page : 1
	}).trigger("reloadGrid");
	$('#allTrace').html(showTrace(0, index));

	haveSearch = false;
}

function showTrace(start, end) {
	var result = "";
	var _trace = [];
	for ( var i = start; i <= end; i++) {
		_trace.push(trace[i]);
		result += "<span class='currentAddress' onclick=\"openNext('" + i + "')\" style='cursor: pointer;'>" + trace[i].name + "</span>/";
	}
	trace = _trace;
	return result;
}


// 默认路径
function initPosition(){
	parentCode =null;
	level0 = 0;
	setCurrentPosition(findCurrentPosition("<span>当前位置：</span>",null,"全部码表"));
}

// 设置当前路径
function findCurrentPosition(curPos,curParentCode,codeTableName){
	var aLink = "";
	if(codeTableName == "全部码表"){
		aLink="<a href='javascript:void(0)' onclick='goBackToPosition(\""+curParentCode+"\",\""+codeTableName+"\");'>"+codeTableName+"</a>";
	}else{
		aLink="-><a href='javascript:void(0)' onclick='goBackToPosition(\""+curParentCode+"\",\""+codeTableName+"\");'>"+codeTableName+"</a>";
	}
	parentCode= curParentCode;
	return curPos + aLink;
}

// 回到某一路径
function goBackToPosition(curParentCode,codeTableName){
	if(curParentCode != "null")
		parentCode = curParentCode;
	else
		parentCode = null;
	var condition = getPosCondition();
	refresh(condition);
	
	if(codeTableName == "全部码表"){
		initPosition();
		return;
	}
	setCurrentPosition(delePosition($("#currentPosition").html(),codeTableName));
}

function setCurrentPosition(curPos){
	$("#currentPosition").html(curPos);
}

// 删除codeTableName后的路径包括codeTableName的路径
function delePosition(curPos,codeTableName){
	var positions = curPos.split("/");
	var update = positions[0];
	
	level0 =0;
	for(var i=1;i<positions.length;i++){
		if(positions[i].indexOf(codeTableName) != -1){
			update += "/" + positions[i] + "/" + positions[i + 1];
			level0 ++;
			break;
		}
		
		update += "/" + positions[i];
		if(positions[i].indexOf("<a") == 0)
			level0 ++;
	}
	
	return update;
}

// 获取路径条件
function getPosCondition(){
	var currentSearchCondition = "1=1 AND parentCode " ;
		currentSearchCondition += (parentCode == null )?"is null":"= '" +parentCode+"'";
	
	return currentSearchCondition;
}

//刷新表格
function refresh(condition){
	//console.info(condition);
	if(condition){
		jQuery("#codeTableTable").setGridParam({
			searchCondition:condition,
			condition:'',
		}).trigger("reloadGrid");
	}else{
		jQuery("#codeTableTable").trigger("reloadGrid");
	}
}
































		