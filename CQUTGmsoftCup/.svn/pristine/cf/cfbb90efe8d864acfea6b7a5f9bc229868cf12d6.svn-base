
$(document).ready(function() {
	initGame();
	
	var codeTableTable = $("#codeTableTable");
	gridSetting.searchCondition="gameStepDetailID='"+$("#task")+"'";
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
	
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : GameResultService.findMapByPropertiesWithLimitNew,
	dwrCountFun : GameResultService.findCountByProperties,
	condition : "",
	searchCondition : "",
	selectCondition : '',
	needLink : true,
	dwr : true,
	checkable:true,
	autowidth : true,
	loadComplete:function(){
		$(window).trigger('resize');
	},
	colNames : [ 'ID','竞赛详情ID','团队名称','团队ID', '参赛题目', '项目编号','组长ID', '组长','评审评委', '操作' ],
	colModel : [ {
		//代号	
		key : true,
		name : 'Code',
		index : 'Code',
		hidden : true
	},{
		name:'gameStepDetailID',
		index:'gameStepDetailID',
		hidden:true
	}, {
		//名称
		name : 'mo_teamName',
		index : 'mo_teamName',
		sortable : false,
		hidden : false,
		align:'center'
	},{
		//代码类型
		name : 'mo_teamID',
		index : 'mo_teamID',
		hidden:true
	},{
		//父亲节点
		name : 'mo_title',
		index : 'mo_title',
		align : 'center'
	}, {
		//是否有子节点
		name : 'mo_no',
		index : 'mo_no',
		align : 'center'
	}, {
		//拼音字母
		name : 'mo_captianId',
		index : 'mo_captianId',
		align : 'center',
		hidden:true
	}, {
		//是否有子节点
		name : 'ur_name',
		index : 'ur_name',
		align : 'center'
	}, {
		name:'expert',
		index:null,
		formatter:function(data){
			return data;
		}
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

function initGame(){
	var html="";
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>";
		}
	});
	dwr.engine.setAsync(true);
	$("#game").append(html);
	
	initStep();
}

function initStep(){
	$("#gameStep").empty();
	var html="";
	dwr.engine.setAsync(false);
	GameStepService.findMapByPropertiesQuick(["gameStep","gameStepID","mo_codeTableName"],"gameID='"+$("#game").val()+"'",true,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepID"]+"'>"+data[i]["mo_codeTableName"]+"</option>";
		}
	});
	dwr.engine.setAsync(true);
	$("#gameStep").append(html);
	
	initTask();
}

function initTask(){
	$("#task").empty();
	var html="";
	dwr.engine.setAsync(false);
	GameStepDetailService.findMapByPropertiesQuick(["gameStepDetailID","type","mo_codeTableName"],"gameStepID='"+$("#gameStep").val()+"'",true,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepDetailID"]+"'>"+data[i]["mo_codeTableName"]+"</option>";
		}
	});
	dwr.engine.setAsync(true);
	$("#task").append(html);
	quickSearch();
}

//功能方法---------------------------------------------------------
//search
function quickSearch() {
	var task = $("#task").val();
	var currentSearchCondition = "gameStepDetailID='"+task+"'";
	
	jQuery("#codeTableTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}

function getSelectedRowID() {
	return jQuery('#codeTableTable').getGridParam('selrow');//单选
}
function getDataIds() {
	return jQuery('#codeTableTable').getGridParam('selarrrow');//多选
}

//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var state=d3.checkState;
	var alink = "";
	alink =   "<a href='javascript:void(0)' onclick=\"arrangeExpert('1','" + d3.mo_teamID + "');\"><span class='sheetWord'>指定评委</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"	
		
	return alink;
}

function arrangeExpert(type,id){
	var selrow = [];
	if(type=="0"){
		selrow = getDataIds();
		if(selrow==null||selrow.length==0){
			jAlert("请选择团队");
			return false;
		}
	}
	else{
		selrow.push(id);
	}
	
	DialogUtil.openFloatWindow("module/arrangeExpertManager/arrange.jsp",{id:id,task:$("#task").val()},{EVENT_OK:function(param){
		var teacher="";
		for(var i=0;i<param.teacherAppend.length;i++){
			
			if(i==param.teacherAppend.length-1){
				teacher+=param.teacherAppend[i];
			}
			else{
				teacher+=param.teacherAppend[i]+",";
			}
		}
   		 	ExpertTargetDistributeService.saveData(teacher,selrow,$("#task").val(),function(data){
   		 		if(data){
   		 			mWin.ok("分配成功");
   		 			quickSearch();
   		 		}
   		 		else{
   		 			jAlert("分配失败");
   		 		}
   		 	});
   	 }});
}

