var academy = "";
var major = "";
var education = "";
var profession = "";
var isVia = false;

$(document).ready(function(){
	init();
});

//用户信息
function init() {
	dwr.engine.setAsync(false);
	UserService.findUserAction(userId,function(data) {
		$("#userID").val(data[0].userID);
		$("#name").val(data[0].name);
		$("#email").val(data[0].email);
		$("#phone").val(data[0].phone);
		$("#QQ").val(data[0].QQ);
		academy = data[0].academy;
		major = data[0].major;
		education = data[0].education;
		profession = data[0].profession;
		findAcademic();
	});
	dwr.engine.setAsync(true);
}

//查找所有学院
function findAcademic() {
	UserService.findAcademic(function(data) {
		for(var i=0;i<data.length;i++) {
			if(academy == data[i].academicID) {
				$("#academy").append("<option value='"+data[i].academicID+
						"' selected = 'selected'>"+data[i].academicName+"</option>");
			} else {
				$("#academy").append("<option value='"+data[i].academicID+"'>"+data[i].academicName+"</option>");
			}
		}
		if(roleType == 0){
			findDiscipline();
			findEducation();
		} else if(roleType == 1) {
			findProfession();
		}
	});
}

//查找所有专业
function findDiscipline() {
	var selectAcademy = $("#academy").val();
	UserService.findDiscipline(selectAcademy,function(data) {
		$("#major").html(""); 
		for(var i=0;i<data.length;i++) {
			if(major == data[i].disciplineID) {
				$("#major").append("<option value='"+data[i].disciplineID+
						"' selected = 'selected'>"+data[i].disciplineName+"</option>");
			} else {
				$("#major").append("<option value='"+data[i].disciplineID+"'>"+data[i].disciplineName+"</option>");
			}
		}
	});
}

//查找所有学历
function findEducation() {
	UserService.findEducation(function(data) {
		for(var i=0;i<data.length;i++) {
			if(education == data[i].codeTableCode) {
				$("#education").append("<option value='"+data[i].codeTableCode+
						"' selected = 'selected'>"+data[i].codeTableName+"</option>");
			} else {
				$("#education").append("<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>");
			}
		}
	});
}

//查找所有职称
function findProfession() {
	UserService.findProfession(function(data) {
		for(var i=0;i<data.length;i++) {
			if(profession == data[i].codeTableCode) {
				$("#profession").append("<option value='"+data[i].codeTableCode+
						"' selected = 'selected'>"+data[i].codeTableName+"</option>");
			} else {
				$("#profession").append("<option value='"+data[i].codeTableCode+"'>"+data[i].codeTableName+"</option>");
			}
		}
	});
}

//修改信息
function saveUser() {
	var User = {};
	User.name = $("#name").val();
	User.academy = $("#academy").val();
	User.email = $("#email").val();
	User.phone = $("#phone").val();
	User.QQ = $("#QQ").val();
	
	if(User.name == null || User.name == "") {
		isVia = false;
	}
	
	if(User.email != null && User.email != ""){
		if(!CheckMail(User.email)) {
			isVia = false;
		}
	}
	
	if(User.phone != null && User.phone != ""){
		if(!CheckPhone(User.phone)) {
			isVia = false;
		}
	}
	
	if(User.QQ != null && User.QQ != ""){
		if(!CheckQQ(User.QQ)) {
			isVia = false;
		}
	}
	if(isVia) { 
		var userID = $("#userID").val();
		if(roleType == 0){
			User.major = $("#major").val();
			User.education = $("#education").val();
		} else if(roleType == 1) {
			User.profession = $("#profession").val();
		}
		UserService.updateEntity(User,"userID = '"+userID+"'",function(data) {
			if(data == true) {
				alert("修改成功");
			}
			else{
				alert("修改失败!");
			}
		});
	}
}

function focusFun(index){
	switch (index) {
		case 1:
			changeBorderColor1("#name");
			$("#name_error").hide();
			break;
		case 2:
			changeBorderColor1("#email");
			$("#email_error").hide();
			break;
		case 3:
			changeBorderColor1("#phone");
			$("#phone_error").hide();
			break;
		case 4:
			changeBorderColor1("#QQ");
			$("#QQ_error").hide();
			break;
	}
}

function mouseOut(index){
	switch (index) {
		case 1:
			var userId = $("#name").val();
			if(userId == null||userId == ""){
				changeBorderColor("#name");
				setWran("#name_error","请输入姓名");
				isVia = false;
				return;
			}else{
				isVia = true;
			}
			break;
		case 2:
			var email = $("#email").val();
			if(email == null||email == ""){
				if(roleType == 0){
					changeBorderColor("#email");
					setWran("#email_error","请输入邮箱");
					isVia = false;
					return;
				}else{
					isVia = true;
				}
			}else if(!CheckMail(email)){
				changeBorderColor("#email");
				setWran("#email_error","邮箱格式错误");
				isVia = false;
				return;
			}else{
				isVia = true;
			}
			break;
		case 3:
			var phone = $("#phone").val();
			if(phone == null||phone == ""){
				if(roleType == 0){
					changeBorderColor("#phone");
					setWran("#phone_error","请输入电话号码");
					isVia = false;
					return;
				}else{
					isVia = true;
				}
			}else if(!CheckPhone(phone)){
				changeBorderColor("#phone");
				setWran("#phone_error","无效的电话号码");
				isVia = false;
				return;
			}else{
				isVia = true;
			}
			break;
		case 4:
			var QQ = $("#QQ").val();
			if(QQ == null||QQ == ""){
				if(roleType == 0){
					changeBorderColor("#QQ");
					setWran("#QQ_error","请输入QQ");
					isVia = false;
					return;
				}else{
					isVia = true;
				}
			}else if(!CheckQQ(QQ)){
				changeBorderColor("#QQ");
				setWran("#QQ_error","无效的QQ");
				isVia = false;
				return;
			}else{
				isVia = true;
			}
			break;
	}
}

function setWran(selecter,data){
	$(selecter).show();
	$(selecter).html("<img src=\"images/common/warn.png\" class=\"warn_img\"/><label class=\"warn\" style=\"vertical-align: baseline;padding-left: 0px;\">" + data + "</label>");
}

//检验邮箱格式
function CheckMail(mail) {
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(mail)) {
		return true;
	} else {
		return false;
	}
}

//检验电话格式
function CheckPhone(phone) {
	var filter = /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/;
	if (filter.test(phone)) {
		return true;
	} else {
		return false;
	}
}

//检验QQ格式
function CheckQQ(QQ) {
	var filter = /^[\-\+]?\d+$/;
	if (filter.test(QQ)) {
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