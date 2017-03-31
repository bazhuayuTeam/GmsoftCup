;

var expertTable = $("#expertTable");

//页面加载执行----------------------------------------------------------------------
$(document).ready(function() {
	$("#showMainPage").mask("正在加载中,请耐心等候......");
	$("#nameSearch").Watermark("请输入姓名", "#8b8b8b");

	jQuery("#expertTable").jqGrid(gridSetting);
	jQuery("#expertTable").jqGrid('navGrid', '#operatorPager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false
	});
	$(window).bind('resize', function() {
		$("#expertTable").setGridWidth($("#MainArea").width() - 6);
	}).trigger('resize');

	modifyToNiceScroll("body");
});

var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : ExpertService.findMapByPropertiesWithLimit,
	dwrCountFun : ExpertService.findCountByProperties,
	condition : "",
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	colNames :['expertID','姓名','education','学历','所属单位','专业领域','电话','邮箱','birth','operatorID','操作'],
			colModel :[{
						name : 'expertID',
						index : 'expertID',
						hidden : true,
						sortable : false,
						width : 30
					},{
						name : 'name',
						index : 'name',
						sortable : false,
						align: 'center',
						width : 40
					},{
						name : 'education',
						index : 'education',
						sortable : false,
						align: 'center',
						hidden:true,
						width : 20
					},{
						name : 'ct_codeTableName',
						index : 'ct_codeTableName',
						sortable : false,
						align: 'center',
						width : 20
					},{
						name : 'department',
						index : 'department',
						sortable : false,
						width : 60
					},{
						name : 'major',
						index : 'major',
						sortable : false,
						width : 40
					},{
						name : 'telephone',
						index : 'telephone',
						hidden:true,
						align: 'center',
						sortable : false,
						width : 40
					},{
						name : 'email',
						index : 'email',
						sortable : false,
						hidden:true,
						width : 40
					},{
						name : 'birth',
						index : 'birth',
						sortable : false,
						hidden:true,
						width : 40
					},{
						name : 'operatorID',
						index : 'operatorID',
						hidden:true
					},{
						name : 'operatorColumn',
						index : null,
						align : 'center',
						formatter : operatorColumn_formater,
						title : false,
						width : 50,
						sortable : false
					}],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#expertPager',
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
	loadComplete : function() {
		$(window).trigger('resize');
		$("#showMainPage").unmask();
	},
	multiselect : false,
	multiboxonly : false,
	altRows : true,
	altclass : 'altclass'
};

//修改为新滚动条
function modifyToNiceScroll(selector) {
	$(selector).each(function(index, element) {
		$(element).niceScroll( {
			horizrailenabled : false,
			scrollspeed : 20
		});
	});
}

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4) {
	alink += "<a style='text-decoration : none;margin-right:10px' href='javascript:void(0)' onclick=\"editExpert('"
			+ d3.expertID
			+ "','"
			+ d3.name
			+ "','"
			+ d3.education
			+ "','"
			+ d3.department
			+ "','"
			+ d3.major
			+ "','"
			+ d3.telephone
			+ "','"
			+ d3.email
			+ "','"
			+ d3.birth
			+ "','"
			+ d3.operatorID
			+ "')\"><span class='operatorColumn sheetWord'>编辑</span></a>";
	alink += "<a style='text-decoration : none;' href='javascript:void(0)' onclick=\"deleteMsgById('"
			+ d3.expertID
			+ "')\"><span class='operatorColumn sheetWord'>删除</span></a>";
	return alink;
}

//获取学历
function getEducation() {
	dwr.engine.setAsync(false);
	$("#education").empty();
	ExpertService.findMapByPropertiesQuick(
			[ 'education', 'ct_codeTableName' ], "", true, function(data) {
				$("#education").append("<option value='全部'>全部</option>");
				for ( var i = 0; i < data.length; i++) {
					$("#education")
							.append(
									"<option value='" + data[i].education
											+ "'>"
											+ data[i]['ct_codeTableName']
											+ "</option>");
				}
			});
	dwr.engine.setAsync(true);
}

//新增评委
function addFun() {
	DialogUtil.openFloatWindow("module/managerRater/managertAdd.jsp", {
		wno : $("#expertTable").jqGrid('getDataIDs')
	}, {
		EVENT_OK : function(param) {
			param.operatorID = getOperatorId(param.name);
			ExpertService.saveEntity(param, function(data) {
				if (data) {
					mWin.ok("新增成功");
					//重新获取学历
					getEducation();
					jQuery("#expertTable").trigger("reloadGrid");
				} else {
					jjAlert("新增失败!");
				}
			});
			
			//OperatorService.saveEntity()
		}
	});
}

// 获取账号
function getOperatorId(zgh, name) {
	var operatorID = "";
	dwr.engine.setAsync(false); // 先增加账号
	ExpertService.addOperatorForExpertAndReturnOperatorId(zgh, name, function(
			data) {
		operatorID = data;
	});
	dwr.engine.setAsync(true);
	return operatorID;
}

//编辑
function editExpert(rowId, name, education, department, major,
		telephone, email, birth, operatorID) {
	DialogUtil.openFloatWindow("module/managerEdit/managerEdit.jsp", {
		name : name,
		education : education,
		department : department,
		major : major,
		phone : telephone,
		email : email,
		birth : birth,
		operatorID : operatorID
	}, {

		EVENT_OK : function(param) {
			ExpertService.updateEntity(param, "expertID='" + rowId + "'",
					function(data) {
						if (data) {
							mWin.ok("编辑成功");
							//重新获取学历
					//getEducation();
					jQuery("#expertTable").trigger("reloadGrid");
				} else {
					jjAlert("编辑失败!");
				}
			});
		}
	});
}

//单选删除
function deleteMsgById(rowId) {
	jConfirm('您确定要删除所选专家吗？', function() {
		ExpertService.deleteById(rowId, function(data) {
			if (data) {
				mWin.ok('删除成功');
				$("#expertTable").trigger("reloadGrid");
			} else {
				jAlert('删除失败!');
			}
		});
	});
}

//多选删除
function deleteSelectedsFun() {
	var selrow = getSelectedRowIDs();
	if (selrow != null && selrow.length > 0) {
		jConfirm('您确定要删除所选专家吗？', function() {
			ExpertService.deleteByIds(selrow, function(data) {
				if (data) {
					mWin.ok("删除成功");
					jQuery("#expertTable").trigger("reloadGrid");
				} else {
					jAlert("删除失败!");
				}
			});
		});
	} else {
		jAlert("请您选择具体的数据!");
	}
}

//获取表格的选中的rowId  参数  一个表格的jquary对象
function getSelectedRowIDs() {
	return jQuery('#expertTable').getGridParam('selarrrow');//多选
}

//快速搜索
function quickSearch() {
	$("#showMainPage").mask("正在搜索中,请耐心等候......");
	var con = "1=1";
	var ZGH = $("#ZGHSearch").val();
	var name = $("#nameSearch").val();
	var education = $("#education option:selected").val();
	var type = $("#type option:selected").val();

	if (ZGH != '请输入职工号') {
		con += " and zgh like '%" + ZGH + "%'";
	}
	if (education != '全部') {
		con += " and education = '" + education + "'";
	}
	if (type != '-1') {
		con += " and operatorType = '" + type + "'";
	}
	if (name != '请输入姓名') {
		con += " and name like '%" + name + "%'";
	}

	expertTable.setGridParam( {
		condition : con,
		page : 1
	}).trigger("reloadGrid");
	$("#showMainPage").unmask();
}