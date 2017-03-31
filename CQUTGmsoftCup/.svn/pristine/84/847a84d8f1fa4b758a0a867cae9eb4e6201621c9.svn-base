;

var operatorTable = $("#operatorTable");

//页面加载执行-----------------------------------------------------------------
$(document).ready(function(){
	//$("#showMainPage").mask("正在加载中,请耐心等候......");
	$("body").niceScroll({scrollspeed : 20,horizrailenabled: false});
	$("#ZGHSearch").Watermark("请输入职工号","#8b8b8b");
	$("#nameSearch").Watermark("请输入姓名","#8b8b8b");
	
	jQuery("#operatorTable").jqGrid(gridSetting);
	jQuery("#operatorTable").jqGrid('navGrid', '#operatorPager',
	{
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false
	});
	$(window).bind('resize', function()
	{
		$("#operatorTable").setGridWidth($("#MainArea").width());
	}).trigger('resize');
});

//jqGrid表格配置
var gridSetting = {
	height : 'auto',
	datatype : "json",
	dwrFun : OperatorService.findMapByPropertiesWithLimit,
	dwrCountFun : OperatorService.findCountByProperties,
	condition :"",
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth : true,
	colNames :['operatorID','账号','姓名','学院','专业','是否禁用', '操作'],
	colModel :[{
				key : true,
				name : 'operatorID',
				index : 'operatorID',
				hidden : true,
				sortable : false,
				width : 30
			},{
				name : 'ZGH',
				index : 'ZGH',
				sortable : false,
				width : 40
			},{
				name : 'name',
				index : 'name',
				sortable : false,
				width : 40
			},{
				name : 'academicID',
				index : 'academicID',
				sortable : false,
				hidden : true,
			},{
				name : 'disciplineID',
				index : 'disciplineID',
				sortable : false,
				hidden : true,
			},{
				name : 'disabled',
				index : 'disabled',
				sortable : false,
				width : 40,
				formatter:function(val){
					if(val == 1){
						return "否";
					}
					else{
						return "是";
					}
				}
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
	pager : '#operatorPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader :
	{
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false
	// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	},
	loadComplete:function(){
		$(window).trigger('resize');
		$("#showMainPage").unmask();
	},
	multiselect : true,
	multiboxonly : false,
	altRows:true,
	altclass:'altclass'
};

//操作列生成Button
function operatorColumn_formater(data, d2, d3, d4){
	var alink = "<a style='text-decoration : none;margin-right:30px' href='javascript:void(0)' onclick=\"editFun('" + d2.rowId + "','"+d3.ZGH+"','"+d3.name+"','"+d3.academicID+"','"+d3.disciplineID+"')\"><span class='sheetWord'>编辑</span></a>&nbsp";
	if(d3.disabled == '1'){
		alink += "<a style='text-decoration : none;margin-right:30px' href='javascript:void(0)' onclick=\"stopOperator('" + d2.rowId + "')\"><span class='sheetWord'>禁用账号</span></a>";
	}
	else{
		alink += "<a style='text-decoration : none;margin-right:30px' href='javascript:void(0)' onclick=\"openOperator('" + d2.rowId + "')\"><span class='sheetWord'>启用账号</span></a>";
	}
	alink += "<a style='text-decoration : none;margin-right:30px' href='javascript:void(0)' onclick=\"rewritePassWord('" + d2.rowId + "')\"><span class='sheetWord'>重置密码</span></a>";
	alink += "<a style='text-decoration : none;' href='javascript:void(0)' onclick=\"deleteMsgById('" + d2.rowId + "')\"><span class='sheetWord'>删除</span></a>";
	return alink;
}

//启用账号
function openOperator(rowId){
	jConfirm("您确定要启用此账号吗?",function() {
		var param={
			disabled:1
		};
		OperatorService.updateEntity(param,"operatorID = '"+rowId+"'",function(data){
			if(data){
				mWin.ok("启用账号成功");
				$("#operatorTable").trigger("reloadGrid");
			}
			else{
				jAlert("启用账号失败!");
			}
		});
	});
}

//禁用账号
function stopOperator(rowId){
	jConfirm("您确定要禁用此账号吗?",function() {
		var param={
			disabled:0
		};
		OperatorService.updateEntity(param,"operatorID = '"+rowId+"'",function(data){
			if(data){
				mWin.ok("禁用账号成功");
				$("#operatorTable").trigger("reloadGrid");
			}else{
				jAlert("禁用账号失败!");
			}
		});
	});
}

//单选删除
function deleteMsgById(rowId){
	jConfirm('您确定要删除所选操作人员吗?',function() {
		OperatorService.deleteById(rowId,function(data){
			if (data && deleteByCondition("ID IN(SELECT SYSTEMFILEID FROM OPERATOR WHERE OPERATORID='"+rowId+"')")) {
					mWin.ok('删除成功');
					$("#operatorTable").trigger("reloadGrid");
				} else {
					jAlert('删除失败!');
				}
		});
	});
}

//多选删除
function deleteSelectedsFun(){
	var selrow = getSelectedRowIDs();
		if (selrow != null && selrow.length > 0) {
			jConfirm('您确定要删除所选操作人员吗?',function() {
				OperatorService.deleteByIds(selrow,function(data) {
					if (data && deleteByCondition("ID IN (SELECT SYSTEMFILEID FROM OPERATOR WHERE OPERATORID IN" + getINData(getSelectedRowIDs()) + ")")) {
						mWin.ok("删除成功");
						jQuery("#operatorTable").trigger("reloadGrid");
					}else{
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
	return jQuery('#operatorTable').getGridParam('selarrrow');//多选
}

//新增人员
function addFun(){
	dwr.engine.setAsync(false);
	DialogUtil.openFloatWindow("module/operatorManager/operatorAdd.jsp", {}, {
		EVENT_OK : function(param) {
			OperatorService.saveEntity(param, function(data) {
				mWin.ok("新增成功");
				jQuery("#operatorTable").trigger("reloadGrid");
			});
		}
	});
	dwr.engine.setAsync(false);
}

//编辑人员
function editFun(rowId,ZGH,name,academicID,majorID){
	DialogUtil.openFloatWindow("module/operatorManager/operatorEdit.jsp",{rowId:rowId,ZGH:ZGH,name:name,academicID:academicID,majorID:majorID},{
		EVENT_OK:function(param){
			OperatorService.updateEntity(param,"operatorID='"+rowId+"'",function(data){
				if(data){
					mWin.ok("编辑成功");
					jQuery("#operatorTable").trigger("reloadGrid");
				}else{
					jAlert("编辑失败!");
				}
			});
		}
	});
}

//重置密码
function rewritePassWord(rowId){
	var param={
		password:123456
	};
	jConfirm("您确定要重置密码吗?",function() {
		OperatorService.rewritePassWord(param.password,"operatorID='"+rowId+"'",function(data){
			if(data){
				mWin.ok("密码重置成功");
				jQuery("#operatorTable").trigger("reloadGrid");
			}
			else{
				jAlert("密码重置失败!");
			}
		});
	});
}

//快速搜索
function quickSearch(){
	$("#MainArea").mask("正在搜索中,请耐心等候......");
	var con = "1=1";
	var ZGH = $("#ZGHSearch").val();
	var name = $("#nameSearch").val();
	var isOpen = $("#isOpen option:selected").val();
	
	if(ZGH != '请输入职工号'){
		con += " and zgh like '%"+ZGH+"%'";
	}
	if(isOpen != ''){
		con += " and disabled = '"+isOpen+"'";
	}
	if(name != '请输入姓名'){
		con += " and name like '%"+name+"%'";
	};
	
	operatorTable.setGridParam({condition : con, page : 1}).trigger("reloadGrid");
	$("#MainArea").unmask();
}