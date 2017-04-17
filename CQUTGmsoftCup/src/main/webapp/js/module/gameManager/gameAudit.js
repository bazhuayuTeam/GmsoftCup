var main_params={
		stepID:false,
		gameID:false,
		trace:[]
};
$(document).ready(function() {
	var gameAuditTable = $("#gameAuditTable");
	gameAuditTable.jqGrid(gridSetting);
	gameAuditTable.jqGrid('navGrid', '#gameAuditPager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	$(window).bind('resize', function() {
		gameAuditTable.setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	initGame();
});


//jqGrid的数据对象------------------------------------------------
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : GameService.findAuditByPropertiesWithLimit,
	dwrCountFun : GameService.findCountByProperties,
	condition : "me.state=1",
	searchCondition : "",
	selectCondition : '',
	needLink : true,
	dwr : true,
	checkable:true,
	autowidth : true,
	loadComplete:function(){
		$(window).trigger('resize');
	},
	colNames : [ 'ID','竞赛名称', '申请人', '申请时间','备注','状态','操作' ],
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
		name : 'user_name',
		index : 'user_name',
		align:'center',
	}, {
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
		name : 'me_checkRemark',
		index : 'me_checkRemark',
		align:'center',
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
	} ],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#gameAuditPager',
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
	jQuery("#gameAuditTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}


//表格中的操作这一列的显示样式
function codetableColumn_formater(data, d2, d3, d4) {
	var alink =   "<a href='javascript:void(0)' onclick=\"agree('" + d2.rowId + "');\"><span class='sheetWord'>通过</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<a href='javascript:void(0)' onclick=\"agreeNot('"+ d2.rowId + "');\"><span class='sheetWord'>退回</span></a>&nbsp;&nbsp;&nbsp;&nbsp;"	
		+ "<a href='javascript:void(0)' onclick=\"exit('"+ d2.rowId+ "',1);\"><span class='sheetWord'>查看详情</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
	return alink;
}

//通过
function agree(id){
	var game={
		gameId:id,
		state:2
	}
	GameService.updateEntity(game,"gameId='"+id+"'",function(data){
		if(data){
			mWin.ok("通过成功");
			quickSearch();
		}
		else{
			jAlert("通过失败");
		}
	});
}

function agreeNot(id){
	var game={
		gameId:id,
		state:3
	};
	GameService.updateEntity(game,"gameId='"+id+"'",function(data){
		if(data){
			mWin.ok("退回成功");
			quickSearch();
		}
		else{
			jAlert("退回失败");
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

function agreeMulti(){
	var selrow = getSelectedRowIDs();
	if(null == selrow || selrow.length == 0){
		jAlert('您请选择要通过的数据！');
		return;
	}
	jConfirm('确认要通过吗？',function() {
		GameService.updateEntities(selrow,function(data){
			if(data){
				mWin.ok("通过成功");
				quickSearch();
			}
			else{
				jAlert("通过失败");
			}
		});
	});
}

function agreeNotMulti(){
	var selrow = getSelectedRowIDs();
	if(null == selrow || selrow.length == 0){
		jAlert('您请选择要退回的数据！');
		return;
	}
	jConfirm('确认要退回吗？',function() {
		GameService.updateNotEntities(selrow,function(data){
			if(data){
				mWin.ok("退回成功");
				quickSearch();
			}
			else{
				jAlert("退回失败");
			}
		});
	});
}

//search
function quickSearch() {
	var game = $("#game").val(),
	currentSearchCondition="";   
	 if (game!= "") {
		 currentSearchCondition = "me.gameId ='" +game + "'";
	 }  
	jQuery("#gameAuditTable").setGridParam( {
		searchCondition : currentSearchCondition,
		page:1
	}).trigger("reloadGrid");
}

function initGame(){
	var html="";
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime desc",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>"
		}
		$("#game").append(html);
	})
}

function getSelectedRowIDs() {
	return jQuery('#gameAuditTable').getGridParam('selarrrow');//多选
}