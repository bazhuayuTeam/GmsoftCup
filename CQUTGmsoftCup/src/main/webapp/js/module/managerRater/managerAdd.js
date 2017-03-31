;

//初始化数据
Page.initComponent = function() {
	Page.Util.callOver = true;
};

var wno = {};	// 已经添加过的职工号

$(document).ready(function() {
	
	Page.Data.exchangeData = ChildDialogUtil.getExchangeData();
	wno =  ChildDialogUtil.getExchangeData().wno;

	dwr.engine.setAsync(true);
	//创建控件
		$("#dialog-modal").createDialog( {
			height : 600,
			width : 550,
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
					var name = $("#name").val();
					//var login = $("#login").val();
					var department = $("#department").val();
					var education = $("#education").val();
					var major = $("#major").val();
					var phone = $("#phone").val();
					//var email = $("#email").val();
					var birth = $("#birthday").val();
					var result={
						name:name,
						birth:birth,
						telephone:phone,
						major:major,
						education:education,
						department:department,
						//email:email
					};
					ChildDialogUtil.doClose(DialogUtil.EVENT_OK, result);
				} else if (event.data.type == DialogUtil.EVENT_ERROR) {
					ChildDialogUtil.doClose(event.data.type, {});
				} else {
					ChildDialogUtil.doClose(event.data.type, {});
				}
			}
		});
		
	setEducationToSelect();
});

// 从码表中读取学历并设置到下拉框
function setEducationToSelect(){
	DropDownBox.loadEducationBackground("#education", true);
}

//验证
userDialogValid.valid = function(){
	
	if($("#name").val() == "") {
		jAlert("请输入姓名");
		return false;
	}
	if($("#login").val() == "") {
		jAlert("请输入登录名");
		return false;
	}
	if($("#birthday").val() == "") {
		jAlert("请输入出生年月");
		return false;
	}
	if($("#phone").val() == "") {
		jAlert("请输入电话号码");
		return false;
	}
	
	return true;

};