
var pageData={
	id:null
}

$(document).ready(function() {
	if(ChildDialogUtil.getExchangeData){
		 data = ChildDialogUtil.getExchangeData();
		 getProcess();
		 getStandardVersion(data);
		 getData(data);
	}
	
	//创建控件
	$("#dialog-modal").createDialog({
		height :500,
		width : 400,
		resizable : false,
		modal : true,
		close : function(event) {

			if (!event.data) {
				ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL,{});
				return;
			}
				
			if (event.data.type == DialogUtil.EVENT_OK
					&& (Page.Data.exchangeData.showFlag == undefined || !Page.Data.exchangeData.showFlag)) {
	
					var result = {
						gameName:$("#name").val(),
						year:$("#year").val(),
						startTime:$("#startTime").val(),
						endTime:$("#endTime").val()
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

function getData(id){
	var properties=["ct_codeTableCode","gsd_gameTime","sv_standardVersionID","gsd_checkStartTime","gsd_checkEndTime","gsd_works","gsd_startTime","gsd_endTime","gsd_fileID"];
	GameStepService.findMapByPropertiesQuick(properties
	,"gsd.parentID='"+id+"'",true,function (data){
		if(data&&data.length>0){
			var info=data[0];
			setTimeout(function (){
				$("#processID").val(info.ct_codeTableCode);
				$("#standardVersionName").val(info.sv_standardVersionID);
			},500);
			$("#gameTime").val(DateUtil.dateDiffMills(info.gsd_gameTime));
			$("#checkStartTime").val(DateUtil.dateDiffMills(info.gsd_checkStartTime));
			$("#checkEndTime").val(DateUtil.dateDiffMills(info.gsd_checkEndTime));
			$("#works").val(info.gsd_works);
			$("#startTime").val(DateUtil.dateDiffMills(info.gsd_startTime));
			$("#endTime").val(DateUtil.dateDiffMills(info.gsd_endTime));
			$("#fileID").attr("data-id",info.gsd_fileID);
		}else{
			jAlert("加载数据失败");
		}
	});
}

function getProcess(){
	CodeTableService.findMapByPropertiesQuick(["codeTableCode","codeTableName"],"parentCode='task'",false,function (data){
		var processHTML="";
		for(var i=0,len=data.length;i<len;i++){
			processHTML+=("<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>");
		}
		$("#processID").html(processHTML);
	});
}

function getStandardVersion(id){
	StandardVersionService.findMapByPropertiesQuick(["standardVersionID","standardVersionName"],"",false,function (data){
		var standardVersionHTML="";
		for(var i=0,len=data.length;i<len;i++){
			standardVersionHTML+=("<option value='"+data[i].standardVersionID+"'>"+data[i].standardVersionName+"</option>");
		}
		$("#standardVersionName").html(standardVersionHTML);
	});
}

//验证输入框是否为空
userDialogValid.valid = function(){
	var name=$("#name").val();
	var year=$("#year").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	if(name==""){
		jAlert("请输入大赛名称");
		$("#name").focus();
		return false;
	}
	else if(year==""){
		jAlert("请输入年份");
		$("#year").focus();
		return false;
	}
	else if(startTime==""){
		jAlert("请输入报名开始时间");
		$("#startTime").focus();
		return false;
	}
	else if(endTime==""){
		jAlert("请输入报名截止时间");
		$("#endTime").focus();
		return false;
	}
}
