
var code=[];
var mode=0;

$(document).ready(function() {
	initGame();
	
	var codeTableTable = $("#gameTable");
	codeTableTable.jqGrid(gridSetting);
	codeTableTable.jqGrid('navGrid', '#gamePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	code.push({id:$("#game").val(),name:$("#game").find("option:selected").text()});
	setCurrentPosition();
	quickSearch();
	$(window).bind('resize', function() {
		codeTableTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : GameStepService.findMapByPropertiesWithLimit,
	dwrCountFun : GameStepService.findCountByProperties,
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
	colNames : [ 'ID','大赛名称','年份','竞赛类别Code', '竞赛类别','操作' ],
	colModel : [ {
		//代号	
		key : true,
		name : 'gameStepId',
		index : 'gameStepId',
		hidden : true
	}, {
		name:'ga_gameName',
		index:'ga_gameName',
		align:'center'
	},{
		//代码类型
		name : 'ga_year',
		index : 'ga_year',
		align:'center',
		formatter:function(data){
			var state="";
			if(data!=""){
				state=data+"年";
			}
			return state;
		}
	},{
		name : 'gameStep',
		index : 'gameStep',
		align:'center',
		hidden:true
	}, {
		//名称
		name : 'mo_codeTableName',
		index : 'mo_codeTableName',
		align:'center'
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
	pager : '#gamePager',
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
	var game = $("#game").val();   
	var currentSearchCondition = "1=1";
	 if(mode==0){
		 if (game!= "") {
		currentSearchCondition = "ga.gameId ='" +game + "'";
	}  
	 }
	 if(mode==0){
		code=[];
		code.push({id:$("#game").val(),name:$("#game").find("option:selected").text()});
		setCurrentPosition();
	 }

	jQuery("#gameTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
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

function initOne(){
	jQuery('#gameTable').GridUnload();
	$("#gameTable").jqGrid(gridSetting);
	$(window).bind('resize', function() {
		$("#gameTable").setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
}

function initTwo(id){
	jQuery('#gameTable').GridUnload();
	gridSettingNew.condition="me.gameStepID='"+id+"'";
	$("#gameTable").jqGrid(gridSettingNew);
	$(window).bind('resize', function() {
		$("#gameTable").setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
}

//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var	alink =   "<a href='javascript:void(0)' onclick=\"checkTask('" + d2.rowId + "','"+d3.mo_codeTableName+"','"+d3.gameStep+"');\"><span class='sheetWord'>查看竞赛任务</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			/*+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId+ "','"+d3.gameName+"','"+d3.year+"','"+d3.startTime+"','"+d3.endTime+"');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"*/	
		+ "<a href='javascript:void(0)' onclick=\"deleteFun('"+ d2.rowId+ "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	
	return alink;
}

function deleteFun(id){
	jConfirm('确认要删除吗?',function() {
		GameStepService.deleteData(id,function(data){
			if(data){
				mWin.ok("删除成功");
				quickSearch();
			}
			else{
				jAlert("删除失败");
			}
		});
	})
}

//编辑
function exit(id,name,year,startTime,endTime){
	DialogUtil.openFloatWindow("module/gameStepManager/gameEdit.jsp",{name:name,year:year,startTime:startTime,endTime:endTime},{EVENT_OK:function(param){
   		 	GameService.updateEntity(param,"gameId='"+id+"'",function(data){
   		 		if(data){
   		 			mWin.ok("编辑成功");
   		 			quickSearch();
   		 		}
   		 		else{
   		 			jAlert("编辑失败");
   		 		}
   		 	})
   	 }});
}

function addNew(){
	if(mode==0){
		DialogUtil.openFloatWindow("module/gameStepManager/gameAdd.jsp",{},{EVENT_OK:function(param){
	   		 	GameStepService.saveEntity(param,function(data){
	   		 		if(data){
	   		 			mWin.ok("新增成功");
	   		 			quickSearch();
	   		 		}
	   		 		else{
	   		 			jAlert("新增失败");
	   		 		}
	   		 	})
	   	 }});
	}
	else{
		DialogUtil.openFloatWindow("module/gameStepManager/taskAdd.jsp",{gameStep:code[1].id},{EVENT_OK:function(param){
			param.gameStepID=code[1].id;
	   		 	GameStepDetailService.saveEntity(param,function(data){
	   		 		if(data){
	   		 			mWin.ok("新增成功");
	   		 			quickSearch();
	   		 		}
	   		 		else{
	   		 			jAlert("新增失败");
	   		 		}
	   		 	})
	   	 }});
	}
}

function checkTask(id,name,gameStepId){
	initTwo(id);
	code=[];
	code.push({id:$("#game").val(),name:$("#game").find("option:selected").text()});
	code.push({id:id,name:name});
	mode=1;
	setCurrentPosition();
}

var gridSettingNew = {
	height : 'auto',
	datatype : "json",
	dwrFun : GameStepDetailService.findMapByPropertiesWithLimit,
	dwrCountFun : GameStepDetailService.findCountByProperties,
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
	colNames : [ 'ID','大赛ID','竞赛Code','竞赛任务','提交文档开始时间','提交文档截止时间','操作' ],
	colModel : [ {
		//代号	
		key : true,
		name : 'me_gameStepDetailID',
		index : 'me_gameStepDetailID',
		hidden : true
	}, {
		name : 'gs_gameID',
		index : 'gs_gameID',
		hidden : true
	},{
		name:'mo_codeTableCode',
		index:'mo_codeTableCode',
		hidden:true
	},{
		name:'mo_codeTableName',
		index:'mo_codeTableName',
		align:'center'
	},{
		name : 'me_startTime',
		index : 'me_startTime',
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	}, {
		//名称
		name : 'me_endTime',
		index : 'me_endTime',
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	}, {
		//操作
		name : 'codetableColumn',
		index : null,
		formatter : codetableColumn,
		title : false,
		sortable : false,
		align:'center'
	} ],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#gamePager',
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

function codetableColumn(data,d2,d3,d4){
	var alink= "<a href='javascript:void(0)' onclick=\"editTime('" + d2.rowId + "','"+d3.mo_codeTableCode+"','"+d3.me_startTime+"','"+d3.me_endTime+"');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"+
	 "<a href='javascript:void(0)' onclick=\"deleteData('" + d2.rowId + "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
	return alink;
}

function editTime(id,codes,start,end){
	DialogUtil.openFloatWindow("module/gameStepManager/taskAdd.jsp",{code:codes,startTime:start,endTime:end,gameStep:code[1].id,theId:id},{EVENT_OK:function(param){
			param.gameStepDetailID=id;
	   		 	GameStepDetailService.updateEntity(param,"gameStepDetailID='"+id+"'",function(data){
	   		 		if(data){
	   		 			mWin.ok("编辑成功");
	   		 			quickSearch();
	   		 		}
	   		 		else{
	   		 			jAlert("编辑失败");
	   		 		}
	   		 	})
	   	 }});
}

function deleteData(id){
	jConfirm('确认要删除吗?',function() {
		GameStepDetailService.deleteById(id,function(data){
			if(data){
				mWin.ok("删除成功");
				quickSearch();
			}
			else{
				jAlert("删除失败");
			}
		});
	})
}

function setCurrentPosition(){
	var html="<span>当前位置:"
	for(var i=0;i<code.length;i++){
		if(i==code.length-1){
			html+="<a onclick=\"changePages('"+i+"','"+code[i].id+"')\">"+code[i].name+"</a>";
		}
		else{
			html+="<a onclick=\"changePages('"+i+"','"+code[i].id+"')\">"+code[i].name+"</a>->";
		}
	}
	html+="</span>"

	$("#currentPosition").html(html);
}

function changePages(choose,id){
	if(choose!=mode){
		if(choose==0){
			initOne();
			code=[];
			code.push({id:$("#game").val(),name:$("#game").find("option:selected").text()});
			mode=0;
			setCurrentPosition();
		}
	}
}