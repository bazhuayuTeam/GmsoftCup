//页面加载
$(document).ready(function() {
	//initSelect();//初始化搜索下拉框
	initGame();
	initGameStep();
	initStep();
	jQuery("#table").jqGrid(gridSetting);	
});

function initGame(){
	var html="";
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime desc",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#name").append(html);
}

function initGameStep(){
	var html="";
	dwr.engine.setAsync(false);
	CodeTableService.findMapByPropertiesQuick(['codeTableName',"codeTableCode"],
		"codeTableCode like 'task%' and hasChild='0' and createId='"+operatorId+"'",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#task").append(html);
}

function initStep(){
	var html="";
	var gameid=$("#game").val();
	dwr.engine.setAsync(false);
	GameStepService.findMapByPropertiesQuick(["gameStepName"],"gameid='"+gameid+"'",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepName"]+"'>"+data[i]["gameStepName"]+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#clazz").append(html);
}


function initSelect(){
	dwr.engine.setAsync(false);
	//加载竞赛名称下拉框
	GameService.findMapByProperties([ 'gameName','gameId'], 
		"state = '1'", 'gameId', 'asc',false, function(data) {
		if(data){
			var html = "";
			for(var i in data) {
				html+= "<option value='" + data[i]['gameId'] + "'>" + data[i]['gameName'] + "</option>";
			}
			$("#name").empty();
			$("#name").html(html);
		}
	});
	//加载竞赛类别下拉框
	CodeTableService.findMapByProperties([ 'CodeTableName', 'CodeTableCode'], 
		"parentCode = 'gameStep'", 'CodeTableCode', 'asc',false, function(data) {
			if(data){
				var html = "";
				for(var i in data) {
					html+= "<option value='" + data[i]['CodeTableCode'] + "'>" + data[i]['CodeTableName'] + "</option>";
				}
				$("#clazz").empty();
				$("#clazz").html(html);
			}
	});
	//加载竞赛任务下拉框
	CodeTableService.findMapByProperties([ 'CodeTableName', 'CodeTableCode'], 
		"parentCode = 'task'", 'CodeTableCode', 'asc',false, function(data) {
			if(data){
				var html = "<option value='all'>全部</option>";
				for(var i in data) {
					html+= "<option value='" + data[i]['CodeTableCode'] + "'>" + data[i]['CodeTableName'] + "</option>";
				}
				$("#task").empty();
				$("#task").html(html);
			}
	});
	dwr.engine.setAsync(true);
}

var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : TeamService.findMapByPropertiesWithLimitCheck,
	dwrCountFun : TeamService.findCount,
	condition : '1=1',
	searchCondition : "",
	selectCondition : '',
	needLink : true,
	dwr : true,
	checkable:true,
	autowidth : true,
	colNames : ["teamId","","结果id","gameId","gameStepID","评委id","gameStepDetailID","团队名称","组长","组员","指导老师","总分数","操作"],
	colModel : [{
		key : true,
		name : "me_teamId",
		index : "me_teamId",
		hidden : true,
	},{
		key : true,
		name : "gr_code",
		index : "gr_code",
		hidden : true,
	},{
		key : true,
		name : "tg_code",
		index : "tg_code",
		hidden : true,
	},{
		key : true,
		name : "gg_gameId",
		index : "gg_gameId",
		hidden : true,
	},{
		key : true,
		name : "ga_gameStepID",
		index : "ga_gameStepID",
		hidden : true,
	},{
		key : true,
		name : "te_expertID",
		index : "te_expertID",
		hidden : true,
	},{
		key : true,
		name : "gs_gameStepDetailID",
		index : "gs_gameStepDetailID",
		hidden : true,
	},{
		name : "me_teamName",
		index : "me_teamName",
		align : 'center',
	},{
		name : "na_name",
		index : "na_name",
		align : 'center',
	},{
		name:'crewName',
		index:null,
		align : 'center',
	},{
		name : 'teacher',
		index : null,
		align : 'center',
		hidden : false,
		sortable : false
	},{
		name : 'score',
		index : null,
		align : 'center',
	},{
		name : "operatorColumn",
		index : null,
		align : "center",
		title : false,
		sortable : false,
		formatter : operatorColumn_formater,
	}],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#pager',
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

//得到操作栏
function operatorColumn_formater(data, d2, d3, d4) {
	var url = "module/exprtScore/grade/gradeManager.jsp?teamId="+d3.me_teamId+"&gameStepID="+d3.ga_gameStepID+"&gameStepDetailID="+d3.gs_gameStepDetailID+"&teamName="+d3.me_teamName;
	return "<a href='"+url+"'><span class='sheetWord'>进入打分</span></a>";
}

//搜索
function quickSearch() {  //type判断是学院改变还是专业改变
	var name = $("#name").val();
	var clazz = $("#clazz").val();
	var task = $("#task").val();
	var conditon = " 1=1 ";
	if(name!="all"){
		conditon += " and ga.gameID = '"+name+"' ";
	}
	if(clazz!="all"){
		conditon += " and ga.gameStep = '"+clazz+"' ";	
	}
	if(task!="all"){
		conditon += " and gs.type = '"+task+"' ";
	}
	$("#table").setGridParam({condition : conditon, page : 1}).trigger("reloadGrid");
	$("#content").unmask();
}
