;

var schoolYearID = "";
var operatorID = "";
//初始化数据
Page.initComponent = function() {
	Page.Util.callOver = true;
};

$(document).ready(function() {
	FormUtil.createComponent( [ "major" ],{});
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();
	//创建控件
		$("#dialog-modal").createDialog( {
			height : 350,
			width : 550,
			resizable : false,
			modal : true,
			title : "编辑",
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
					var email = $("#email").val();
					var mark = $("#mark").val();
					var result={
						expertID:zgh,
						name:name,
						department:department,
						education:education,
						telephone:phone,
						email:email,
						birth:major
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK, result);
				} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					ChildDialogUtil.doClose(event.data.type, {});
				} else {
					ChildDialogUtil.doClose(event.data.type, {});
				}
			}
		});
		initData();
});


function initData() {
	$("#ZGH").val(Page.Data.exchangeData.ZGH != "null"? Page.Data.exchangeData.ZGH :"");
	$("#name").val(Page.Data.exchangeData.name != "null"? Page.Data.exchangeData.name : "");
	$("#department").val(Page.Data.exchangeData.department != "null"? Page.Data.exchangeData.department : "");
	$("#education").val(Page.Data.exchangeData.education != "null"? Page.Data.exchangeData.education : "");
	$("#major").val(Page.Data.exchangeData.major != "null" ?Page.Data.exchangeData.major :"");
	$("#phone").val(Page.Data.exchangeData.phone != "null" ? Page.Data.exchangeData.phone : "" );
}
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