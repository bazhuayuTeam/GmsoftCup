;
var needData = {
	state:"",
	inputEnd:"",
	checkEnd:"",
	delayed:null,
	newTime:""
};

$(function() {
	getTaskData();
	needData.newTime = getTime();
	controlShow();
});

function controlShow() {
	if(type == 1) {
		if(needData.state == 0 && (needData.newTime <= needData.inputEnd || (needData.newTime > needData.inputEnd && needData.delayed == 1))) {
			maskReport();
		}else{
			noShow();
		}
	}else if(type == 2) {
		if(needData.state == 1 && (needData.newTime <= needData.checkEnd || (needData.newTime > needData.checkEnd && needData.delayed == 1))) {
			maskReport();
		}else{
			noShow();
		}
	}else if(type == 3) {
		if(needData.state == 2 && (needData.newTime <= needData.checkEnd || (needData.newTime > needData.checkEnd && needData.delayed == 1))) {
			
		}else{
			noShow();
		}
	}else if(type == 4 || type == 5) {
		noShow();
	}
}

function noShow() {
	maskOperatorColumn();
	maskAddEditReport();
	maskSaveButton();
	maskTableOperate();
}

//查找任务数据，包括录入截至时间，审核截至时间，专业是否延期等
function getTaskData() {
	dwr.engine.setAsync(false);
	TaskService.findTaskOfTaskDetail(taskDetailID,function(data) {
		if(data&&data.length > 0) {
			needData.state = data[0].TASKDETAILSTATE;//专业当前状态
			needData.inputEnd=data[0].taskInputDate;//录入截至时间
			needData.checkEnd=data[0].taskAuditDate;//审核截至时间
			needData.delayed = data[0].delayed;//专业延期
		}
	});
	dwr.engine.setAsync(true);
}

//屏蔽表格的操作栏
function maskOperatorColumn() {
	$(".ui-jqgrid-btable").setGridParam().hideCol("operatorColumn").trigger("reloadGrid");
	$(".ui-jqgrid-btable").setGridParam().hideCol("operatporColumn").trigger("reloadGrid");
	$(".ui-jqgrid-btable").setGridWidth($("#MainArea").width()).setGridParam().trigger("reloadGrid");
	
}

//屏蔽新增、多选删除、反馈问题
function maskAddEditReport() {
	var span1 = $(".operationDiv").find("span");
	var span2 = $(".basicOperationDiv").find("span");
	var spans = span1.length != 0 ? span1 : span2;
	for(var i = 0; i < spans.length; i++) {
		$(spans[i]).remove();	
	}	
}

//屏蔽反馈问题
function maskReport() {
	var span1 = $(".operationDiv").find("span");
	var span2 = $(".basicOperationDiv").find("span");
	var spans = span1.length != 0 ? span1 : span2;
	for(var i = 0; i < spans.length; i++) {
		if($(spans[i]).html().indexOf("反馈") >= 0) {
			$(spans[i]).remove();
			if(spans.length > 3) {
				$(spans[i+1]).remove();
			}else {
				$(spans[i+1]).remove();
				$(spans[i-1]).remove();
			}	
		}
	}	
}

//屏蔽保存按钮和提交按钮
function maskSaveButton() {
	$("#rio").hide();
	var buttons = $(":button");
	$(buttons).each(function(index, val) {
		if($(val).attr("value").indexOf("保存") >= 0 || $(val).attr("value").indexOf("提 交") >= 0 ||
				$(val).html().indexOf("保存") >= 0 || $(val).html().indexOf("提交") >= 0) {
			$(val).remove();
		}
	});
	$(".u-file-uploadedFile-img").hide();
	$("a").each(function(index, val) {
		if($(val).html().indexOf("删除") >= 0) {
			$(val).remove();
		}else if($(val).attr("title") != undefined && $(val).attr("title").indexOf("点击下载") >= 0) {
			$(val).removeAttr("onclick");
			$(val).removeAttr("title");
			$(val).css({"text-decoration":"none", "cursor":"auto"});
		}
	});
}

//屏蔽自定义表格的操作
function maskTableOperate() {
	$("tr td input").attr("readonly","true");
	$("tr td input").focus(function() {
		$(this).blur();
	});
	$("tr td select").attr("disabled","true");
	$("tr td select").css({"color":"black","background-color":"white"});
	$("textarea").attr("disabled","true");
	$("textarea").css({"color":"black","background-color":"white"});
	$(".operatorFont").remove();
	$(".specialTd").remove();
	$(".table-td").removeAttr("onclick");
}

//得到当前时间（格式：2015-01-01）
function getTime(){
	var newTime=new Date();
	var year=newTime.getFullYear();
	var month=newTime.getMonth()+1;
	var day=newTime.getDate();
	var time=year+"-"+(month < 10 ? "0" + month : month)+"-"+ (day < 10 ? "0" + day : day);
	return time;
}