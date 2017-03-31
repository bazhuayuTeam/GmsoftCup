;

var selectedrowid = new Array();
var courseNameAry = new Array();
var signleSelect  = false;//是否为单选
var resizable = false;//是否允许更改框的大小
var size= {};
size.height = 580;
size.width = 1000;
var allSelect = false;
var hasSelectAll = false;
var mytree = null;

//程序入口
$(document).ready(function(){
  $("#addCourseComponent").mask("正在加载中，请耐心等候......");
	
	//初始化所有学院
  	HandleViewService.findDepartment(function(data) {
  		for(var i=0;i<data.length;i++){
			$("#departmentSearch").append("<option>"+data[i]['BM']+"</option>");
		}
  	});
	
	if(UserPackage.PageCall.beginInitComponents){
		UserPackage.PageCall.beginInitComponents();
	}
	
	//控件初始化	
	if(Page.initComponent){
		Page.initComponent();
	}
	
	//检查所有调用是否返回并开始加载数据
	Page.Util.checkCallFinish();
	
	//初始化表格
	iniJqGrid();
	
	if(allSelect){
		$("#allSelect").css("display","inline");
	}
	
	$("#courseSearch").Watermark("请输入姓名","#8b8b8b");
	$("#kcdmSearch").Watermark("请输入职工号","#8b8b8b");
});


var dialogsetting = {
	    height : size.height,
		width : size.width,
		resizable : resizable ,//允许更改大小
		modal : true ,// 模式
		title: "新增操作人员",
		winMode : ChildDialogUtil.getWinMode(),
		//关闭
		close : function(event)
		{
			if(!event.data)
			{
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				//ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL , {});
				return;
			}
			if(event.data.type == DialogUtil.EVENT_OK)
			{
				var result = getResult();
				//这里处理返回值
			    ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
				//ChildDialogUtil.doClose(event.data.type , selectedrowid);
			}
			/*else
			{
				ChildDialogUtil.doClose(event.data.type , {});
			}*/
			else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
			DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
}};

function getResult() {
	var id =$("#courseTable").jqGrid("getGridParam","selrow");
	rowData = $("#courseTable").jqGrid("getRowData",id);
	var data = {};
	data.XM = rowData.XM.replace(rowData.XM.substring(rowData.XM.indexOf("<"), rowData.XM.lastIndexOf(">") + 1), "");
	data.ZGH = rowData.ZGH.replace(rowData.ZGH.substring(rowData.ZGH.indexOf("<"), rowData.ZGH.lastIndexOf(">") + 1), "");
	data.XYDM = rowData.XYDM;
	return data;
}

var gridSetting ={
	height : '231',
	datatype : "json" ,
	dwrFun : HandleViewService.findOperator,
	dwrCountFun : HandleViewService.findCountOperator,
	condition : '',
	searchCondition : '' ,
	selectCondition : '' ,
	needLink : false ,
	dwr : true ,
	autowidth : true ,
	sortable:true,
	sortname:'',
	order:'',
	altRows:true,//设置隔行换色；
	altclass:'altclass',//隔行换色样式
	colNames : ['专业代码','职工号','姓名','部门'] ,
	colModel : [
	{
		name : 'XYDM',
		index : 'XYDM',
		sortable : false,
		hidden : true,
		width : 30
	},
	{
		key : true ,
		name : 'ZGH' ,
		index : 'ZGH' ,
		sortable : false,
		width:80,
		align:'center'
	},
	{
		name : 'XM' ,
		index : 'XM' ,
		sortable : false,
		width:95,
		align:'center'
	} ,
	{
		name : 'BM',
		index : 'BM',
		sortable : false,
		width:100
	}] ,
	rowNum : 10,
	rowList : [ 10,20,30 ],
	pager : '#coursePager',
	loadComplete:function()
	{
    	$("#addCourseComponent").unmask();
		setSelectedRow();		
		$(window).trigger('resize');
	},
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
	beforeSelectRow:function(rowid, e)
	{
		if(allSelect && hasSelectAll)
		{
			return false;
		}else
		{
			return true;
		}
		
	},
	onSelectRow:function(rowid,status)
	{
		if(status)
		{
			selectCourse(rowid);
		}else
		{
	    	unselectCourse(rowid);
		}
	},
	onSelectAll:function(aRowids,status)
	{
		if(allSelect && hasSelectAll)
		{
			return;
		}
		if(status)
		{
			selectCurPageCourse(aRowids);
		}else
		{
			unselectCurPageCourse(aRowids);
		}
	},
	multiselect :  signleSelect,
	multiboxonly : signleSelect
    };

//回车事件监听
function checkkey(event){
  var evt = event || window.event;
  	if (evt.keyCode==13)
		quickSearch();
}

$("#addCourseComponent").createDialog(dialogsetting);	

FormUtil.valid = function()	{
		var executeValid = false;
		if(selectedrowid && selectedrowid.length == 0)
		{
			executeValid = true;
		}
		if(executeValid)
		{
			if(confirm("您还未选择人员，确定退出？")){
				return false;
			}
			else{
				return '您取消了退出,可以继续新增人员!';
			}
		}		
}
	
//初始化表格
function iniJqGrid(){
	gridSetting.searchCondition="ZGH NOT IN (SELECT ZGH FROM OPERATOR)";
    jQuery("#courseTable").jqGrid(gridSetting);
	jQuery("#courseTable").jqGrid('navGrid' , '#coursePager' ,
	{
		edit : false ,
		add : false ,
		del : false ,
		search : false,
		refresh : false
	});
	$(window).bind('resize', function()
	{
	  $("#courseTable").setGridWidth($("#addCourseComponent").width()*0.93- 3);
	}).trigger('resize');
}

//搜索
function quickSearch(){
	$("#showMainPage").mask("正在加载中，请耐心等候......");
	var condition="1=1";
	var couresSearch = $("#courseSearch").val();
	var kcdm = $("#kcdmSearch").val();
	var options=$("#departmentSearch option:selected");
	var departmentSearch = options.text();
	
    if(couresSearch != '请输入姓名'&&couresSearch!=""){
    	condition += " and XM LIKE '%" + couresSearch + "%' ";
    }
    if(departmentSearch != '全部'&&departmentSearch !=''){
    	condition += " and BM LIKE '%" + departmentSearch + "%' ";
    }
    if(kcdm != '请输入职工号'&&kcdm !=''){
    	 condition+=" and ZGH LIKE '%" + kcdm + "%'";
    }
	$("#courseTable").setGridParam( {
		searchCondition :condition
	}).trigger("reloadGrid");
}

	
function isSelected(rowid){
	var exist = false;
	for( var i in selectedrowid)
	{
		
		if(selectedrowid[i] == rowid)
		{
			exist = true;
			break;
		}
	}
	return exist;
}

function selectCourse(rowid){
	var course = $('#courseTable').getRowData(rowid);
	
	if(selectedrowid.length == 0)
	{
	}
	if(isSelected(rowid) == false)
	{
		var newCourse = getFieldSetNodeStr(course['ZGH'],course['XM']);
		if(signleSelect)
		{
			selectedrowid[0] = rowid;
			courseNameAry[0] = course['XM'];
			document.getElementById("selectedCourse").innerHTML = newCourse;
		}else
		{
			selectedrowid[selectedrowid.length] = rowid;
			courseNameAry[courseNameAry.length] = course['XM'];
			document.getElementById("selectedCourse").innerHTML += newCourse;
			bindScroll();
		}
	}
}

function bindScroll(){
	var scroll = $("#selectedCourse");
	scroll[0].scrollTop = scroll[0].scrollHeight;
}

function unselectCourse(rowid)
{
	var selectedCourseDom = document.getElementById("selectedCourse");
	var allFieldSet = selectedCourseDom.childNodes;
	for(var i = 0, len = allFieldSet.length; i < len; i++) {
		if(allFieldSet[i].nodeType == 1 && rowid == allFieldSet[i].id) {
			allFieldSet[i].style.display = "none";
			allFieldSet[i].id = "discap";
		} 
	} 
	unSelectCourseAry(rowid);
	$("#courseTable").jqGrid("resetSelection",rowid);
}

function unSelectCourseAry(rowid)
{
	for( var i in selectedrowid)
	{
		if(selectedrowid[i] == rowid)
		{
			var deleteid = selectedrowid.splice(i , 1);
			courseNameAry.splice(i , 1);
			
			if(deleteid =="00000000")
			{
				hasSelectAll = false;
			}
		}
	}
}

function unSelectFromFieldset(rowid)
{
	unselectCourse(rowid);
	$("#courseTable").jqGrid("resetSelection",rowid);
}

function deleteAll()
{
	selectedrowid.length = 0;
	courseNameAry.length = 0;
	$("#courseTable").jqGrid("resetSelection");
	setSelectedRow();
	document.getElementById("selectedCourse").innerHTML ="";
}

function selectCurPageCourse(aRowids)
{
	if(selectedrowid.length == 0)
	{
	}
	var html = "";
	for(var index in aRowids)
	{
		var rowid = aRowids[index];
		if(isSelected(rowid) == false)
		{
			var course = jQuery('#courseTable').getRowData(rowid);
			var newCourse = getFieldSetNodeStr(course['ZGH'],course['XM']);
			selectedrowid[selectedrowid.length] = rowid;
			courseNameAry[courseNameAry.length] = course['XM'];
			html += newCourse;
		}
	}
	document.getElementById("selectedCourse").innerHTML  += html;
	bindScroll();
}

function unselectCurPageCourse(aRowids)
{
	for(var index in aRowids)
	{
		var rowid = aRowids[index];
		if(isSelected(rowid))
		{
			unselectCourse(rowid);	
		}
	}
}

function reAddCourse()
{
	if(!selectedrowid || selectedrowid.length == 0)
	{
		return ;
	}
	for(i in selectedrowid)
	{
		var rowid = selectedrowid[i];
		if(rowid == "00000000" && allSelect == true)
		{
			hasSelectAll = true;
		}
		var newCourse = getFieldSetNodeStr(selectedrowid[i],courseNameAry[i]);
		document.getElementById("selectedCourse").innerHTML += newCourse;
		bindScroll();
	}
}

//添加所有人员
function addAllCourse()
{
	if(allSelect == false)
	{
		return ;
	}
	if(selectedrowid.length!=0)
	{
		selectedrowid.length=0;
		courseNameAry.length=0;
	}
	if(hasSelectAll == false)
	{
		hasSelectAll = true;
		var newCourse = getFieldSetNodeStr("00000000","所有人员");
		document.getElementById("selectedCourse").innerHTML = newCourse;
		selectedrowid[0] = "00000000";
		courseNameAry[0] = "所有人员";
	}else
	{
		hasSelectAll = false;
	}
	setSelectedRow();
	bindScroll();
}

//让某行被选中
function setSelectedRow()
{
	for( var i in selectedrowid)
	{
		$("#courseTable").jqGrid("setSelection",selectedrowid[i],false);
	}
}

function getFieldSetNodeStr(id,name)
{
	return "<fieldset  style = \"width:170px;height:34px;float:left;border:1px solid #D5DCEE;margin-left:10px;margin-top:5px;background-color:#F8F8F8;\" id='"+id+"' title='"+name+"' ><div style=\"float:left;cursor:default;width:128px;overflow:hidden;\"><font color='#2E8BD9'>"+name+"</font></div><div style=\"float:right;cursor:pointer;\" onclick=\"unSelectFromFieldset('"+id+"');\" title=\"取消该人员\" class=\"removeDiv\"><a>[删除]</a></div></fieldset>";
}