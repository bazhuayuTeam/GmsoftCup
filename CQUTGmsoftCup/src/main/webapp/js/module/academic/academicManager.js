;
$(document).ready(function() {
	//回车事件
	$("#operatorName").keyup(function(event){//键盘起来时执行的函数
		if(event.keyCode == 13){//enter的ASCLL 码值为13，此处用于判断按下的是否是enter键
			quickSearch();//调用快速查找的函数
		}
    });
	
	$('#operatorName').Watermark("请输入部门名称", "#8b8b8b");//水印
	;
	jQuery("#roleTable").jqGrid(gridSetting);//gridSetting为json数组
	jQuery("#roleTable").jqGrid('navGrid', '#rolePager', {
		edit : false,
		add : false,
		del : false,
		search : false
	});
	
	$(window).bind('resize', function() {//bind() 方法为被选元素添加一个或多个事件处理程序，并规定事件发生时运行的函数
		$("#roleTable").setGridWidth($("#MainArea").width() - 2);
	}).trigger('resize');

});
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : AcademicService.findMapByPropertiesWithLimit,
	dwrCountFun : AcademicService.findCountByProperties,
	condition : '',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	sortname:"ACADEMICCODE",
	sortorder: "asc",
	colNames : [ '','部门代号','部门名称','部门简称', '部门类型','', '操作' ],
	colModel : [ {
		key : true,
		name : 'academicID',
		index : 'academicID',
		width : 90,
		hidden : true,
		sortable : false
	}, {
		name : 'academicCode',
		index : 'academicCode',
		align : 'center',
		width : 50,
		sortable : false
	}, {
		name : 'academicName',
		index : 'academicName',
		width : 100,
		sortable : false
	}, {
		name : 'academicShort',
		index : 'academicShort',
		width : 60,
		sortable : false
	}, {
		name : 'code_codeTableName',
		index : 'code_codeTableName',
		width : 100,
		align : 'center',
		sortable : false
	}, {
		name : 'academicType',
		index : 'academicType',
		width : 150,
		align : 'center',
		hidden : true,
		sortable : false
	}, {
		name : 'sheetWord',
		index : null,
		align : 'center',
		formatter : operatorColumn_formater,
		title : false,
		width : 50,
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
// 			,caption:'你好'
};



function quickSearch() {
	var name = $.trim($('#operatorName').val());//$.trim()是jQuery提供的函数，用于去掉字符串首尾的空白字符
	var condition = '1=1';
	if (name != '' && name != '请输入部门名称') {
		condition = "AcademicName like '%" + name + "%'";//用于数据库查找数据
	}
	jQuery("#roleTable").setGridParam( {
		searchCondition : condition,
		page : 1
	}).trigger("reloadGrid");//重新载入
}


function addFun() {
	dwr.engine.setAsync(false);//dwr自身的设置异步同步的函数
	DialogUtil.openFloatWindow("module/academic/academicAdd.jsp", {}, {
		EVENT_OK : function(param) {
			if (param) {
				AcademicService.saveEntity(param, function(data) {
					   	mWin.ok("新增成功");
					    jQuery("#roleTable").trigger("reloadGrid");
				});
			}
		}
	});
	dwr.engine.setAsync(false);
}
function editFun(rowId,academicCode,academicName,academicShort,academicType) {
	DialogUtil.openFloatWindow("module/academic/academicEdit.jsp", {//打开浮动框
		academicId : rowId,academicCode:academicCode,academicName:academicName,academicShort:academicShort,academicType:academicType,
	}, {
		EVENT_OK : function(param) {
			if (param) {
				AcademicService.updateEntity(param," academicId='"+rowId+"'",function(){
				    jQuery("#roleTable").trigger("reloadGrid");
					mWin.ok('编辑成功'); 
				});
				
			}
		}
	});
}
function deleteFun(rowId) {
	jConfirm('确认要删除吗?',function() {
		AcademicService.deleteById(rowId, function(data) {
			if (data) {
			     mWin.ok('删除成功');
				jQuery("#roleTable").trigger("reloadGrid");//方法触发被选元素的指定事件类型
			} else {
				jAlert('删除失败!');
			}
		});
	});
}
function deleteSelectedsFun() {
	var selrow = getSelectedRowIDs();
	if(null == selrow || selrow.length == 0){
		jAlert('您请选择要删除的数据！');
		return;
	}
	jConfirm('确认要删除吗？',function() {
		AcademicService.deleteByIds(selrow,function(data){
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
function updateRole(dto, id) {
	var name = jQuery('#roleTable').jqGrid('getRowData', id)['name'];
	if (name != dto.roleNam)
		RoleService.updateRoleName(id, dto.roleName, function(data) {
			if (data) {
				jQuery("#roleTable").trigger("reloadGrid");
			} else {
				jAlert('编辑失败！');
			}
		});
}

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4) {
	var alink = "<a href='javascript:void(0);' onclick=\"editFun('"
			+ d2.rowId+"','"+d3.academicCode+"','"+d3.academicName+"','"+d3.academicShort+"','"+d3.academicType+"'"
			+ ")\"><span class='sheetWord'>编辑</span></a>"
			+ "&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"deleteFun('"
			+ d2.rowId
			+ "')\"><span class='sheetWord'>删除</span></a>";
	return alink;
}