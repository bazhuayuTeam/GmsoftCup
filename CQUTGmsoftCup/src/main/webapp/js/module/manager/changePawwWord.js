;

$(document).ready(function(){
	
	if($.browser.msie && '8.0' == $.browser.version){
		//修正IE8显示bug
		//d_height = 560;
	}
	
	$("#changePassWord").createDialog({
		height : d_height,
		width : 500,
		resizable : false,
		modal : true,
		close : function(event) {
			if (!event.data) {
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
				return;
			}else if (event.data.type == DialogUtil.EVENT_OK) {
				var passWord = $("#newPassWord_one").val();
				var oldPSW = $("#oldPassWord").val();
				var result = {oldPSW:oldPSW,newPSW:passWord};
				ChildDialogUtil.doClose(event.data.type, result);
			} else {
				DialogUtil.getTopWindow().WindowManager.hiddenFlowWindow();
			}
		}
	});
});

userDialogValid.valid = function(){
	var passWord_one = $("#newPassWord_one").val();
	var passWord_two = $("#newPassWord_two").val();
	if($("#oldPassWord").val() == ""){console("请输入旧密码！");return false;}
	if(passWord_one==""){console("请输入新密码！");return false;}
	if(passWord_one.length < 4 || passWord_one.length > 15){
		console("新密码的长度必须为4-15位！");
		return false;
	}
	if(passWord_two==""){console("请确认新密码！");return false;}
	if(passWord_one != passWord_two){console("两次输入密码不相符！");return false;}
	return true;
};

function checkNewPassWord(){
	if($("#newPassWord_one").val() == "" || $("#newPassWord_two").val() == ""){return;}
	if($("#newPassWord_one").val() != $("#newPassWord_two").val()){
	}else{
		$("#warning").text(" ");
	}
}

function console(str){
	jAlert(str,function(){},"错误");
}
