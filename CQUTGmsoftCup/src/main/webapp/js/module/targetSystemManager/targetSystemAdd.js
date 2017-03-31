var pageData={
	targetSystemName:null,
	standard :null,
};
//入口
$(document).ready(function(){
	loadSelect();
	$("#dialog-modal").createDialog( {
			height : 200,
			width : 350,
			resizable : false,
			modal : true,
			close : function(event) {
				if (!event.data) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					    var result ={};
					    result.standardVersionName=pageData.targetSystemName;
					    result.standardVersionB = pageData.standard;
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});
});

function loadSelect(){
	//DropDownBox.loadTargetSystem("#targetSystem");
	StandardVersionService.findProperties(function(data){
		var option = "<option value=\"\">不引用</option>";
		if(data){
			for(var i=0;i<data.length;i++){
				option += "<option value=\""+data[i].standardVersionID+"\">"+data[i].standardVersionName+"</option>";
			}
		}
		$("#targetSystem").empty();
		$("#targetSystem").append(option);
	});
}

//验证
userDialogValid.valid = function(){
	pageData.targetSystemName=$("#targetSystemName").val();
	pageData.standard = $("#targetSystem").val();
	if(pageData.targetSystemName=="" || pageData.targetSystemName==null){
		jAlert("请输入指标版本名称");
		return false;
	}
};
