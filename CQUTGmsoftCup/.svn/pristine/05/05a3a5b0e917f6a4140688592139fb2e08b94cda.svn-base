;

//初始化数据
Page.initComponent = function() {
	Page.Util.callOver = true;
};


$(document).ready(function() {
	FormUtil.createComponent( [ "major" ],{});
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();
	dwr.engine.setAsync(false);
	ExpertService.getRandomNumber(function(data){
		if(data){
			$("#ZGH").val(data);
			$("#ZGH").attr("disabled",true);
		}
		else{
			return false;
		}
	});
	dwr.engine.setAsync(true);

	dwr.engine.setAsync(true);
	//创建控件
		$("#dialog-modal").createDialog( {
			height : 350,
			width : 450,
			resizable : false,
			modal : true,
			title : "新增评委",
			winMode : ChildDialogUtil.getWinMode(),
			close : function(event) {
				if (!event.data) {
					ChildDialogUtil.doClose(DialogUtil.EVENT_CANCEL, {});
					return;
				}
				if (event.data.type == DialogUtil.EVENT_OK) {
					var zgh = $("#ZGH").val();
					var name = $("#name").val();
					var department = $("#department").val();
					var education = $("#education").val();
					var major = $("#major").val();				
					var phone = $("#phone").val();
					var result={
						expertID:zgh,
						name:name,
						department:department,
						education:education,
						birth:major,
						telephone:phone
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK, result);
				} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					ChildDialogUtil.doClose(event.data.type, {});
				} else {
					ChildDialogUtil.doClose(event.data.type, {});
				}
			}
		});
});


//验证
userDialogValid.valid = function(){
	if($("#name").val() == "") {
		jAlert("请输入姓名");
		return false;
	}
	if($("#department").val() == "") {
		jAlert("请输入部门");
		return false;
	}
	
	return true;

};














