var type = "0";
			
$(function(){
	setWatermark();
	
	$(':input').labelauty();
	
	initAcademySelect();
	initEducationSelect();
	
	//window.parent.toRegister();
});

function setWatermark(){
	$("#userId").Watermark("请输入账号(学号/职工号)","#8b8b8b");
	$("#name").Watermark("请输入姓名","#8b8b8b");
	$("#email").Watermark("请输入邮箱","#8b8b8b");
	$("#QQ").Watermark("请输入QQ","#8b8b8b");
	$("#phone").Watermark("请输入电话号码","#8b8b8b");
}

function initAcademySelect(){
	AcademicService.findByProperties(type,function(data){
		if(data){
			optionsStr = "";
			for(var i in data) {
				optionsStr+= "<option value='" + data[i]["academicID"] + "'>" + data[i]["academicName"] + "</option>";
			}
			$("#academy").empty();
			$("#academy").html(optionsStr);
			if(type=="0"){
				initDisciplineSelect();
			}
		}
	});
}

function initDisciplineSelect(){
	var academy = $("#academy").val();
	DisciplineService.findByProperties(academy,function(data){
		if(data){
			optionsStr = "";
			for(var i in data) {
				optionsStr+= "<option value='" + data[i]["disciplineID"] + "'>" + data[i]["disciplineName"] + "</option>";
			}
			$("#major").empty();
			$("#major").html(optionsStr);
		}
	});
}

function initEducationSelect(){
	CodeTableService.findEducation(["education"],function(data){
		if(data){
			optionsStr = "";
			for(var i in data) {
				optionsStr+= "<option value='" + data[i]["codeTableCode"] + "'>" + data[i]["codeTableName"] + "</option>";
			}
			$("#education").empty();
			$("#education").html(optionsStr);
		}
	});
}

function initProfessionSelect(){
	CodeTableService.findEducation(["profession"],function(data){
		if(data){
			optionsStr = "";
			for(var i in data) {
				optionsStr+= "<option value='" + data[i]["codeTableCode"] + "'>" + data[i]["codeTableName"] + "</option>";
			}
			$("#profession").empty();
			$("#profession").html(optionsStr);
		}
	});
}

function link(){
	window.parent.childLink("register");
}

function register(){
	if(validate()){
		var param = getResult();
		UserService.register(param,function(data){
			if(data == "success"){
				alert("注册成功");
				window.parent.childLink("register");
			}else if(data = "exist"){
				alert("该用户已存在");
			}
		});
	}
}

function validate(){
	var userID = $("#userId").val();
	var name = $("#name").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	var email = $("#email").val();
	var QQ = $("#QQ").val();
	var phone = $("#phone").val();
	
	if(userID == null||userID == ""||userId=="请输入学号"){
		alert("请输入账号(学号或职工号)");
		return false;
	}
	if(name == null||name == ""||name=="请输入姓名"){
		alert("请输入姓名");
		return false;
	}
	if(password1 == null||password1 == ""){
		alert("请输入密码");
		return false;
	}
	if(password2 == null||password2 == ""){
		alert("请确认密码");
		return false;
	}
	if(password1 != password2){
		alert("两次密码不一致，请重新输入");
		return false;
	}
	if(type == "0"){
		if(email == null||email == ""||email=="请输入邮箱"){
			alert("请输入邮箱");
			return false;
		}
		if(QQ == null||QQ == ""||QQ=="请输入QQ"){
			alert("请输入QQ");
			return false;
		}
		if(phone == null||phone == ""||phone=="请输入电话号码"){
			alert("请输入电话号码");
			return false;
		}
	}
	return true;
}

function getResult(){
	var userID = $("#userId").val();
	var name = $("#name").val();
	var password = $("#password1").val();
	var type = $("input:radio[name='type']:checked").val();
	var email = $("#email").val();
	var QQ = $("#QQ").val();
	var academy = $("#academy").val();
	var major = $("#major").val();
	var profession = $("#profession").val();
	var education = $("#education").val();
	var phone = $("#phone").val();
	var data = new Array(10);
	data[0] = userID;
	data[1] = name;
	data[2] = password;
	data[3] = type;
	if(type == "0"){
		data[4] = email;
		data[5] = QQ;
		data[6] = phone;
		data[7] = academy;
		data[8] = major;
		data[9] = education;
	}else{
		if(email == "请输入邮箱"){
			data[4] = "";
		}else{
			data[4] = email;
		}
		if(QQ == "请输入QQ"){
			data[5] = "";
		}else{
			data[5] = QQ;
		}
		if(phone == "请输入电话号码"){
			data[6] = "";	
		}else{
			data[6] = phone;	
		}
		data[7] = academy;
		data[8] = profession;
	}
	return data;
}

function focusFun(index){
	switch (index) {
		case 1:
			changeBorderColor1("#userId");
			$("#userId_error").hide();
			break;
		case 2:
			changeBorderColor1("#name");
			$("#name_error").hide();
			break;
		case 3:
			changeBorderColor1("#password1");
			$("#password1_error").hide();
			break;
		case 4:
			changeBorderColor1("#password2");
			$("#password2_error").hide();
			break;
		case 5:
			changeBorderColor1("#email");
			$("#email_error").hide();
			break;
		case 6:
			changeBorderColor1("#phone");
			$("#phone_error").hide();
			break;
		case 7:
			changeBorderColor1("#QQ");
			$("#QQ_error").hide();
			break;
	}
}

function mouseOut(index){
	switch (index) {
		case 1:
			var userId = $("#userId").val();
			if(userId == null||userId == ""){
				changeBorderColor("#userId");
				setWran("#userId_error","请输入账号(学号或职工号)");
			}
			break;
		case 2:
			var name = $("#name").val();
			if(name == null||name == ""){
				changeBorderColor("#name");
				setWran("#name_error","请输入姓名");
			}
			break;
		case 3:
			var password1 = $("#password1").val();
			if(password1 == null||password1 == ""){
				changeBorderColor("#password1");
				setWran("#password1_error","请输入8-16位密码");
				return;
			}else if(password1.length < 8){
				changeBorderColor("#password1");
				setWran("#password1_error","请输入8-16位密码");
				return;
			}					
			break;
		case 4:
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			if(password2 == null||password2 == ""){
				changeBorderColor("#password2");
				setWran("#password2_error","请输入8-16位密码");
				return;
			}else if(password2.length < 8){
				changeBorderColor("#password2");
				setWran("#password2_error","请输入8-16位密码");
				return;
			}else if(password1 != password2){
				changeBorderColor("#password2");
				setWran("#password2_error","两次输入的密码不一致");
				return;
			}
			break;
		case 5:
			var email = $("#email").val();
			if(email == null||email == ""){
				if(type == "0"){
					changeBorderColor("#email");
					setWran("#email_error","请输入邮箱");
					return;
				}
			}else if(!CheckMail(email)){
				changeBorderColor("#email");
				setWran("#email_error","邮箱格式错误");
				return;
			}
			break;
		case 6:
			var phone = $("#phone").val();
			if(phone == null||phone == ""){
				if(type == "0"){
					changeBorderColor("#phone");
					setWran("#phone_error","请输入电话号码");
					return;
				}
			}else if(!checkPhone(phone)){
				changeBorderColor("#phone");
				setWran("#phone_error","请正确输入电话号码");
				return;
			}
			break;
		case 7:
			var QQ = $("#QQ").val();
			if(QQ == null||QQ == ""){
				if(type == "0"){
					changeBorderColor("#QQ");
					setWran("#QQ_error","请输入QQ");
					return;
				}
			}else if(!CheckQQ(QQ)){
				changeBorderColor("#QQ");
				setWran("#QQ_error","请正确输入QQ");
				return;
			}
			break;
	}
}

function setWran(selecter,data){
	$(selecter).show();
	$(selecter).html("<img src=\"images/common/warn.png\" class=\"warn_img\"/><label class=\"warn\">" + data + "</label>");
}

function CheckQQ(QQ) {
	var filter = /^\d{5,10}$/;
	if (filter.test(QQ)) {
		return true;
	} else {
		return false;
	}
}

function checkPhone(phone){
	var filter = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
	if (filter.test(phone)) {
		return true;
	} else {
		return false;
	}
}

function CheckMail(mail) {
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(mail)) {
		return true;
	} else {
		return false;
	}
}

function changeToTeacher(){
	$("#acadmic").html("学院(单位):&nbsp;");
	$("#major_tr").hide();
	$("#education_tr").hide();
	$("#profession_tr").show();
	$(".remind1").hide();
	$(".error_span").hide();
	changeBorderColor1(".input");
	$(".input").val("");
	setWatermark();
	$("#userId").Watermark("请输入职工号","#8b8b8b");
	initAcademySelect();
	initProfessionSelect();
	type = "1";
}

function changeToStudent(){
	$("#acadmic").html("学院:&nbsp;");
	$("#major_tr").show();
	$("#education_tr").show();
	$("#profession_tr").hide();
	$(".remind1").css("display","ruby");
	$(".error_span").hide();
	changeBorderColor1(".input");
	initAcademySelect();
	initEducationSelect();
	$(".input").val("");
	setWatermark();
	$("#userId").Watermark("请输入学号","#8b8b8b");
	type = "0";
}

function changeBorderColor(selecter){
	$(selecter).css("border","1px solid #fa2e2e");
}

function changeBorderColor1(selecter){
	$(selecter).css("border","1px solid #cbcbcb");
}

function toLogin(){
	location.href = basePath + "module/common.jsp?info=login";
}