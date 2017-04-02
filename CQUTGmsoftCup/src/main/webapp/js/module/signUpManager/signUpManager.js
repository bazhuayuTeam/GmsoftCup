
$(document).ready(function() {
	initGame();
	initStyle();
	initStep();
	initState();
	
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
	quickSearch();
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : TeamService.findMapByPropertiesWithLimitTeam,
	dwrCountFun : TeamService.findCountByProperties,
	condition : "checkState!='2'",
	searchCondition : "",
	selectCondition : '',
	needLink : true,
	dwr : true,
	checkable:true,
	autowidth : true,
	loadComplete:function(){
		$(window).trigger('resize');
	},
	colNames : [ 'ID','竞赛名称','竞赛','类别ID', '竞赛类别', '团队名称', '参赛题目', '项目编号','组长ID', '组长','组员', '指导教师','状态', '操作' ],
	colModel : [ {
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
		name:'crewName',
		index:null
	},{
		//等级
		name : 'teacher',
		index : null,
		align : 'center',
		hidden : false,
		sortable : false
	},{
		name:'checkState',
		index:'checkState',
		align:'center',
		formatter:function(data){
			var state="";
			if(data=="0"){
				state="待审核";
			}
			else if(data=="1"){
				state="已通过";
			}
			else if(data=="3"){
				state="被退回";
			}
			return state;
		}
	}, {
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

function initState(){
	var html="";
	html+="<option value='all'>全部</option>";
	html+="<option value='0'>待审核</option>";
	html+="<option value='1'>通过</option>";
	html+="<option value='3'>退回</option>";
	$("#state").append(html);
}

function initStep(){
	var html="";
	var gameid=$("game").val();
	dwr.engine.setAsync(false);
	GameStepService.findMapByPropertiesQuick(["gameStepName"],"gameid='"+gameid+"'",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepName"]+"'>"+data[i]["gameStepName"]+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#gameStep").append(html);
}

//功能方法---------------------------------------------------------
//search
function quickSearch() {
	var gameStyle = $("#gameStyle").val();   
	var gameStep = $("#gameStep").val(); 
	var state = $("#state").val();
	var currentSearchCondition = "1=1 and gg.gameid='"+$("#game").val()+"'";
	 if (gameStyle!= "all") {
		currentSearchCondition ="gameStyle='" +gameStyle + "'";
	}  
	if (gameStep != "all") {
		currentSearchCondition += " and mo.codeTableCode LIKE '%" + gameStep + "%'";
	} 
	if(state!="all"){
		currentSearchCondition+=" and checkState ='"+state+"'";
	}
	jQuery("#codeTableTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}

function getSelectedRowID() {
	return jQuery('#codeTableTable').getGridParam('selrow');//单选
}
function getDataIds() {
	return jQuery('#codeTableTable').getDataIDs();
}

//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var state=d3.checkState;
	var alink = "";
	if(state==0||state==null||state==""){
		alink =   "<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>通过</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId+ "');\"><span class='sheetWord'>退回</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";	
	}		
	return alink;
}

//通过
function agree(id){
	if(confirm("是否确定通过？")){
		TeamService.update(id,"1",function(data){
			if(data){
				mWin.ok("通过成功");
				quickSearch();
			}
			else{
				alert("通过失败");
			}
		});
	}
}

//退回
function exit(id){
	if(confirm("是否确定退回？")){
		TeamService.update(id,"3",function(data){
			if(data){
				mWin.ok("退回成功");
				quickSearch();
			}
			else{
				alert("退回失败");
			}
		});
	}
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


//导出
function exportData(){
	if(confirm("您确定要导出吗?")){
		$("#MainArea").mask("正在导出中，请耐心等候......");
		var gameStyle = $("#gameStyle").val();   
		var gameStep = $("#gameStep").val(); 
		var state = $("#state").val();
		var currentSearchCondition = "checkState!='2' and gg.gameid='"+$("#game").val()+"'";
		
		 if (gameStyle!= "all") {
			currentSearchCondition ="gameStyle='" +gameStyle + "'";
		}  
		if (gameStep != "all") {
			currentSearchCondition += " and mo.codeTableCode LIKE '%" + gameStep + "%'";
		} 
		if(state!="all"){
			currentSearchCondition+=" and checkState ='"+state+"'";
		}
		var deriveSetting = {
			dwrFun : TeamService.exportTable,
			properties : ['teamId','ga_gameStepID','mo_codeTableCode','mo_codeTableName','teamName','title','no','captianId','checkState'],
			condition : currentSearchCondition,
			needLink : true,
			templateName : "Propo",
			lineNumber : 1,   
		  	orderNumber : false,
		    excelName : "报名名单"
		 };
		 DeriveExel.initDeriveExel(deriveSetting);  
		 $("#MainArea").unmask();
	 }
}