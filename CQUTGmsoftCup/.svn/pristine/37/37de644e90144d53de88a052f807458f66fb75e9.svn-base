var gridSetting = {
	url : 'http://127.0.0.1:8080/WebConnector/data.jsp',
	height : 'auto',
	datatype : "json",
	dwrFun : RoleService.findMapByPropertiesWithLimit,
	dwrCountFun : RoleService.findCountByProperties,
	condition : '',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	colNames : [ 'ID', '角色名称', '操作' ],
	colModel : [ {
		key : true,
		name : 'roleID',
		index : 'roleID',
		width : 90,
		hidden : true,
		sortable : false
	}, {
		name : 'roleName',
		index : 'roleName',
		width : 150,
		sortable : false
	}, {
		name : 'operatorColumn',
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
	altRows:true,
	altclass:'altclass',
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false
	// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	},
	ondblClickRow : function(rowid, iRow, iCol, e) {//双击表格的行
	},
	loadComplete : function() {
		$("#roleTable").setGridWidth($("#MainArea").width() - 2);
		$(".content").unmask();
	},
	multiselect : true,
	multiboxonly : true
};

//页面加载执行----------------------------------------------------------------------
$(document).ready(function() {
	$(".content").mask("正在加载中,请耐心等候......");
	//回车事件
	$("#operatorName").keyup(function(event){
		if(event.keyCode == 13){
			quickSearch();
		}
    });
	
	$('#operatorName').Watermark("请输入角色名称", "#8b8b8b");
	;
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

function quickSearch() {
	$(".content").mask("正在搜索中,请耐心等候......");
	var name = $.trim($('#operatorName').val());
	var condition = '1=1';
	if (name != '' && name != '请输入角色名称') {
		condition = "roleName like '%" + name + "%'";
	}
	jQuery("#roleTable").setGridParam( {
		searchCondition : condition,
		page : 1
	}).trigger("reloadGrid");
	$(".content").unmask();
}


function addFun() {
	dwr.engine.setAsync(false);
	DialogUtil.openFloatWindow("module/roleManager/roleAdd.jsp", {}, {
		EVENT_OK : function(param) {
			if (param) {
				RoleService.addRole(param.roleName, function(data) {
					if (data =="0") {
					   	mWin.ok("新增成功");
					} else if (data == -1) {
						jAlert('新增失败!');
					} else {
						jAlert('新增失败 ,该角色已存在!');
					}
					
					jQuery("#roleTable").trigger("reloadGrid");
				});
			}
		}
	});
	dwr.engine.setAsync(false);
}
function editFun(rowId) {
	DialogUtil.openFloatWindow("module/roleManager/roleAdd.jsp", {
		mode : 3,
		roleId : rowId
	}, {
		EVENT_OK : function(param) {
			if (param) {
				dwr.engine.setAsync(false);
				updateRole(param, rowId);
				dwr.engine.setAsync(true);
				mWin.ok('编辑成功');
			}
			else{
				jAlert("编辑失败!");
			}
		}
	});
}
function deleteFun(rowId) {
	if (confirm('您确认要删除该角色吗?')) {
		RoleService.deleteById(rowId, function(data) {
			if (data) {
			     mWin.ok('删除成功');
				jQuery("#roleTable").trigger("reloadGrid");
			} else {
				jAlert('删除失败!');
			}
		});
	}
}
function deleteSelectedsFun() {
	var selrow = getSelectedRowIDs();
	if(null == selrow || selrow.length == 0){
		jAlert('请您选择具体的数据!');
		return;
	}
	if (confirm('您确认要删除这些角色吗?')) {
		RoleService.deleteByIds(selrow,function(data){
			if(data){
			 mWin.ok('删除成功');
			}else{
				jAlert("删除失败!");				
			}
			jQuery("#roleTable").trigger("reloadGrid");
		});
	}
}
function operatorname_formater(data, d2, d3, d4) {
	var showext = "鼠标点击的是" + data + ";这一行的ID是" + d2.rowId;
	return "<a href=\"#\" onclick=\"alert('" + showext + "')\">" + data
			+ "</a>";
}
function getSelectedRowIDs() {
	return jQuery('#roleTable').getGridParam('selarrrow');//多选
}
function getSelectedRowID() {
	return jQuery('#roleTable').getGridParam('selrow');//单选
}
function updateRole(dto, id) {
	var name = jQuery('#roleTable').jqGrid('getRowData', id)['roleName'];
	if (name != dto.roleName)
		RoleService.updateRoleName(id, dto.roleName, function(data) {
			if (data) {
				jQuery("#roleTable").trigger("reloadGrid");
			} else {
				jAlert('编辑失败!');
			}
		});
}

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4) {
	var alink = "<a href='javascript:void(0);' onclick=\"editFun('"
			+ d2.rowId
			+ "')\"><span class='sheetWord' style='margin-right:30px;'>编辑</span></a>"
			+ "&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"deleteFun('"
			+ d2.rowId
			+ "')\"><span class='sheetWord'>删除</span></a>";
	return alink;

}
