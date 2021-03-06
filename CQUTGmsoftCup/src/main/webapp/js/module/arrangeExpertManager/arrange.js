
var signleSelect=false
var selectedrowid=new Array();  
var selectName=[];
var allSelect=false;
var save=true;
var pageData={
	number:0,
	targeCode:null,
	task:null
}

$(function(){
	if(ChildDialogUtil.getExchangeData){
		var data = ChildDialogUtil.getExchangeData();
		if(data.id){
			pageData.targeCode=data.id;
		}
		if(data.task){
			pageData.task=data.task;
		}
	}
	
	//添加水印文字
	$("#teacherSearch").Watermark("请输入姓名","#8f8f8f");
	
	//加载表
	$("#teacherTable").jqGrid(gridSetting);
	$("#teacherTable").jqGrid('navGrid', '#teacherPager', {
		edit : false,
		add : false,
		del : false,
		search:false
	});
	
	//创建对话框
	 $("#dialog-modal").createDialog({
		 width:"825",
		 height:"565",
		 resizable:false,       
		 modal : true,
		 winMode : ChildDialogUtil.getWinMode(), 
		 //按钮
		 buttons:{
		 	"确定":function(){
		 		var result={   
			 		teacherAppend:selectedrowid,
			 		name:selectName,//选择的专家的名字和链接
					save:save
			 	};
		 		ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
		 	},
		 	"退出":function(){
		 		DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
		 	},
		 },
		 //点击保存
		 close:function(event){
			 if(!event.data){
				 ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL, {});      
				 return;
			 }
			 if(event.data.type == DialogUtil.EVENT_OK){   
			 	var result={   
			 		teacherAppend:selectedrowid,
			 		name:selectName,//选择的专家的名字和链接
					save:save
			 	};
				ChildDialogUtil.doClose(event.data.type, result); 
			 }
			 else if(event.data.type==DialogUtil.EVENT_ERROR){             //发生错误的情况
				 return;
			 }
			 else{                                                         //点击退出时,值为EVENT_CANEL  关闭窗口
				 ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL, {});     
			 }
		}
	 })
	 loadExitData();
});

//配置表
var gridSetting={
	height:'210',
	datatype : "json",
	dwrFun : ExpertService.findMapByPropertiesWithLimit,
	dwrCountFun : ExpertService.findCountByProperties,
	needLink : true,
	dwr : true,
	autowidth:true,
	colNames : ['登陆号','评委姓名'],
	colModel : [{
		key:true,
		name:'expertID',
		index:'expertID',
		align:'center'
	},{
		name:'name',
		index:'name',
		width:'60',
		align:'center',
	}],
	rowNum : 5,
	rowList : [ 5,10,15],
	pager : '#teacherPager',
	viewrecords : true,
	rownumbers : false,
	jsonReader : {
		root : "rows",
		page : "page",
		total : "total",
		records : "records", 
		repeatitems : false
	},
	//点击某行时的操作
	onSelectRow:function(rowid,status){
		if(status){
			selectPeople(rowid);
		}else{
	    	unselectPeople(rowid);
		}
	},
	//全选
	onSelectAll:function(aRowids,status){
		if(allSelect && hasSelectAll){
			return;
		}
		if(status){
			selectCurPagePeople(aRowids);
		}else{
			unselectCurPagePeople(aRowids);
		}
	},
	
	multiselect:signleSelect==false,
	multiboxonly:signleSelect,
	altRows:true,//设置隔行换色；
	altclass:'altclass',//隔行换色样式
};

//--------------------------------------------初始化数据--------------------------------------

//消息验证
userDialogValid.valid=function(){
	if(selectedrowid && selectedrowid.length == 0){
		save=true;
		return true;
	}
};

//加载已经选择的数据
function loadExitData(){
	dwr.engine.setAsync(false);
	ExpertTargetDistributeService.findMapByPropertiesQuick(['ex_expertID','ex_name'],"gameStepDetailID='"+pageData.task+"' and teamID='"+pageData.targeCode+"'",true,function(data){
		if(data){
			var html="";
			for(var i =0;i<data.length;i++){
				$("teacherTable").jqGrid('setSelection',data[i].ex_expertID);
				var newPeople = getFieldSetNodeStr(data[i].ex_expertID,data[i].ex_name);
				selectedrowid[selectedrowid.length] = data[i].ex_expertID;
				selectName[selectName.length]=getStyle(data[i].ex_expertID,data[i].ex_name);
				document.getElementById("selectedPeople").innerHTML += newPeople;
			}
		}
	})
	dwr.engine.setAsync(true);
}

//选中某一行时，将人员添加到下方栏
function selectPeople(rowid){
	var recevier = jQuery('#teacherTable').getRowData(rowid);
	if(isSelected(rowid) == false){
		var newPeople = getFieldSetNodeStr(recevier['expertID'],recevier['name']);
		if(signleSelect){
			selectedrowid[0] = rowid;
			selectName[0]=getStyle(rowid,recevier['name']);
			document.getElementById("selectedPeople").innerHTML = newPeople;
		}else{
			selectedrowid[selectedrowid.length] = rowid;
			selectName[selectName.length]=getStyle(rowid,recevier['name']);
			document.getElementById("selectedPeople").innerHTML += newPeople;
		}
	}
	showNumberNow();
}

function getStyle(id,name){
	return name;
}

//取消选中某行时
function unselectPeople(rowid){
	var selectedPeopleDom = document.getElementById("selectedPeople");
	var allFieldSet = selectedPeopleDom.childNodes;
	for(var i = 0, len = allFieldSet.length; i < len; i++) {
		if(allFieldSet[i].nodeType == 1 && rowid == allFieldSet[i].id) {
			allFieldSet[i].style.display = "none";
			allFieldSet[i].id = "discap";
		} 
	} 
	unSelectPeopleAry(rowid);
	$("#teacherTable").jqGrid("resetSelection",rowid);
	showNumberNow();
}

//全选
function selectCurPagePeople(aRowids){
	var html = "";
	for(var index in aRowids){
		var rowid = aRowids[index];
		if(isSelected(rowid) == false){
			var recevier = jQuery('#teacherTable').getRowData(rowid);
			var newPeople =getFieldSetNodeStr(recevier['expertID'],recevier['name']);
			selectedrowid[selectedrowid.length] = rowid;
			selectName[selectName.length] = getStyle(rowid,recevier['name']);
			document.getElementById("selectedPeople").innerHTML += newPeople;
		}
	}
	document.getElementById("selectedPeople").innerHTML  += html;
	showNumberNow();
}

//取消全选时
function unselectCurPagePeople(aRowids){
	for(var index in aRowids){
		var rowid = aRowids[index];
		if(isSelected(rowid)){
			unselectPeople(rowid);	
		}
	}
	showNumberNow();
}

//去掉删除的人员
function unSelectPeopleAry(rowid){
	for(var i in selectedrowid){
		if(selectedrowid[i] == rowid){
			selectedrowid.splice(i,1);
			selectName.splice(i,1);
		}
	}
}

//判断是否已经选过
function isSelected(rowid){
	var exist = false;
	for( var i in selectedrowid){
		if(selectedrowid[i] == rowid){
			exist = true;
			break;
		}
	}
	return exist;
}

//显示当前选择了多少人数
function showNumberNow(){
	$("#countNum").text(selectedrowid.length);
}

//点击删除某一个人员时
function unSelectFromFieldset(rowid){
	unselectPeople(rowid);
	showNumberNow();
}

//点击清空按钮
function deleteAll(){
	selectedrowid.length = 0;
	selectName.length=0;
    $("#teacherTable").jqGrid("resetSelection");//表格中的设置选中的为空
	document.getElementById("selectedPeople").innerHTML ="";
	showNumberNow();
}

//人员显示的格式
function getFieldSetNodeStr(id,name){
	var result="<div class=\"selectPersonDiv\" style=\"width:150px;\" id='"+id+"'>" +
	"<div class=\"selecteMajorName\">"+name+"</div>" +
		"<div class='selectDeleteDiv' style='width:50px;' onclick=\"unSelectFromFieldset('"+id+"');\">" +
		"<div class='selectDelete'>删除" +
		"</div></div></div>";
	
	return result;
	
}

//快速搜索
function quickSearch(){
	var condition="true=true";
	var name=$("#teacherSearch").val();
	if(name!="请输入姓名"){
		condition+=" and name like'%"+name+"%'";
	}
	refreshTable(condition);
}

//刷新表格
function refreshTable(condition){
	if(condition){
		$("#teacherTable").setGridParam( {searchCondition:condition} ).trigger("reloadGrid");
	}
	else{
		$("#teacherTable").trigger("reloadGrid");
	}
}

//监听回车事件
function enterEvent(event,id){
	if(event.keyCode==13&&id=='teacherSearch'){
		quickSearch();
	}
}

