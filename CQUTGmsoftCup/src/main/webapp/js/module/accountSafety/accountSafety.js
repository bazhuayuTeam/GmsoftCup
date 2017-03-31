
//添加水印
var addWatermark = function() {
	$("#nowPassword").Watermark("请输入当前密码", "#369");
	$("#newPassword").Watermark("请输入新的密码", "#369");
	$("#secondPassword").Watermark("请再次输入新的密码", "#369");
};

$(document).ready(function(){
});

//修改密码
function updatePassword() {
	isVia = true;
	var nowPassword = $("#nowPassword").val();
	var newPassword = $("#newPassword").val();
	var secondPassword = $("#secondPassword").val();
		
	if(!isPassword()||nowPassword.length < 8||newPassword.length < 8||secondPassword.length < 8) {
		isVia = false;
	}
	
	if(newPassword != secondPassword) {
		isVia = false;
	}
	
	if(isVia) {
		updateOldPassword();
	}
}

function isPassword() {
	var result = 0;
	var nowPassword = $("#nowPassword").val();
	dwr.engine.setAsync(false);
	UserService.isPassword(userId,nowPassword,function(data){
		if(data == true) {
			result = 1;
		} 
	});
	dwr.engine.setAsync(true);
	if(result == 1) {
		return true;
	}
	return false;
}

//保存
function updateOldPassword() {
	var nowPassword = $("#nowPassword").val();
	var newPassword = $("#newPassword").val();
	var secondPassword = $("#secondPassword").val();
	var nowPassword = $("#nowPassword").val();
	var newPassword = $("#newPassword").val();
	var secondPassword = $("#secondPassword").val();
	var User = {};
	User.password = newPassword;
	User.userID = userId;
	UserService.updatePassword(User,secondPassword,function(data) {
		if(data == true) {
			spanHide();
			mWin.ok("修改成功！");
		} else {
			alert("修改失败！");
		}
	});
}

//隐藏
function focusFun(index){
	switch (index) {
		case 1:
			changeBorderColor1("#nowPassword");
			$("#nowPassword_error").hide();
			break;
		case 2:
			changeBorderColor1("#newPassword");
			$("#newPassword_error").hide();
			break;
		case 3:
			changeBorderColor1("#secondPassword");
			$("#secondPassword_error").hide();
			break;
	}
}

//验证
function mouseOut(index){
	switch (index) {
		case 1:
			var nowPassword = $("#nowPassword").val();
			if(!isPassword()) {
				changeBorderColor("#nowPassword");
				setWran("#nowPassword_error","当前密码错误");
				isVia = false;
				return;
			}else if(nowPassword == null||nowPassword == ""){
				changeBorderColor("#nowPassword");
				setWran("#nowPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else if(nowPassword.length < 8 || nowPassword.length > 16){
				changeBorderColor("#nowPassword");
				setWran("#nowPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else{
				isVia = true;
				focusFun(1);
			}
			break;
		case 2:
			var newPassword = $("#newPassword").val();
			if(newPassword == null||newPassword == ""){
				changeBorderColor("#newPassword");
				setWran("#newPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else if(newPassword.length < 8 || newPassword.length > 16){
				changeBorderColor("#newPassword");
				setWran("#newPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else{
				isVia = true;
				focusFun(2);
				focusFun(3);
			}
			break;
		case 3:
			var newPassword = $("#newPassword").val();
			var secondPassword = $("#secondPassword").val();
			if(secondPassword == null||secondPassword == ""){
				changeBorderColor("#secondPassword");
				setWran("#secondPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else if(secondPassword.length < 8 || secondPassword.length > 16){
				changeBorderColor("#secondPassword");
				setWran("#secondPassword_error","请输入8-16位密码");
				isVia = false;
				return;
			}else if(newPassword != secondPassword){
				changeBorderColor("#secondPassword");
				setWran("#secondPassword_error","两次输入的密码不一致");
				isVia = false;
				return;
			}else{
				isVia = true;
				focusFun(3);
				focusFun(2);
			}
			break;
	}
}

function spanHide() {
	focusFun(1);
	focusFun(2);
	focusFun(3);
}
function setWran(selecter,data){
	$(selecter).show();
	//$(selecter).css("display","ruby");
	$(selecter).html("<img src=\"images/common/warn.png\" class=\"warn_img\"/><label class=\"warn\" style=\"vertical-align: baseline;padding-left: 0px;\">" + data + "</label>");
}

function CheckMail(mail) {
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(mail)) {
		return true;
	} else {
		return false;
	}
}

function changeBorderColor(selecter){
	$(selecter).css("border","1px solid #fa2e2e");
}

function changeBorderColor1(selecter){
	$(selecter).css("border","1px solid #cbcbcb");
}