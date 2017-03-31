
$(document).ready(function() {
	initGame();
	initStyle();
	initStep();
	initState();
	initGameStep();
	
	var codeTableTable = $("#codeTableTable");
	codeTableTable.jqGrid(gridSetting);
	codeTableTable.jqGrid('navGrid', '#codeTablePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	quickSearch();
	$(window).bind('resize', function() {
		codeTableTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	
	
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : TeamService.findMapByPropertiesWithLimitTeamNew,
	dwrCountFun : TeamService.findCountByProperties,
	condition : "checkState='1'",
	searchCondition : "",
	selectCondition : '',
	needLink : true,
	dwr : true,
	checkable:true,
	autowidth : true,
	loadComplete:function(){
		$(window).trigger('resize');
	},
	colNames : [ 'ID','竞赛id','竞赛','类别ID', '竞赛类别','竞赛任务', '团队名称', '参赛题目', '项目编号','组长ID', '组长', '指导教师','状态','是否提交文档','文件ID', '操作' ],
	colModel : [  {
		//代号	
		key : true,
		name : 'teamId',
		index : 'teamId',
		hidden : true
	},{
		name:'gg_gameID',
		index:'gg_gameID',
		hidden:true
	} ,{
		name:'ga_gameStepID',
		index:'ga_gameStepID',
		hidden:true
	},{
		//代码类型
		name : 'mo_codeTableCode',
		index : 'mo_codeTableCode',
		hidden:true
	},{
		name : 'mo_codeTableName',
		index : 'mo_codeTableName',
		align:'center'
	}, {
		name:'gs_type',
		index:'gs_type',
		hidden:true
	},{
		//名称
		name : 'teamName',
		index : 'teamName',
		sortable : false,
		hidden : false,
		align:'center'
	}, {
		//父亲节点
		name : 'title',
		index : 'title',
		align : 'center'
	}, {
		//是否有子节点
		name : 'no',
		index : 'no',
		align : 'center'
	}, {
		//拼音字母
		name : 'captianId',
		index : 'captianId',
		align : 'center',
		hidden:true
	},{
		name : 'captianName',
		index : null,
		align : 'center'
	} ,{
		//等级
		name : 'teacher',
		index : null,
		align : 'center',
		hidden : false,
		sortable : false
	},{
		name:'checkState',
		index:'checkState',
		hidden:true
	},{
		name:'fileExit',
		index:null,
		align:'center',
		formatter:function(data){
			if(data=="0"){
				return "否";
			}
			else if(data=="1"){
				return "是";
			}
		}
	} ,{
		name:'fileId',
		index:null,
		hidden:true
	},{
		//操作
		name : 'codetableColumn',
		index : null,
		formatter : codetableColumn_formater,
		title : false,
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

function initStyle(){
	var html="";
	html+="<option value='all'>全部</option>";
	html+="<option value='0'>团队</option>";
	html+="<option value='1'>个人</option>";
	//html+=""
	$("#gameStyle").append(html);
}

function initGame(){
	var html="";
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime desc",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#game").append(html);
}

function initGameStep(){
	var html="";
	dwr.engine.setAsync(false);
	CodeTableService.findMapByPropertiesQuick(['codeTableName',"codeTableCode"],
		"codeTableCode like 'task%' and hasChild='0'",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#type").append(html);
}


function initState(){
	var html="";
	html+="<option value='all'>全部</option>";
	html+="<option value='1'>是</option>";
	html+="<option value='0'>否</option>";
	$("#state").append(html);
}

function initStep(){
	var html="";
	dwr.engine.setAsync(false);
	GameStepService.findMapByPropertiesQuick(["gameStep","mo_codeTableName"],"true=true",true,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStep"]+"'>"+data[i]["mo_codeTableName"]+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#gameStep").append(html);
}

//功能方法---------------------------------------------------------
//search
function quickSearch() {   
	var gameStep = $("#gameStep").val(); 
	var type=$("#type").val();
	var currentSearchCondition="gs.type='"+type+"'";
	var gameid=$("#game").val();
	if (gameStep != "all") {
		currentSearchCondition += " and mo.codeTablecode='" + gameStep + "'";
	} 
	currentSearchCondition+=" and gg.gameid='"+gameid+"'";
	jQuery("#codeTableTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}

//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var state=d3.fileExit;
	var fileId=d3.fileId;
	var alink = "";
	if(state=="1"){
		alink = "<a href='javascript:void(0)' onclick=\"getID('" + d2.rowId + "','"+d3.gs_type+"');\"><span class='sheetWord'>下载文档</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"		
	}		
	return alink;
}

function getID(id,type){
	dwr.engine.setAsync(false);
	ProjectService.findMapByPropertiesQuick(["fileId"],"teamId='"+id+"' and type='"+type+"'",false,function(data){
		if(data.length>0){
			loadFile(data[0]["fileId"]);
		}
		else{
			alert('该文件不存在');
		}
	})
	dwr.engine.setAsync(true);
}


//下载文档
function loadFile(fileID){
	SystemFileService.downloadFile(fileID, function(data){
			if (data)
			{
				dwr.engine.openInDownload(data);
			} else{
				alert('该文件不存在');
			}
		});
}