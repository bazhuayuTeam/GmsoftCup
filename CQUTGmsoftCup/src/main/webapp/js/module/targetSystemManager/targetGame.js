;
var pageData={
	targetSystemName:null,
	standard :null,
};
//入口
$(document).ready(function(){
	pageData.data = ChildDialogUtil.getExchangeData();
	$("#targetHead").html(pageData.data.name);
	$("#targetSystemTable").jqGrid(gridSetting);
	$(window).bind('resize', function() { 
	    $("#targetSystemTable").setGridWidth($("#targetSystemPager").width()-2); 
	}).trigger('resize');
});

var gridSetting = {
	height : 'auto',
	autowidth : true,
	datatype : "json",
	dwr : true,
	dwrFun : GameStepDetailService.findPropertiesWithLimit,
	dwrCountFun : GameStepDetailService.findCountByProperties,
	condition : 'me.standardVersionID=\''+id+'\'',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	colNames : ["竞赛id","竞赛名称","年份","竞赛阶段id","竞赛阶段","竞赛详情id","赛制流程"],
	colModel : [{
		name : 'ga_gameID',
		index : 'ga_gameID',
		hidden : true
	}, {
		name : 'ga_gameName',
		index : 'ga_gameName',
		align : 'center'
	},{
		name : "ga_year",
		index : "ga_year",
		align : 'center',
		hidden:true
	},{
		name : "gs_gameStepID",
		index : "gs_gameStepID",
		align : 'center',
		hidden : true
	},{
		name : "gc_codeTableName",
		index : "gc_codeTableName",
		align : 'center'
	},{
		name : "me_gameStepDetailID",
		index : "me_gameStepDetailID",
		align : 'center',
		hidden : true
	},{
		name : "type",
		index : "me_type",
		align : 'center'
	}],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#targetSystemPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records",
		repeatitems : false
	},
	multiselect : true,
	multiboxonly : true,
	altRows : true,
	altclass : 'altclass'
};
