;

//页面样式
var pageStyle={
	inputBackgroundPath:"url(images/login/selected.png) no-repeat left center",
	lableColor:"#8ab939",
	errorLogin:["<span>输入用户名不能为空</span>",
	            "<span>输入密码不能为空</span>",
	            "<span>用户名或密码输入有误</span>",
	            "<span>该账号尚未激活,请与管理员联系!</span>"]
};

var loginData={
	beforSelectRole:"role2Lable",
	selectRoleType:2
};

//------------------------------------------入口-------------------------
$(function(){
	loadData();
	changeOfSize();
	$('input, textarea').placeholder(); 	//使IE支持placeholder
});

function loadData(){
	if(judgeBrowser()){
		inputPasswordAndUser();
		keyEvent();
		setStyleBegin();
	}
};

//-----------------------------------浏览器判断-------------
//不支持浏览器
function judgeBrowser(){
	if($.browser.msie) { 
		var version=$.browser.version;
		var num=parseInt(version);
		if(num<=9){
			$("body").html("");
			alert("您使用的浏览器版本过低，请转换或下载浏览器！");
			window.location.href='http://www.firefox.com.cn/download/';
			return false;
		}
	}
	
	return true;
}

//------------------------------------效果-------------------------------

//输入用户和密码
function inputPasswordAndUser(){
	$(".inputStyle").focus(function(){
		$(this).css("color","#c3a080");
	});
	
	$(".inputStyle").blur(function(){
		var text=$(this).val();
		if(text==""){
			$(this).css("color","#cbcaca");
		}
	});
}

//窗口改变时
function changeOfSize(){
	$(window).resize(function(){
		window.resizeBy(136,450);
	});
}

//初始加载的样式
function setStyleBegin(){
	$("#role2Lable").css("background",pageStyle.inputBackgroundPath);
	$("#role2Lable").css("color",pageStyle.lableColor);
}


//按键事件
function keyEvent(){
	$(document).keyup(function(event){
		if(event.keyCode==13){
			loginJudge();
		}
	});
}

//---------------------------------------------------登陆-------------------------
function loginJudge(){
	var user=$("#user").val();
	var password=$("#password").val();
	if(isInput(user,password)){
		checkLogin(user,password);
	}
}

//判断是否输入
function isInput(user,password){
	if(user==""){
		$("#error").html("");
		$("#error").html(pageStyle.errorLogin[0]);
		return false;
	}
	
	if(password==""){
		$("#error").html("");
		$("#error").html(pageStyle.errorLogin[1]);
		return false;
	}
	
	return true;
}

//查询
function checkLogin(user,password){
	OperatorService.LoginCheck(user, password,function(message) {
		if (0 == message) {
			$("#error").html("");
			$("#error").html(pageStyle.errorLogin[2]);
		} else if (1 == message) {
			window.location.href = 'module/manager/manager.jsp';
		} else {
			$("#error").html("");
			$("#error").html(pageStyle.errorLogin[3]);
		}
	});
}