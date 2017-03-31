var data = ChildDialogUtil.getExchangeData();
var pageData={
	start:null,
	end:null
}
userDialogValid.valid = function(){
	return valid(setData());
}
$(document).ready(function() {
	initVersion();
	FormUtil.createComponent( [ "endTime" ],"timepicker",load);
	FormUtil.createComponent( [ "startTime" ],"timepicker",load);
		getTime();
		$("#dialog-modal").createDialog( {
			height : 350,
			width : 380,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					    var result = getResult();   
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});

	});

//验证输入框是否为空
userDialogValid.valid = function(){
	var startTime = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime = DateUtil.DateToMS($.trim($('#endTime').val()));
	if(startTime===""||!startTime){
		jAlert("请输入评审开始时间!");
		return false;
	}
	if(endTime===""||!endTime){
		jAlert("请输入评审结束时间!");
		return false;
	}
	if(startTime>endTime){
		jAlert("结束时间不能小于开始时间!");
		return false;
	}
	if(startTime<pageData.end){
		jAlert("审核开始时间不能提前于文档提交结束时间"+DateUtil.dateDiffMills(pageData.end)+"!");
		$("#startTime").focus();
		return false;
	}
}

function load(){
	$("#gameName").val(data.gameName);
	$('#gameStep').val(data.gameStep);
	$('#gameMission').val(data.rate);
	$('#startTime').val(DateUtil.dateDiffMills(data.checkStartTime));
	$('#endTime').val(DateUtil.dateDiffMills(data.checkEndTime));
	
}
//获取最后结果
function getResult(){
	var startTime = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime = DateUtil.DateToMS($.trim($('#endTime').val()));
	var version = $.trim($('#version').val());
	var data={};
	data.checkStartTime=startTime;
	data.checkEndTime=endTime;
	data.standardVersionID=version;
	return data;
}

//初始化版本列表
function initVersion(){
	StandardVersionService.findMapByPropertiesQuick(['standardVersionID',"standardVersionName"],
		"1=1",false,function(data){
		var html="";
		for(var i=0;i<data.length;i++){
			if(data[i].standardVersionID!=this.data.version){			
				html+="<option value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>";
			}else{
				html+="<option selected='selected' value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>";
			}
		}
		$("#version").append(html);
	})
}

function getTime(){
	GameStepDetailService.findMapByPropertiesQuick(['startTime','endTime'],
		"gameStepDetailID='"+data.id+"'",false,function(data){
		if(data.length>0){
			pageData.start=data[0].startTime;
			pageData.end=data[0].endTime;
		}
	})
}