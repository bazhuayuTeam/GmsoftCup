;

var expertTable = $("#expertTable");

$(document).ready(function() {
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

var gridSetting= {
			height : 'auto',
			datatype : "json",
			dwrFun : ExpertService.findMapByPropertiesWithLimit,
			dwrCountFun : ExpertService.findCountByProperties,
			condition :"",
			searchCondition : '',
			selectCondition : '',
			needLink : true,
			dwr : true,
			autowidth : true,
			colNames :['账号','姓名','专业领域','出生年月','电话号码','操作员ID','操作'],
			colModel :[{
						name : 'expertID',
						index : 'expertID',
						sortable : false,
						width : 30,
						align:'center'
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
						width : 60
					},{
						name : 'birth',
						index : 'birth',
						sortable : false,
						align: 'center',
						width : 20
					},{
						name : 'telephone',
						index : 'telephone',
						align: 'center',
						sortable : false,
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
			jsonReader :
			{
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false
			},
			loadComplete:function(){
				$(window).trigger('resize');
				$("#showMainPage").unmask();
			},
			multiselect : false,
			multiboxonly : false,
			altRows:true,
			altclass:'altclass'
		};

function modifyToNiceScroll(selector) {
	$(selector).each(function(index, element) {
		$(element).niceScroll({horizrailenabled : false,scrollspeed : 20});
	});
}


function operatorColumn_formater(data, d2, d3, d4){
	var alink ="";
	alink += "<a style='text-decoration : none;margin-right:10px' href='javascript:void(0)' onclick=\"editExpert('" + d3.expertID + "','"+d3.name+"','"+d3.department+"','"+d3.education+"','"+d3.birth+"','"+d3.telephone+"')\"><span class='operatorColumn sheetWord'>编辑</span></a>";
	alink += "<a style='text-decoration : none;' href='javascript:void(0)' onclick=\"deleteMsgById('" + d3.expertID + "')\"><span class='operatorColumn sheetWord'>删除</span></a>";
	return alink;
}

function addFun(){
	DialogUtil.openFloatWindow("module/expertManager/expertAdd.jsp", {}, {
		EVENT_OK : function(param) {
			ExpertService.saveData(param,function(data){
				if(data){
					mWin.ok("新增成功");
					jQuery("#expertTable").trigger("reloadGrid");
				}
				else{
					jjAlert("新增失败!");
				}
			});
		}
	});
}

function getOperatorId(zgh,name){
		var operatorID = "";
		dwr.engine.setAsync(false);	
		ExpertService.addOperatorForExpertAndReturnOperatorId(zgh,name,
			function(data){
				operatorID = data;
		});
		dwr.engine.setAsync(true);
		return operatorID;
}

function editExpert(rowId,name,department,education,birth,telephone){
	DialogUtil.openFloatWindow("module/expertManager/expertEdit.jsp",{ZGH:rowId,name:name,
		department:department,education:education,major:birth,phone:telephone},{
		EVENT_OK:function(param){
			ExpertService.updateEntity(param,"expertID='"+rowId+"'",function(data){
				if(data){
					mWin.ok("编辑成功");
					jQuery("#expertTable").trigger("reloadGrid");
				}else{
					jjAlert("编辑失败!");
				}
			});
		}
	});
}

function deleteMsgById(rowId){
	jConfirm('确认要删除吗?',function() {
		ExpertService.deleteById(rowId,function(data){
			if (data) {
					mWin.ok('删除成功');
					$("#expertTable").trigger("reloadGrid");
				} else {
					jAlert('删除失败!');
				}
		});
	});
}


function deleteSelectedsFun(){
	var selrow = getSelectedRowIDs();
		if (selrow != null && selrow.length > 0) {
			if(jConfirm('您确定要删除吗？')){
				ExpertService.deleteByIds(selrow,function(data) {
						if (data) {
							mWin.ok("删除成功");
							jQuery("#expertTable").trigger("reloadGrid");
						}else{
							jAlert("删除失败");
						}
				});
			}
		} else {
			jAlert("请选择你要删除的数据");
		}
}

function getSelectedRowIDs() {
	return jQuery('#expertTable').getGridParam('selarrrow');//澶氶�
}


function quickSearch(){
	var name = $("#nameSearch").val();
	var con="true =true";
	if(name != "请输入姓名"){
		con += " and name like '%"+name+"%'";
	}
	
	expertTable.setGridParam({condition : con, page : 1}).trigger("reloadGrid");
	$("#showMainPage").unmask();
}