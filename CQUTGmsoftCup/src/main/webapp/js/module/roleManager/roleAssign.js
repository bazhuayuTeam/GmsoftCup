;

var isRoleAssign=1;//用于人员选择器
pageData={
	operatorID:operatorId,
    roleID:null,
    roleName:null,
    selPersonName:null,
    selPersonZgh:null,
    selPersonBm:null,
    condition:null
};

//入口
$(function() {
	//人员选择器
	FormUtil.createComponent(["personSelect"],{});
	//已有角色表格
	jQuery("#selectedTable").jqGrid(selectedGridSetting);
	jQuery("#selectedTable").jqGrid('navGrid', '#selectedPager', {
		edit : false,
		add : false,
		del : false,
		search:false
	});
	//备选角色表格
	jQuery("#spareTable").jqGrid(spareGridSetting);
	jQuery("#spareTable").jqGrid('navGrid', '#sparePager', {
		edit : false,
		add : false,
		del : false,
		search:false
	});
	
	$(window).bind('resize', function() { 
	    $("#selectedTable").setGridWidth($("#mainArea").width()*0.45-2); 
	}).trigger('resize'); 
	
	$(window).bind('resize', function() { 
	    $("#spareTable").setGridWidth($("#mainArea").width()*0.45-2); 
	}).trigger('resize'); 
});

//已有角色JGrig
var selectedGridSetting={
	url : 'null',
	height:'auto',
	width:'auto',
	datatype : "json",
	dwrFun : RoleAssignService.findMapByPropertiesWithLimit,
	dwrCountFun : RoleAssignService.findCountByProperties,
	condition :'',
	searchCondition : '',
	selectCondition : "me.operatorID=null",
	needLink : true,
	dwr : true,
	sort:false,
	autowidth:true,
	colNames : [ 'id', '角色名称 ', '操作','roleID' ],
	colModel : [{
		key:true,
		name : 'me_roleAssignId',
		index : 'me_roleAssignId',
		hidden:true		
	},	{
		name : 'role_roleName',
		index : 'role_roleName',
		width:50,
	},	{
		name : 'operateColumn',
		index : null,
		align: 'center',
		formatter : selectedFormatter,
		title : false,
		width : 50,
		sortable : false
	},{
		name : 'me_roleId',
		index : 'me_roleId',
		hidden:true,
	}
	],
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#selectedPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false
	},
	ondblClickRow:function(rowid,iRow,iCol,e){//双击表格的行
		deleteFun(rowid);
	},
	multiselect:false,
	multiboxonly:false,
	caption:'已有角色',
	hidegrid:false,
	altRows:true,
	altclass:'altclass'
};

//备选角色JGrid
var spareGridSetting = {
	url : 'null',
	height:'auto',
	width:'auto',
	datatype : "json",
	dwrFun : RoleService.findMapByPropertiesWithLimit,
	dwrCountFun : RoleService.findCountByProperties,
	condition : '',
	searchCondition : '',
	selectCondition : '',
	needLink : true,
	dwr : true,
	autowidth:true,
	colNames : [ 'id', '角色名称','操作' ],
	colModel : [{
		key:true,
		name : 'me_roleID',
		index : 'me_roleID',
		hidden:true
	},	{
		name : 'me_roleName',
		index : 'me_roleName',
		width:50,
	},	{
		name : 'operateColumn',
		index : null,
		align: 'center',
		formatter : spareFormatter,
		title : false,
		width : 50,
		sortable : false
	}],
	rowNum : 10,
	rowList : [ 10, 20, 30],
	pager : '#sparePager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", // 总记录数
		repeatitems : false
	},
	ondblClickRow:function(rowid,iRow,iCol,e){//双击表格的行
		addSelectFun(rowid);
	},
	multiselect:false,
	multiboxonly:false,
	caption:'可选角色',
	hidegrid:false,
	altRows:true,
	altclass:'altclass'
};

function selectedFormatter(data, d2, d3, d4){
	var alink = "<a href='javascript:void(0)' onclick=\"deleteFun('"+d2.rowId+"')\">" +
				"<img border='0' src='images/plugins/jqgrid/for_right.gif'/>" +
				"<span class='operatorColumn'>移除</span></a>";		
	return alink;
}

function spareFormatter(data,d2,d3,d4){
	var alink = "<a href='javascript:void(0)' onclick=\"addSelectFun('"+d2.rowId+"')\">" +
				"<img border='0' src='images/plugins/jqgrid/for_left.gif'/>" +
				"<span class='operatorColumn'>选择</span></a>";
	
	return alink;
}

//显示当前选择人员
function currentState(){
	if(pageData.selPersonName){
	    $("#personSelect").val(pageData.selPersonName);
	    $("#currentPerson").html(pageData.selPersonName);
	}else{    	
    	var fomatter="<label style='color:red;'>暂无信息</label>";
    	$("#department").html(fomatter);
    }
}

//删除分配
function deleteFun(rowId){
	if(rowId && rowId != null){
		RoleAssignService.deleteById(rowId,function(data){
			if (data) {
			     mWin.ok('删除成功');
				jQuery("#selectedTable").trigger("reloadGrid");
			} else {
				jAlert('删除失败!');
			}
		});
	}
}

//添加分配
function addSelectFun(rowId){	
	if(!pageData.selPersonZgh){
		jAlert("请您先选择人员!");
		return;
	}else{
		var result={};
		result.roleID=rowId;
		result.operatorID=pageData.operatorID;
		
		RoleAssignService.isExistRole(rowId,pageData.operatorID,function(data){
			if(data==1){
				jAlert("该人员已经具有此角色,请您重新选择!");
			}
			else{
				RoleAssignService.saveEntity(result,function(data){
					if(data){
						pageData.roleID=rowId;
						jQuery("#selectedTable").trigger("reloadGrid");
						jQuery("#spareTable").trigger("reloadGrid");
						mWin.ok('添加分配信息成功');
					}else{
						mWin.ok('添加分配信息失败!');
					}
				});
			}
		});
    }
}


//关闭弹出框后获取从子页面传过来的值
function refresh(result){
	//获取选择人员
	 pageData.selPersonZgh=result.zgh;
	 pageData.selPersonName=result.name;
	 pageData.operatorID=result.operatorID;
	 //获取角色id	
	 
	 getRoleId();	
	 pageData.condition="me.operatorID='"+pageData.operatorID+"'";
	 
	 currentState();	
 	 jQuery("#selectedTable").setGridParam({
 	   			selectCondition :pageData.condition
 			}).trigger("reloadGrid");
	 jQuery("#spareTable").trigger("reloadGrid");
}

//取得roleID
function getRoleId(){
	dwr.engine.setAsync(false);
	RoleAssignService.findMapByPropertiesQuick(['roleID','roleAssignID'],"operatorID='"+pageData.operatorID+"'",false,function(data){
		if(data){
			pageData.roleID=data[0].roleID;
		}
	});
	dwr.engine.setAsync(true);
}
