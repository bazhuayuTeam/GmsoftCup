var pageData={
	targetSystemName:null,
};
//入口
$(document).ready(function(){
	var data = ChildDialogUtil.getExchangeData();
	var id=data.id;
	$("#targetSysVersionName").val(data.name);
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
					    result.standarVsersionId = id;
					    result.standardVersionName=pageData.targetSystemName;
						ChildDialogUtil.doClose(DialogUtil.EVENT_OK,result);
					
				}
				else if (event.data.type == DialogUtil.EVENT_CANCEL) {
					DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				}
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		});
});

//验证
userDialogValid.valid = function(){
	pageData.targetSystemName=$("#targetSysVersionName").val();
	if(pageData.targetSystemName=="" || pageData.targetSystemName==null){
		jAlert("请输入指标版本名称");
		return false;
	}
};