var main_params={
		stepID:false,
		gameID:false,
		trace:[]
};
$(document).ready(function() {
	$('#game').Watermark("请输入大赛名称", "#8f8f8f");
	var codeTableTable = $("#gameTable");
	codeTableTable.jqGrid(gridSetting);
	codeTableTable.jqGrid('navGrid', '#gamePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	$(window).bind('resize', function() {
		codeTableTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	var gameStepTable = $("#gameStepTable");
	gameStepTable.jqGrid(gridStepSetting);
	gameStepTable.jqGrid('navGrid', '#gameStepPager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	$(window).bind('resize', function() {
		gameStepTable.setGridWidth($("#MainStepArea").width() - 2);
	}).trigger('resize');
	var gameStepDetailTable = $("#gameStepDetailTable");
	gameStepDetailTable.jqGrid(gridStepDetailSetting);
	gameStepDetailTable.jqGrid('navGrid', '#gameStepDetailPager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	$(window).bind('resize', function() {
		gameStepDetailTable.setGridWidth($("#MainStepDetailArea").width() - 2);
	}).trigger('resize');
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : GameService.findMapByPropertiesWithLimit,
	dwrCountFun : GameService.findCountByProperties,
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
	colNames : [ 'ID','竞赛名称', '报名开始时间', '报名截止时间','参赛形式','是否多阶段','主办单位','状态','操作' ],
	colModel : [ {
		//代号	
		key : true,
		name : 'me_gameID',
		index : 'me_gameID',
		hidden : true
	}, {
		name:'me_gameName',
		index:'me_gameName',
		align:'center'
	},{
		name : 'me_signUpStartTime',
		index : 'me_signUpStartTime',
		align:'center',
		formatter:function(data){
			if(data){
				return DateUtil.dateDiffMills(data);
			}else{
				return ""
			}
		}
	}, {
		//名称
		name : 'me_sigeUpEndTime',
		index : 'me_sigeUpEndTime',
		align:'center',
		formatter:function(data){
			if(data){
				return DateUtil.dateDiffMills(data);
			}else{
				return ""
			}
		}
	},{
		//参赛形式
		name : 'me_competitionType',
		index : 'me_competitionType',
		align:'center',
		width:80,
		formatter:function(data){
			var type=data==0?"团队 ":"个人";
			return type;
		}
	},{
		name : 'me_isMultiStage',
		index : 'me_isMultiStage',
		align:'center',
		width:80,
		formatter:function(data){
			var type=data==0?"否 ":"是";
			return type;
		}
	},{
		name : 'hostUnit',
		index : 'hostUnit',
		align:'center'
	}, {
		//父亲节点
		name : 'me_state',
		index : 'me_state',
		formatter:function(data){
			var states=["暂存","待审核 ","审核通过","被退回"];
			return states[data];
		},
		align:'center'
	}, {
		//操作
		name : 'codetableColumn',
		index : null,
		formatter : codetableColumn_formater,
		title : false,
		sortable : false,
		align:'center',
		width:400
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

var gridStepSetting = {
		height : 'auto',
		datatype : "json",
		dwrFun : GameService.findGameStep,
		dwrCountFun : GameService.findCountByProperties,
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
		colNames : [ 'ID','竞赛名称', '赛制流程', '时间','是否需要提交作品','提交作品时间','操作'],
		colModel : [ {
			//代号	
			key : true,
			name : 'gs_gameStepID',
			index : 'gs_gameStepID',
			hidden : true
		}, {
			name:'me_gameName',
			index:'me_gameName',
			align:'center'
		},{
			name : 'ct_codeTableName',
			index : 'ct_codeTableName',
			align:'center'
		}, {
			//名称
			name : 'gsd_gameTime',
			index : 'gsd_gameTime',
			align:'center',
			formatter:function(data){
				if(data){
					return DateUtil.dateDiffMills(data);
				}else{
					return ""
				}
			}
		},{
			name : 'gsd_works',
			index : 'gsd_works',
			align:'center',
			width:80,
			formatter:function(data){
				var type=data==0?"否 ":"是";
				return type;
			}
		},{
			name : 'gsd_startTime',
			index : 'gsd_startTime',
			align:'center',
			formatter:function(data){
				if(data){
					return DateUtil.dateDiffMills(data);
				}else{
					return ""
				}
			}
		}, {
			//操作
			name : 'gameStepColumn',
			index : null,
			formatter : gameStepColumn_formater,
			title : false,
			sortable : false,
			align:'center',
			width:250
		} ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#gameStepPager',
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
var gridStepDetailSetting = {
		height : 'auto',
		datatype : "json",
		dwrFun : GameService.findGameStep,
		dwrCountFun : GameService.findCountByProperties,
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
		colNames : [ 'ID','竞赛名称','阶段名称', '报名开始时间', '报名截止时间','参赛形式','操作' ],
		colModel : [ {
			//代号	
			key : true,
			name : 'me_gameID',
			index : 'me_gameID',
			hidden : true
		}, {
			name:'me_gameName',
			index:'me_gameName',
			align:'center'
		}, {
			name:'gs_gameStepName',
			index:'gs_gameStepName',
			align:'center'
		},{
			name : 'me_signUpStartTime',
			index : 'me_signUpStartTime',
			align:'center',
			formatter:function(data){
				if(data){
					return DateUtil.dateDiffMills(data);
				}else{
					return ""
				}
			}
		}, {
			//名称
			name : 'me_sigeUpEndTime',
			index : 'me_sigeUpEndTime',
			align:'center',
			formatter:function(data){
				if(data){
					return DateUtil.dateDiffMills(data);
				}else{
					return ""
				}
			}
		},{
			//参赛形式
			name : 'me_competitionType',
			index : 'me_competitionType',
			align:'center',
			width:80,
			formatter:function(data){
				var type=data==0?"团队 ":"个人";
				return type;
			}
		},{
			//操作
			name : 'stepDetailColumn',
			index : null,
			formatter : gameStepDetailColumn_formater,
			title : false,
			sortable : false,
			align:'center',
			width:350
		} ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#gameStepDetailPager',
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
	 if (game!= ""&&game!="请输入大赛名称") {
		currentSearchCondition = "gameName like'%" +game + "%'";
	}  
	jQuery("#gameTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}


//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var state=d3.me_state;
	var alink = "";
	if(d3.me_isMultiStage==1){
		alink+="<a href='javascript:void(0)' onclick=\"setStepDetail('" + d3.gs_gameStepID + "','"+d3.me_gameName+"');\"><span class='sheetWord'>设置竞赛阶段</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
	}else{
		alink+="<a href='javascript:void(0)' onclick=\"setGameStep('" + d2.rowId + "','"+d3.me_gameName+"');\"><span class='sheetWord'>设置竞赛流程</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
	}
	if(state==0){  //停用
		alink +=   "<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>申请发布</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId + "');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"	
		+ "<a href='javascript:void(0)' onclick=\"deleteFun('"+ d2.rowId+ "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	}		
	else if(state==1){
		alink +=   "<a href='javascript:void(0)' onclick=\"agreeNot('" + d2.rowId + "');\"><span class='sheetWord'>申请取消发布</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
		+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId + "','readonly');\"><span class='sheetWord'>详情</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"	
	}
	return alink;
}

function gameStepColumn_formater(data, d2, d3, d4) {
	var alink = "";
		alink =   "<a href='javascript:void(0)' onclick=\"editStep('" + d2.rowId + "');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a href='javascript:void(0)' onclick=\"removeStep('" + d2.rowId + "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	return alink;
}
function gameStepDetailColumn_formater(data, d2, d3, d4) {
	var alink = "";
		alink = "<a href='javascript:void(0)' onclick=\"setGameStep('" + d2.rowId + "','"+d3.gs_gameStepName+"');\"><span class='sheetWord'>设置竞赛流程</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"  
		+"<a href='javascript:void(0)' onclick=\"exit('" + d2.rowId + "');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a href='javascript:void(0)' onclick=\"deleteFun('" + d2.rowId + "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	return alink;
}

function deleteFun(id){
	jConfirm('确认要删除吗?',function() {
		GameService.deleteById(id,function(data){
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

//启用
function agree(id){
	var game={
		gameId:id,
		state:1
	}
	dwr.engine.setAsync(false);
	GameService.updateEntity(game,"gameId='"+id+"'",function(data){
		if(data){
			mWin.ok("发布成功");
			quickSearch();
		}
		else{
			jAlert("发布失败");
		}
	});
	dwr.engine.setAsync(true);
}

function agreeNot(id){
	var game={
		gameId:id,
		state:0
	};
	GameService.updateEntity(game,"gameId='"+id+"'",function(data){
		if(data){
			mWin.ok("取消成功");
			quickSearch();
		}
		else{
			jAlert("取消失败");
		}
	});
}

//编辑
function exit(id,type){
	DialogUtil.openFloatWindow("module/gameManager/gameEdit.jsp",{id:id,type:type},{EVENT_OK:function(param){
   		 	GameService.updateData(param.game,"gameId='"+id+"'",function(data){
   		 		if(data){
	   		 		param.hostUnit.gameId=id;
	   				HostUnitService.updateData(param.hostUnit,function (data){
	   					mWin.ok("编辑成功");
	   		 			quickSearch();	
	   				});
   		 		}else{
   		 			jAlert("编辑失败");
   		 		}
	 		});
   	 }});
}

function addNew(){
	var isStep=$("#gameStepTableContainer").hasClass("hidden");
	if(isStep){
		DialogUtil.openFloatWindow("module/gameManager/gameAdd.jsp",{},{EVENT_OK:function(param){
   		 	GameService.saveData(param.game,function(data){
   		 		if(data){
   		 			param.hostUnit.gameId=data;
   		 			HostUnitService.saveData(param.hostUnit,function (data){
   		 				if(data){
	   		 				mWin.ok("新增成功");
		   		 			quickSearch();
   		 				}else{
   		 					jAlert("新增失败");
   		 				}
   		 			});
   		 		}
   		 		else{
   		 			jAlert("新增失败");
   		 		}
   		 	});
   	 	}});
	}else{
		DialogUtil.openFloatWindow("module/gameStepManager/gameAdd.jsp",{},{EVENT_OK:function(param){
			var gameStepParam={
				gameId:main_params.gameID	
			};
			GameStepDetailService.saveData(param,gameStepParam,function(data){
		 		if(data){
		 			mWin.ok("新增成功");
		 			setGameStep(main_params.gameID);	
		 		}else{
		 			jAlert("新增失败");
		 		}
			});
   	 	}});
	}
}

function setGameStep(id,name){
	main_params.gameID=id;
	$(".searchList").css("visibility","hidden");
	$(".navigate").removeClass("hidden");
	switchtTable("gameStepTableContainer");
	jQuery("#gameStepTable").setGridParam( {
		searchCondition : "gs.gameID='"+id+"'",
		page:1
	}).trigger("reloadGrid");
	if($("#gameStepDetailTableContainer").hasClass("hidden")){
		name&&showTrace(id,name,0);
	}else{
		name&&showTrace(id,name,1);
	}
}

function editStep(id){
	DialogUtil.openFloatWindow("module/gameStepManager/gameEdit.jsp",id,{EVENT_OK:function(param){
		 	GameStepDetailService.updateEntity(param,"gameStepID='"+id+"'",function(data){
		 		if(data){
		 			mWin.ok("编辑成功");
		 			setGameStep(main_params.gameID);	
		 		}else{
		 			jAlert("编辑失败");
		 		}
 		});
	 }});
}
function removeStep(id){
	var r=confirm("确定删除？");
	r&&GameStepDetailService.deleteStep(id,function (data){
		if(data){
 			mWin.ok("删除成功");
 			setGameStep(main_params.gameID);	
 		}else{
 			jAlert("删除失败");
 		}
	});
}
function setStepDetail(id,name){
	switchtTable("gameStepDetailTableContainer");
	$(".searchList").css("visibility","visible");
	$(".navigate").removeClass("hidden");
	jQuery("#gameStepDetailTable").setGridParam( {
		searchCondition : "gs.gameStepID='"+id+"'",
		page:1
	}).trigger("reloadGrid");
	showTrace(id,name,0);
}
function returnToGame(){
	var table="gameTableContainer";
	$(".searchList").css("visibility","visible");
	$(".navigate").addClass("hidden");
	switchtTable(table);
}
function returnToGameStep(){
	$(".searchList").css("visibility","hidden");
	$(".navigate").removeClass("hidden");
	switchtTable("gameStepTableContainer");
}
function switchtTable(name){
	var tables=$("div[id*='TableContainer']");
	tables.each(function (){
		var that=$(this);
		that.attr("id")==name?that.removeClass("hidden"):that.addClass("hidden");
	});
	$(window).trigger('resize');
}
function showTrace(id, name,type) {
	var result = "",
		pathContainer=$("#currentPosition");
	result = "<span class='currentAddress' data-type='"+type+"' onclick=\"clickPath('" + id + "','"+type+"')\" style='cursor: pointer;'>" + name + "/</span>";
	pathContainer.append(result);
}
function clickPath(id,type){
	$("span[data-type='"+type+"'] ~ span").remove();
	$("span[data-type='"+type+"']").remove();
	switch(parseInt(type)){
	case 0:returnToGame();switchtTable("gameTableContainer");$("#currentPosition").html("当前位置：");break;
	case 1:returnToGameStep();switchtTable("gameStepDetailTableContainer");break;
	};
}
