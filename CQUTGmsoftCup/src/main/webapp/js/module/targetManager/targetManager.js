;
var pageData={
	     level0:1,//记录当前所属层次
	     targetCode:null,//记录当前code层级
	     curParentCode: null, // 当前路径
};

var totalScores = null;

$(document).ready(function(){
	dwr.engine.setAsync(false);
	//加载指标版本
    loadSelect();
    dwr.engine.setAsync(true);
    
	totalScores = getLowerTargetTotalScore();
    // 初始路径
    initPosition();
    //初始化表格
    initGrid();
    $("#targetName").watermark("请输入指标名","#8f8f8f");
    //回车事件绑定
    $('#targetName').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            $('#searchButton').click();
        }
    });
    
    $("body").niceScroll({horizrailenabled : false,scrollspeed : 20});
});
//配置表格
var gridSetting={
	height : 'auto',
	autowidth : true,
	//shrinkToFit:false,   
	//autoScroll: true,
	datatype : "json",
	dwr : true,
	dwrFun : TargetService.findMapByPropertiesWithLimitNew,
	dwrCountFun :TargetService.findCountByPropertiesNew,
	condition : '',
	searchCondition:'',
	selectCondition : '',
	needLink : true,
	sortname:"me.showOrder",
	order:'asc',
	sortable:true,
	colNames : [ 'targetCode','指标名','是否有下级指标','指标分值','指标说明','操作','父级指标代码','状态'],//'对象材料关系ID',
	colModel : [{
		key:true,
		name:'me_targetCode',
		index:'me_targetCode',
		hidden:true
	},{
		name:"me_targetName",
		index:"me_targetName",
		width:30,
		align:"center",
	},{ 
		name:"me_isLastTarget",
		index:"me_isLastTarget",
		formatter:function(val){
			var type="";
			if(val != null){
				switch(val){
						case 0:type="是";break;
						case 1:type="否";break;
				}
			}
			return type;
		},
		width:30,
		align:"center",
	},/*{在后台手动处理，获取所有跟该数据相关的材料支撑关系，然后再传字符串		   name:"objRelated_relatedDocumentID",
	   index:null,
		hidden:true
	},*/{
		name:"me_targetScore",
		index:"me_targetScore",
		formatter:addWarning,
		width:20,
		align:"center",
	},{
		name:"me_targetExplain",
		index:"me_targetExplain",
		width:50,
		align:"center",
		
	},{             	  
		//操作
		name : 'operatorColumn',
		index : null,
		formatter : operatorFormatter,
		align : 'center',
		width:40,
	},{
		name:'me_targetParentCode',
		index:'me_targetParentCode',
		hidden:true
	},{
		name:'standard_citeState',
		index:'standard_citeState',
		hidden:true
	}],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#targetPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records",
		repeatitems : false
	},
	multiselect : false,
	multiboxonly : true,
	altRows:true,
	altclass:'altclass',
	
};

// 为当前指标的分值与其下级指标的总分值不匹配时添加警告
function addWarning(data, d2, d3, d4){
	if(!d3.me_targetType && !isCurScoreEqualLowerScore(data,d2.rowId))
		return data + "<img src='images/module/targetManager/warning.png' alt='!' class='warning'/><p style='display:none;'>&nbsp;&nbsp;注意：下级指标的总分值与当前分值不匹配</p>";
	
	return d3.me_targetType ? "" : data;
}

// 判断当前指标的分值与其下级指标的总分值是否匹配
function isCurScoreEqualLowerScore(curScore,targetCode){
	for(var i =0;i<totalScores.length;i++)
		if(totalScores[i].targetParentCode == targetCode && totalScores[i].totalScore != curScore)
			return false;
	
	return true;
}

//操作
function operatorFormatter(data, d2, d3, d4){	// data是undefined，我真的，不要用这个数据，然而火狐并不会报错，这就直接导致了表格不显示数据！！！
	var alink="";
	var state=d3.standard_citeState;
	if(pageData.level0<4){
		if(state=="0"){
	alink+= 
		       "<a style='cursor:pointer;' onclick=\"editFun('"+d2.rowId+"');\"><span class='operatorColumn sheetWord'>编辑</span></a>&nbsp;&nbsp;"
			   +"<a style='cursor:pointer;' onclick=\"deleteFun('"+d2.rowId+"');\"><span class='operatorColumn sheetWord'>删除</span></a>&nbsp;&nbsp;";
	}
	  alink+="<a style='cursor:pointer;' onclick=\"enterSubLevel('"+d2.rowId+"','"+d3.me_targetName+"','" 
	  			+ d3.me_targetScore + "');\"><span class='operatorColumn sheetWord'>进入下级</span></a>&nbsp;&nbsp;";
	  }
	else if(state=="0"){
		alink+= 
		       "<a style='cursor:pointer;' onclick=\"editFun('"+d2.rowId+"');\"><span class='operatorColumn sheetWord'>编辑</span></a>&nbsp;&nbsp;"
			   +"<a style='cursor:pointer;' onclick=\"deleteFun('"+d2.rowId+"');\"><span class='operatorColumn sheetWord'>删除</span></a>&nbsp;&nbsp;";
	
	}
  return alink;
}

//初始化表格
function initGrid(){
//	var condition=" 1=1 AND me.targetParentCode " + (curPos== null)?"is null":"= '" +curPos+"'";
//	var targetSystemID=$("#targetSystem").val();
//	var targetSysVersionID=$("#targetSysVersion").val();
//	if(targetSystemID!=null){
//		condition+=" and me.targetSystemID='"+targetSystemID +"'";
//	}	
//	if(targetSysVersionID!=null){
//		condition+=" and me.targetSysVersionID='"+targetSysVersionID+"'";
//	}	
	var condition = getBaseContion();
	gridSetting.condition=condition;

	$("#targetTable").jqGrid(gridSetting);
	$(window).bind('resize', function() { 
	    $("#targetTable").setGridWidth($("#targetPager").width()-2); 
	}).trigger('resize');
	//$("#targetTable").closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'scroll' });
}  


//加载指标版本
function loadTarget(){
	//DropDownBox.loadTargetSystem("#targetSysVersion", true);
	StandardVersionService.findMapByProperties(["standardVersionID","standardVersionName"],"citeState='0'",'standardVersionID',"asc",function(data){
		if(data){
			var option = "";
			for(var i=0;i<data.length;i++){
				option += "<option value=\""+data[i].standardVersionID+"\">"+data[i].standardVersionName+"</option>";
			}
			$("#targetSysVersion").empty();
			$("#targetSysVersion").html(optionsStr);
		}
	});
}

function loadSelect(){
	var html="";
	dwr.engine.setAsync(false);
	StandardVersionService.findMapByPropertiesQuick(['standardVersionID',"standardVersionName"],
		"1=1",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#targetSysVersion").append(html);
}


//新增指标
function addTarget(){
	if(pageData.level0<=4){
	DialogUtil.openFloatWindow("module/targetManager/targetAdd.jsp",{},
		                                               {EVENT_OK:function(param){	
																sendData(param,"新增");
		                                                  }
                                                   	});
	}
	else{
		jAlert("无法再新增指标");
	}
}

// 获得基本条件
function getBaseContion(){
	var condition=" 1=1";
	if(pageData.curParentCode== null){
		condition+=" AND (me.targetParentCode  is null or me.targetParentCode='')";
	}
	else{
		condition+=" AND me.targetParentCode='"+pageData.curParentCode+"'";
	}
	var targetSysVersionID=$("#targetSysVersion").val();
		
	if(targetSysVersionID!=null){
		condition+=" and me.standardVersionID='"+targetSysVersionID+"'";
	}	
	return condition;
}

//搜索
function quickSearch(){
	var condition = getBaseContion();
	var targetName = $("#targetName").val();
	if(targetName!=null &&targetName!="请输入指标名"){
		condition+=" and me.targetName LIKE '%"+targetName+"%'";
	}
	$("#targetName").val("");
	$("#targetName").watermark("请输入指标名","#8f8f8f");
	refresh(condition);
}

// 获取非末级指标下级指标的总分和
function getLowerTargetTotalScore(){
	var rs = null;
	var condition = "";
	var targetSysVersionID=$("#targetSysVersion").val();
	if(targetSysVersionID!=null){
		condition+=" AND standardVersionID='"+ targetSysVersionID + "'";
	}
	
	dwr.engine.setAsync(false);
	TargetService.findLowerTargetsTotalScores(condition,
		function(data){
			if(!data)
				jAlert("获取失败");
			else
				rs = data;
	});
	dwr.engine.setAsync(true);
	
	return rs;
}

//刷新表格
function refresh(condition){
	totalScores = getLowerTargetTotalScore();
	if(condition){
		jQuery("#targetTable").setGridParam({
			searchCondition:condition,
			condition:'',
		}).trigger("reloadGrid");
	}else{
		jQuery("#targetTable").trigger("reloadGrid");
	}
}

// 从总分数组里获取当前targetParentCode的分数
function getCurParentScore(targetParentCode){
	for(var i =0;i<totalScores.length;i++)
		if(totalScores[i].targetParentCode == targetParentCode)
			return ""+ totalScores[i].totalScore + "分)";
	
	return "";
}

// 默认路径
function initPosition(){
	pageData.curParentCode =null;
	pageData.level0 = 1;
	setCurrentPosition(findCurrentPosition("<img src='images/navigate/location.png'/>当前位置：",null,"指标"));
}

// 设置当前路径
function findCurrentPosition(curPos,targetParentCode,targetName,targetScore){
	var showScore = "";
	if(targetScore && !isCurScoreEqualLowerScore(targetScore,targetParentCode))
		showScore = "(总分" + targetScore + "分,当前分" + getCurParentScore(targetParentCode);
	var aLink = "";
	if(targetParentCode)
		aLink +="->";
	aLink +="<a href='javascript:void(0)' onclick='goBackToPosition(\""+targetParentCode+"\"," +
		"\""+targetName+"\");'>"+targetName + showScore +"</a>";
	pageData.curParentCode= targetParentCode;
	return curPos + aLink;
}

//function refreshPosition(){}
// 回到某一路径
function goBackToPosition(targetParentCode,targetName){
	if(targetParentCode != "null")
		pageData.curParentCode = targetParentCode;
	else
		pageData.curParentCode = null;
	var condition = getBaseContion();
	refresh(condition);
	
	if(targetName == "指标"){
		initPosition();
		return;
	}
	setCurrentPosition(delePosition($("#currentPosition").html(),targetName));
}

function setCurrentPosition(curPos){
	$("#currentPosition").html(curPos);
}

// 删除targetName后的路径包括targetName的路径
function delePosition(curPos,targetName){
	var positions = curPos.split("-&gt;");
	var update = positions[0];
	
	pageData.level0 =1;
	for(var i=1;i<positions.length;i++){
		update += "->" + positions[i];
		pageData.level0 ++;

		if(positions[i].indexOf(targetName) >= 0)
			break;
		
	}
	
	return update;
}

// 编辑
function editFun(id){
	DialogUtil.openFloatWindow("module/targetManager/targetAdd.jsp",{targetCode:id,isModify:"true"},
   {EVENT_OK:function(param){												
 			sendData(param,"修改");
          }
   	});
}

// 将新增和编辑后的数据发送到后台
function sendData(param,opName){
	var targetSysVersionID=$("#targetSysVersion").val();
	if(param!=null){													 
 		param.targetLevel=pageData.level0;
        param.standardVersionID=targetSysVersionID;
        param.targetParentCode=pageData.curParentCode;
	  	TargetService.saveTarget(param,
		 	function(data) {
  				if(data){
  					mWin.ok(opName + "成功!");
  					refresh(getBaseContion());
  				}else{
  					jAlert(opName + "失败");
  				}
	         });
	 };                   
}

// 进入下一级
function enterSubLevel(parentCode,targetName,targetScore){
	pageData.curParentCode = parentCode;
	pageData.level0 ++;
	setCurrentPosition(findCurrentPosition($("#currentPosition").html(),parentCode,targetName,targetScore));
	refresh(getBaseContion());
}

// 删除指标及下级指标
function deleteFun(targetCode){
	jConfirm('您确定要删除吗？这将删除该指标及所有下级指标',function() {
		TargetService.deleteTargetAndLowerTargets(targetCode,
		function(data){
			if(data){
				mWin.ok("删除成功");
				refresh(getBaseContion());
			}else
				jAlert("删除失败");
		});
	});
}

// 预览指标
function showTarget(){
	DialogUtil.openModalWindow(basePath + "/module/indexSystemQuery/indexSystemQuery.jsp",{targetSysVersionID:$("#targetSysVersion").val()},	                                               {   },1200,700);		    
}