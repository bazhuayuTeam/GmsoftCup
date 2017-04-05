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
		name : 'gameId',
		index : 'gameId',
		hidden : true
	}, {
		name:'gameName',
		index:'gameName',
		align:'center'
	},{
		name : 'signUpStartTime',
		index : 'signUpStartTime',
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	}, {
		//名称
		name : 'sigeUpEndTime',
		index : 'sigeUpEndTime',
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	},{
		//参赛形式
		name : 'competitionType',
		index : 'competitionType',
		align:'center',
		width:80,
		formatter:function(data){
			var type=data==0?"团队 ":"个人";
			return type;
		}
	},{
		name : 'isMultiStage',
		index : 'isMultiStage',
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
		name : 'state',
		index : 'state',
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
		width:350
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
	var state=d3.state;
	var alink = "";
	if(state==0){  //停用
		alink =   "<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>申请发布</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a href='javascript:void(0)' onclick=\"setGameStep('" + d2.rowId + "');\"><span class='sheetWord'>设置竞赛流程</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId + "');\"><span class='sheetWord'>编辑</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"	
		+ "<a href='javascript:void(0)' onclick=\"deleteFun('"+ d2.rowId+ "');\"><span class='sheetWord'>删除</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	}		
	else if(state==1){
		alink =   "<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>申请取消发布</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>查看竞赛流程</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId+ "','"+d3.gameName+"','"+d3.year+"','"+d3.startTime+"','"+d3.endTime+"');\"><span class='sheetWord'>详情</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";	
	}
	return alink;
}

function deleteFun(id){
	jConfirm('确认要删除吗?',function() {
		dwr.engine.setAsync(false);
		GameService.deleteData(id,function(data){
			if(data){
				mWin.ok("删除成功");
				quickSearch();
			}
			else{
				jAlert("删除失败");
			}
		});
		dwr.engine.setAsync(true);
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
			mWin.ok("启用成功");
			quickSearch();
		}
		else{
			jAlert("启用失败");
		}
	});
	dwr.engine.setAsync(true);
}

function agreeNot(id){
	var game={
		gameId:id,
		state:0
	}
	GameService.updateEntity(game,"gameId='"+id+"'",function(data){
		if(data){
			mWin.ok("停用成功");
			quickSearch();
		}
		else{
			jAlert("停用失败");
		}
	});
}

//编辑
function exit(id){
	DialogUtil.openFloatWindow("module/gameManager/gameEdit.jsp",id,{EVENT_OK:function(param){
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
   		 	})
   	 }});
}

function setGameStep(id){
	jQuery("#gameTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}
