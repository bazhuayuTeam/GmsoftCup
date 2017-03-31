
$(document).ready(function() {
	$('#name').Watermark("请输入大赛名称", "#8f8f8f");
	$("#year").Watermark("例如:2016", "#8f8f8f");
	
	
	FormUtil.createComponent( [ "endTime" ],"timepicker");
	FormUtil.createComponent( [ "startTime" ],"timepicker");

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

//验证输入框是否为空
userDialogValid.valid = function(){
	var name=$("#name").val();
	var year=$("#year").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	
	var startTime1 = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime1 = DateUtil.DateToMS($.trim($('#endTime').val()));
	if(name==""||name=="请输入大赛名称"){
		jAlert("请输入大赛名称");
		$("#name").focus();
		return false;
	}
	else if(year==""||year=="例如:2016"){
		jAlert("请输入年份");
		$("#year").focus();
		return false;
	}
	else if(startTime==""||startTime=="请输入报名开始时间"){
		jAlert("请输入报名开始时间");
		$("#startTime").focus();
		return false;
	}
	else if(endTime==""||endTime=="请输入报名截止时间"){
		jAlert("请输入报名截止时间");
		$("#endTime").focus();
		return false;
	}
	else if(startTime1>endTime1){
		jAlert("结束时间不能小于开始时间!");
		return false;
	}
}