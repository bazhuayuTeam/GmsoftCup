
var pageData={
	id:null,
	startTime:null,
	endTime:null
}

$(document).ready(function() {
	if(ChildDialogUtil.getExchangeData){
		 data = ChildDialogUtil.getExchangeData();
		 $("#name").val(data.name);
		 $("#year").val(data.year);
		 pageData.startTime=DateUtil.dateDiffMills(data.startTime);
		 pageData.endTime=DateUtil.dateDiffMills(data.endTime);
	}
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
						gameName:$("#name").val(),
						year:$("#year").val(),
						startTime:DateUtil.DateToMS($.trim($('#startTime').val())),
						endTime:DateUtil.DateToMS($.trim($('#endTime').val()))
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

//验证输入框是否为空
userDialogValid.valid = function(){
	var name=$("#name").val();
	var year=$("#year").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	
	var startTime1 = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime1 = DateUtil.DateToMS($.trim($('#endTime').val()));
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
	else if(startTime1>endTime1){
		jAlert("结束时间不能小于开始时间!");
		return false;
	}
}

function load(){
	 $("#startTime").val(pageData.startTime);
	$("#endTime").val(pageData.endTime);
}