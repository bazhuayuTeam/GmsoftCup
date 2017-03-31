
var pageData={
	id:null,
	startTime:null,
	endTime:null,
	gameStep:null,
	start:0,
	end:0,
	theId:null
}

$(document).ready(function() {
	if(ChildDialogUtil.getExchangeData){
		 data = ChildDialogUtil.getExchangeData();
		 pageData.id=data.code;
		 pageData.startTime=DateUtil.dateDiffMills(data.startTime);
		 pageData.endTime=DateUtil.dateDiffMills(data.endTime);
		 pageData.gameStep=data.gameStep;
		 pageData.theId=data.theId;
	}
	initType();
	initVersion();
	getTime();
	FormUtil.createComponent( [ "endTime" ],"timepicker",load);
	FormUtil.createComponent( [ "startTime" ],"timepicker",load);
	//创建控件
	$("#dialog-modal").createDialog({
		height : 310,
		width : 400,
		resizable : false,
		modal : true,
		close : function(event) {
			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK) {
					var result = {
					//	gameID:$("#game").val(),
						type:$("#type").val(),
						startTime:DateUtil.DateToMS($.trim($("#startTime").val())),
						endTime:DateUtil.DateToMS($.trim($("#endTime").val())),
					//	standardVersionID:$("#version").val()
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
			} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					return;
			} else {
					ChildDialogUtil.doClose(event.data.type, {});
			}
		}
	});
});


function load(){
	 $("#startTime").val(pageData.startTime);
	$("#endTime").val(pageData.endTime);
}


function initType(){
	var html="";
	dwr.engine.setAsync(false);
	CodeTableService.findMapByPropertiesQuick(['codeTableName',"codeTableCode"],
		"codeTableCode like 'task%' and hasChild='0' " +
		"and codeTableCode not in(select distinct type from " +
		"gameStepDetail where gameStepId='"+pageData.gameStep+"' and gameStepDetailID!='"+pageData.theId+"')",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#type").append(html);
	if(pageData.code!=""||pageData.code!=null){
		$("#type").val(pageData.code);
	}
}

function initVersion(){
	var html="";
	dwr.engine.setAsync(false);
	StandardVersionService.findMapByPropertiesQuick(['standardVersionID',"standardVersionName"],
		"1=1",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#version").append(html);
}

//验证输入框是否为空
userDialogValid.valid = function(){
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	
	var startTime1 = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime1 = DateUtil.DateToMS($.trim($('#endTime').val()));
	if($.trim($("#type").val())==""){
		jAlert("请选择竞赛任务");
		return false;
	}
	else if(startTime==""){
		jAlert("请输入提交文档开始时间");
		$("#startTime").focus();
		return false;
	}
	else if(endTime==""){
		jAlert("请输入提交文档截止时间");
		$("#endTime").focus();
		return false;
	}
	else if(startTime1>endTime1){
		jAlert("结束时间不能小于开始时间!");
		return false;
	}
	else if(startTime1<pageData.start){
		jAlert("提交文档开始时间不能提前于大赛开始时间"+DateUtil.dateDiffMills(pageData.start)+"!");
		$("#startTime").focus();
		return false;
	}
	else if(endTime1>pageData.end){
		jAlert("提交文档结束时间不能晚于大赛结束时间"+DateUtil.dateDiffMills(pageData.end)+"!");
		$("#endTime").focus();
		return false;
	}
}

function getTime(){
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['startTime','endTime'],
		"gameId in(select distinct gameId from gameStep where gameStepId='"+pageData.gameStep+"')",false,function(data){
		if(data.length>0){
			pageData.start=data[0].startTime;
			pageData.end=data[0].endTime;
		}
	})
	dwr.engine.setAsync(true);
}