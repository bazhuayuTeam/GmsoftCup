
$(document).ready(function() {
	initGame();
	initType();
	
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
						gameID:$("#game").val(),
						gameStep:$("#type").val(),
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

function initGame(){
	var html="";
	dwr.engine.setAsync(false);
	GameService.findMapByPropertiesQuick(['gameId','gameName'],"1=1 order by startTime",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].gameId+"'>"+data[i].gameName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#game").append(html);
}

function initType(){
	var html="";
	var id=$("#game").val();
	$("#type").empty();
	dwr.engine.setAsync(false);
	CodeTableService.findMapByPropertiesQuick(['codeTableName',"codeTableCode"],
		"codeTableCode like 'gameStep%' and hasChild='0' and codeTableCode not in(select distinct gameStep from gameStep where gameID='"+id+"')",false,function(data){
		for(var i=0;i<data.length;i++){
			html+="<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>"
		}
	})
	dwr.engine.setAsync(true);
	$("#type").append(html);
}

userDialogValid.valid = function(){
	var type =$("#type").val();
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	
	var startTime1 = DateUtil.DateToMS($.trim($('#startTime').val()));
	var endTime1 = DateUtil.DateToMS($.trim($('#endTime').val()));
	if(type===""||!type){
		jAlert("请选择竞赛类别");
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