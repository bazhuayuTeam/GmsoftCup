var curacademicID="";
$(document).ready(function() {
	$("body").niceScroll({scrollspeed : 20,horizrailenabled: false});
	//加载学院信息
    load();
	//回车事件
	$("#disciplineName").keyup(function(event){
		if(event.keyCode == 13){
			quickSearch();
		}
    });
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
//加载学院信息
function load(){
	dwr.engine.setAsync(false);
	DropDownBox.loadAcademic("#Academic");
	dwr.engine.setAsync(true);
	$('#disciplineName').Watermark("请输入专业名称", "#8f8f8f");
    $('#Academic').change(function(){
        curacademicID = $(this).children('option:selected').val();  //弹出select的值
        quickSearch();
    });
}
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : DisciplineService.findMapByPropertiesWithLimit,
	dwrCountFun : DisciplineService.findCountByProperties,
	condition : '',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	colNames : [ '','部门名称','专业名称', '操作' ],
	colModel : [ {
		key : true,
		name : 'disciplineID',
		index : 'disciplineID',
		width : 90,
		hidden : true,
		sortable : false
	}, {
		name : 'aca_academicName',
		index : 'aca_academicName',
		width : 120,
		align:'center'
	}, {
		name : 'disciplineName',
		index : 'disciplineName',
		width : 100,
		align:'center'
	}, {
		name : 'operatorColumn',
		index : null,
		align : 'center',
		formatter : operatorColumn_formater,
		title : false,
		width : 100,
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
	var name = $.trim($('#disciplineName').val());
	var condition = '1=1';
	if(curacademicID==""){
	    if (name != '' && name != '请输入专业名称') {
	    	condition=" disciplineName like '%" + name + "%'"
		}
	}else{
		if (name != '' && name != '请输入专业名称') {
			condition = "aca.academicID='"+curacademicID+"' and disciplineName like '%" + name + "%'";
		}else{
			condition = "aca.academicID='"+curacademicID+"'";
		}
	}
	jQuery("#roleTable").setGridParam( {
		searchCondition : condition,
		page : 1
	}).trigger("reloadGrid");
}


function addFun() {
    if(curacademicID!=""){
    	var curacademicName=$("#Academic").find("option:selected").text(); 
    	dwr.engine.setAsync(false);
		DialogUtil.openFloatWindow("module/discipline/disciplineAdd.jsp", {academicName:curacademicName}, {
			EVENT_OK : function(param) {
				if (param) {
				    param.academicID=curacademicID;
				    //printObject(param);
					DisciplineService.saveEntity(param, function(data) {
						   	mWin.ok("新增成功");
						    jQuery("#roleTable").trigger("reloadGrid");
					});
				}
			}
		});
		dwr.engine.setAsync(false);
    }else{
    	jAlert("请选择学院");
    }
}
function editFun(rowId,disciplineName,academicName) {
	DialogUtil.openFloatWindow("module/discipline/disciplineEdit.jsp", {
		academicName:academicName,disciplineName:disciplineName
	}, {
		EVENT_OK : function(param) {
			if (param) {
				    DisciplineService.updateEntity(param," disciplineId='"+rowId+"'",function(){
				    jQuery("#roleTable").trigger("reloadGrid");
					mWin.ok('编辑成功'); 
				});
				
			}
		}
	});
}
function deleteFun(rowId) {
	jConfirm('您确定要删除所选专业吗？',function() {
		DisciplineService.deleteById(rowId, function(data) {
			if (data) {
			     mWin.ok('删除成功');
				 jQuery("#roleTable").trigger("reloadGrid");
			} else {
				jAlert('删除失败!');
			}
		});
	});
}
function deleteSelectedsFun() {
	var selrow = getSelectedRowIDs();
	if(null == selrow || selrow.length == 0){
		jAlert('请选择要删除的数据！');
		return;
	}
	jConfirm('您确定要删除所选专业吗？', function() {
		DisciplineService.deleteByIds(selrow,function(data){
			if(data){
			      jQuery("#roleTable").trigger("reloadGrid");
			      mWin.ok('删除成功');
			}else{
				jAlert("删除失败!");				
			}
			jQuery("#roleTable").trigger("reloadGrid");
		});
	});
}
function operatorname_formater(data, d2, d3, d4) {
	var showext = "鼠标点击的是" + data + ";这一行的ID是" + d2.rowId;
	return "<a href=\"#\" onclick=\"jAlert('" + showext + "')\">" + data
			+ "</a>";
}
function getSelectedRowIDs() {
	return jQuery('#roleTable').getGridParam('selarrrow');//多选
}
function getSelectedRowID() {
	return jQuery('#roleTable').getGridParam('selrow');//单选
}

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4) {
	var alink = "<a href='javascript:void(0);' onclick=\"editFun('"
			+ d2.rowId+"','"+d3.disciplineName+"','"+d3.aca_academicName+"'"
			+ ")\"><span class='sheetWord'>编辑</span></a>"
			+ "&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"deleteFun('"
			+ d2.rowId
			+ "')\"><span class='sheetWord'>删除</span></a>";
	return alink;

}