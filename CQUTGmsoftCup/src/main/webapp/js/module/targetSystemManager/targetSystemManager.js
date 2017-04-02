;
var pageData = {
	targetSystemID : null,
	operatorID : operatorId
};
var currentIDAndName = {
	targetSysID : "",
	targetSysName : ""
};
// 常量
var constant = {
	targetSysTableID : "targetSystemTable",
	targetSysPageID : "targetSystemPager",
	targetSysDivID : "targetSystemDiv",
	targetSysVersionTable : "targetSysVersionTable",
	targetSysVersionPage : "targetSysVersionTablePager",
	targetSysVersionDivID : "targetSysVersion"
};

$(document).ready(function() {
	loadTargetSys();
	$("#add").removeAttr('onclick');
	$("#add").click(function() {
		// 版本
	if (currentIDAndName.targetSysID && currentIDAndName.targetSysName) {
		addTargetSysVersion();
	} else {// 体系
		addTargetSys();
	}
	});
	modifyToNiceScroll("body");
});

// 修改为新滚动条
function modifyToNiceScroll(selector) {
	$(selector).each(function(index, element) {
		$(element).niceScroll({
			horizrailenabled : false,
			scrollspeed : 20
		});
	});
}

//*****************************体系相关*****************
function loadTargetSys() {
	// 加载下拉列表
	initGrid(gridSystemSetting, constant.targetSysTableID, "",
			constant.targetSysVersionDivID, constant.targetSysVersionTable,
			constant.targetSysVersionPage);
}
// 体系配置表格
var gridSystemSetting = {
	height : 'auto',
	autowidth : true,
	datatype : "json",
	dwr : true,
	dwrFun : TargetSystemService.findMapByPropertiesWithLimit,
	dwrCountFun : TargetSystemService.findCountByProperties,
	condition : '',
	needLink : false,
	colNames : [ '版本ID', '版本名称','引用状态', '操作' ],
	colModel : [ {
		// 体系ID
		key : true,
		name : 'standardVersionID',
		index : 'standardVersionID',
		hidden : true
	}, {
		// 体系名称
		name : "standardVersionName",
		index : "standardVersionName",
		align : 'center'
	}, {
		name : "citeState",
		index : "citeState",
		align : 'center',
		hidden : true
	}, {
		// 操作
		name : 'operatorColumn',
		index : null,
		formatter : operatorFormatter,
		align : 'center'
	} ],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#targetSystemPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records",
		repeatitems : false
	},
	multiselect : true,
	multiboxonly : true,
	altRows : true,
	altclass : 'altclass'

};

// 新增指标体系
function addTargetSys() {
	DialogUtil.openFloatWindow(
		"module/targetSystemManager/targetSystemAdd.jsp", {}, {
		EVENT_OK : function(param) {
			dwr.engine.setAsync(false);
			TargetSystemService.saveStandard(param.standardVersionName,
				param.standardVersionB,pageData.operatorID, function(data) {
				if (data) {
					mWin.ok("新增成功");
					quickSearch();
				} else {
					jAlert("新增失败");
				}
			});
			dwr.engine.setAsync(true);
		}
	});
}

function editTargetSys(targetSystemID, name) {
	DialogUtil.openFloatWindow(
		"module/targetSystemManager/targetSystemEdit.jsp", {
		id : targetSystemID,
		name : name
	}, {
		EVENT_OK : function(param) {
			dwr.engine.setAsync(false);
			TargetSystemService.updateStandard(
					param.standardVersionName, param.standarVsersionId,
					function(data) {
						if (data) {
							mWin.ok("修改成功");
							quickSearch();
						} else {
							jAlert("修改失败");
						}
					});
			dwr.engine.setAsync(true);
		}
	});
}

function infoGame(targetSystemID, name) {
	DialogUtil.openModalWindow(basePath+ "module/targetSystemManager/targetGame.jsp?id="+targetSystemID, {
		id : targetSystemID,
		name : name
	}, {}, 1200, 700);
}
// 删除指标体系
function deleteTargetSys(targetSystemID) {
	jConfirm('您确定删除该指标体系吗？', function() {
		dwr.engine.setAsync(false);
		TargetSystemService.deleteById(targetSystemID, function(data) {
			if (data) {
				mWin.ok("删除成功");
				quickSearch();
			} else {
				jAlert("删除失败");
			}
		});
		dwr.engine.setAsync(true);
	});
}

// 快速查找
function quickSearch() {
	var condition = "createrId='"+pageData.operatorID+"'";
	pageData.standardVersionName = $("#targetSystem").val();
	if (pageData.standardVersionName != "") {
		condition += " and standardVersionName like '%" + pageData.standardVersionName + "%'";
	}
	refresh(condition, constant.targetSysTableID);
}

// *************************************版本相关******************
// 删除指标版本
function deleteFun(rowID) {
	jConfirm('您确定删除该指标版本吗？', function() {
		TargetSysVersionService.deleteTargetVersion(rowID, function(data) {
			if (data) {
				mWin.ok("删除成功");
				var condition = "me.standardVersionID='" + currentIDAndName.targetSysID + "'";
				refresh(condition, constant.targetSysVersionTable);
			} else {
				jAlert("删除失败");
			}
		});
	});
}

// ---------------------------公共方法---------------------

// 加载表格
function initGrid(gridSetting, tableID, condition, replaceDivID,
		replaceTableID, replacePageID) {
	if (condition) {
		gridSetting.searchCondition = condition;
	}

	if (replaceDivID && replaceTableID && replacePageID) {
		var html = "<table id='" + replaceTableID
				+ "' border='0' width='100%'></table>" + "<div id='"
				+ replacePageID + "'></div>";
		$("#" + replaceDivID).html(html);
	}
	$("#" + tableID).jqGrid(gridSetting);
	$(window).bind('resize', function() {
		$("#" + tableID).setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');
}

// 刷新表格
function refresh(condition, tableID) {
	if (condition) {
		jQuery("#" + tableID).setGridParam({
			searchCondition : condition,
		}).trigger("reloadGrid");
	} else {
		jQuery("#" + tableID).trigger("reloadGrid");
	}
}

// 操作
function operatorFormatter(data, d2, d3, d4) {
	var alink = "";
	if(d3.citeState == "0"){
		//alink += "<a style='cursor:pointer;' onclick=\"setTargetSys('" + d2.rowId + "');\">" + "<span class='operatorColumn sheetWord'>设置评审指标</span></a>&nbsp;&nbsp;";
		alink += "<a style='cursor:pointer;' onclick=\"editTargetSys('" + d2.rowId+ "','" + d3.standardVersionName+ "');\">" + "<span class='operatorColumn sheetWord'>编辑</span></a>&nbsp;&nbsp;"; 
		alink += "<a style='cursor:pointer;' onclick=\"deleteTargetSys('" + d2.rowId + "');\"><span class='operatorColumn sheetWord'>删除</span></a>&nbsp;&nbsp;";	
	}
	alink += "<a style='cursor:pointer;' onclick=\"showTarget('" + d2.rowId + "','" + d3.standardVersionName + "');\"><span class='operatorColumn sheetWord'>版本预览</span></a>&nbsp;&nbsp;";
	alink += "<a style='cursor:pointer;' onclick=\"infoGame('" + d2.rowId + "','" + d3.standardVersionName + "');\"><span class='operatorColumn sheetWord'>版本使用情况</span></a>&nbsp;&nbsp;";
	return alink;
}

function showTarget(id,standardVersionName) {
	DialogUtil.openModalWindow(basePath + "module/targetSystemManager/indexSystemQuery.jsp", {
		targetSysVersionID : id,
		standardVersionName : standardVersionName
	}, {}, 1200, 700);
}

function setTargetSys(id){
	
}
