var curacademicID="";
$(document).ready(function() {
	$("body").niceScroll({scrollspeed : 20,horizrailenabled: false});
	//加载学院信息
    initGame();
	//回车事件
	$(window).keyup(function(event){
		if(event.keyCode == 13){
			quickSearch();
		}
    });
	
	gridSetting.searchCondition="ga.gameID = '" + $.trim($('#champion').val()) + "' and gs.gameStepID='"+$.trim($('#rate').val())+"'";;
	jQuery("#roleTable").jqGrid(gridSetting);
	jQuery("#roleTable").jqGrid('navGrid', '#rolePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	
	$(window).bind('resize', function() {
		$("#roleTable").setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
	
});

function initGame(){
	var html="";
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#champion").append(html);
	
	initStep();
}

function initStep(){
	$("#rate").empty();
	var html="";
	dwr.engine.setAsync(false);
	GameStepService.findMapByPropertiesQuick(["gameStep","gameStepID","mo_codeTableName"],"gameID='"+$("#champion").val()+"'",true,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i]["gameStepID"]+"'>"+data[i]["mo_codeTableName"]+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#rate").append(html);
	quickSearch();
}

var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : reviewManagementService.findMapByPropertiesWithLimitNew,
	dwrCountFun : reviewManagementService.findCountByProperties,
	condition : '',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	colNames : [ 'ID','大赛名称','年度','竞赛类别','竞赛任务','标准ID','评审标准','评审开始时间','评审结束时间', '操作' ],
	colModel : [ {
		key : true,
		name : 'gameStepDetailID',
		index : 'gameStepDetailID',
		width : 30,
		hidden : true,
		sortable : false
	}, {
		name : 'ga_gameName',
		index : 'ga_gameName',
		width : 40,
		align:'center'
	}, {
		name : 'ga_year',
		index : 'ga_year',
		width : 30,
		align:'center'
	},{
		name : 'gs_gameStep',
		index : 'gs_gameStep',
		width : 30,
		align:'center'
	},{
		name : 'mo_codeTableName',
		index : 'mo_codeTableName',
		width : 30,
		align:'center'
	},{
		name : 'standardVersionID',
		index : 'standardVersionID',
		hidden:true
	},{
		name : 'standardVersionName',
		index : null,
		width : 30,
		align:'center',
		formatter:function(data){
			return data;
		}
	},{
		name : 'checkStartTime',
		index : 'checkStartTime',
		width : 30,
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	},{
		name : 'checkEndTime',
		index : 'checkEndTime',
		width : 30,
		align:'center',
		formatter:function(data){
			return DateUtil.dateDiffMills(data);
		}
	},{
		name : 'operatorColumn',
		index : null,
		align : 'center',
		formatter : operatorColumn_formater,
		title : false,
		width : 70,
		sortable : false
	} ],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#rolePager',
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
	ondblClickRow : function(rowid, iRow, iCol, e) {//双击表格的行
		//showFun(rowid);
		// 				jAlert('当前行的ID是='+rowid);
},
loadComplete : function() {
	$("#roleTable").setGridWidth($("#MainArea").width() - 2);
},
multiselect : true,
multiboxonly : true,
altRows:true,
altclass:'altclass'
};

function quickSearch() {
	var champion = $.trim($('#champion').val());
	var rate = $.trim($('#rate').val());
	var reviewTime = $.trim($('#review-time').val());
	var condition = '1=1';
	if(champion!==''){
	    	condition+=" and ga.gameID = '" + champion + "'";
	}
	if(rate!==''){
			condition+=" and gs.gameStepID = '" + rate + "'";
	}
	if(reviewTime!=="0"){
		 if(reviewTime==="1"){			 
			condition+=" and checkStartTime IS NOT NULL and checkEndTime IS NOT NULL";
		 }else{
			condition+=" and checkStartTime IS NULL and checkEndTime IS NULL";
		 }
	}
	jQuery("#roleTable").setGridParam( {
		searchCondition : condition,
		page : 1
	}).trigger("reloadGrid");
}

function setReviewTime(rowId,gameName,gameStep,rate,checkStartTime,checkEndTime,version) {
	DialogUtil.openFloatWindow("module/reviewManagement/reviewEdit.jsp", {
		id:rowId,gameName:gameName,gameStep:gameStep,rate:rate,checkStartTime:checkStartTime,checkEndTime:checkEndTime,version:version
	}, {
		EVENT_OK : function(param) {
			if (param) {
				    reviewManagementService.updateEntity(param," gameStepDetailID='"+rowId+"'",function(){
					    jQuery("#roleTable").trigger("reloadGrid");
						mWin.ok('设置评审时间成功'); 
					});
			}
		}
	});
}

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4) {
	var nowDate=new Date();
	var alink='';
	if(parseInt(d3.checkStartTime)>nowDate.getTime()||d3.checkStartTime==""||d3.checkStartTime==null){		
	 alink= "<a href='javascript:void(0);' onclick=\"setReviewTime('"
			+ d2.rowId+"','"+d3.ga_gameName+"','"+d3.gs_gameStep+"','"+d3.mo_codeTableName+"','"+d3.checkStartTime+"','"+d3.checkEndTime+"','"+d3.standardVersionID+"')\"><span class='sheetWord'>设置评审时间</span></a>"
			+ "&nbsp;&nbsp;";
	}
	return alink;
}