
//全局对象
var globalVar = {
	$projectApplyStatTable : $('#projectApplyStatTable'),
	$projectApplyStatPager : $('#projectApplyStatPager')
};

gridSetting = {
	height:'auto',
	width:'auto',
	datatype : "json",
	dwrFun : ProjectService.findMapByPropertiesWithLimit,
	dwrCountFun : ProjectService.findCountByProperties,
	condition : "",
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	//shrinkToFit : false, //auScroll要和shrinkToFit同时设置才能正常出现滚动条
	autoScroll : true,
	colNames : [ '项目ID', '项目名称', '申报单位', '申报金额（万元）', '总投资（万元）' ],
	colModel : [ {
		key:true,
		name : 'projectID',
		index : 'projectID',
		hidden:true
	}, {
		name:'projectName',
		index:'projectName',
		align:'left',
		width: 254,
		sortable:false
	}, {
		name:'organizationName',
		index:'organizationName',
		align:'left',
		width: 150,
		sortable:false
	}, {
		name:'subsidizeAmount',
		index:'subsidizeAmount',
		align:'right',
		width: 90,
		sortable:false
	}, {
		name:'totalAmount',
		index:'totalAmount',
		width: 90,
		align:'right',
		sortable:false
	}],
	rowNum : -1,
	//rowList : [ 10, 20, 30 ],
	pager : '#projectApplyStatPager',
	viewrecords : true,
	rownumbers : true,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false	// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	}
};

$(function(){
	dwr.engine.setAsync(false);
	//加载自定义树,后两个方法必须写好
	setTypeAndID(0,"projectApplyStatTable","batchID");//调用方法前的配置，配置批次类型和自己主表的id（用于刷新）
	getHtmlFun();
	dwr.engine.setAsync(true);
	
	FormUtil.createComponent(["search_print"],{});
	
	$(globalVar.$projectApplyStatTable).jqGrid(gridSetting);
	$(globalVar.$projectApplyStatTable).jqGrid('navGrid', '#projectApplyStatPager', {
		edit : false,
		add : false,
		del : false,
		search:false
	});
	
	$(window).bind('resize', function() {
		globalVar.$projectApplyStatTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
});

function quickSearch() {
	var con = '',
		projectName = $('#search_projectName').val(), 
		organizationName = $('#search_organizationName').val(); 
	if(projectName && projectName != '请输入项目名称') {
		con += " AND projectName LIKE '%"+projectName+"%'";
	}
	if(organizationName && organizationName != '请输入申报单位') {
		con += " AND organizationName LIKE '%"+organizationName+"%'";
	}
	if(con){
		con = con.substring(4);
	}
	refreshHandler(con);
}

//按项目批次导出[项目申报信息]
function exportToExcel() {
	if(batchID) {
		ProjectService.exportProjectApplyStatTable(batchID, function(fileTransfer){
			if(fileTransfer) {
				dwr.engine.openInDownload(fileTransfer);
			}
		});
	} else {
		alert('请您选择申报批次！');
	}
}

//JGrid刷新
var refreshHandler = function(condition){
	if(!condition) condition = "";
	globalVar.$projectApplyStatTable.setGridParam({ page:1, searchCondition : condition }).trigger("reloadGrid");
}

//
////获取多行ID
//function getSelectedRowIDs(){
//	return globalVar.$auditProjectTable.getGridParam('selarrrow');//多选
//}
////获取单行ID
//function getSelectedRowID(){
//	return globalVar.$auditProjectTable.getGridParam('selrow');//单选
//}
//
//function getRowData(rowid) {
//	var rowData = {};
//	if(rowid) {
//		rowData = globalVar.$auditProjectTable.getRowData(rowid);
//	}
//	return rowData;
//}

	