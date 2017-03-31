
$(function(){
	$("#header_img").css("width", $(window).width());
	init();
});

function init(){
	if(modeInfo == "register"){
		$("#hidden_span").hide();
		loadHtml("module/register/registerManager.jsp");
		$("#link_one").text("登录");
		$("#link_one").click(function(){
			location.href = basePath + "module/common.jsp?info=login";
		});
		$("#link_two").text("返回");
		$("#link_two").click(function(){
			location.href = basePath + "index.jsp";
		});
	}else if(modeInfo == "login"){
		$("#hidden_span").hide();
		loadHtml("module/login/login.jsp");
		$("#link_one").text("注册");
		$("#link_one").click(function(){
			location.href = basePath + "module/common.jsp?info=register";
		});
		$("#link_two").text("返回");
		$("#link_two").click(function(){
			location.href = basePath + "index.jsp";
		});
	}else if(modeInfo == "enroll"){
		if(operatorId == null||operatorId == ""||operatorId == "null"){
			alert("请先登录！");
			location.href = basePath + "module/common.jsp?info=login";
		}
		$("#hidden_span").css("display","ruby");
		loadHtml("module/enroll/enrollManager.jsp");
		$("#link_one").text(userName);
		$("#link_one").click(function(){
			location.href = basePath + "module/personalCenter/personalManager.jsp?info=userInfo";
		});
		$("#link_two").text("注销");
		$("#link_two").click(function(){
			UserService.quit(function(data){
				if(data){
					location.href = basePath + "index.jsp";	
				}
			});
		});
	}
}

function loadHtml(url){
	$.ajax({
		url: url,
        cache: false,
        success: function(html){
            $("#contentDiv").html(html);
        }
	});
}

function toLogin(){
	$("#link_one").text("注册");
	$("#link_one").click(function(){
		loadHtml("module/register/registerManager.jsp");
	});
}

function toRegister(){
	$("#link_one").text("登录");
	$("#link_one").click(function(){
		loadHtml("module/login/login.jsp");
	});
}

//子页面跳转
function childLink(info){
	$("#header_img").css("width", $(window).width());
	if(modeInfo == "register"){
		location.href = basePath + "index.jsp";
	}else if(modeInfo == "login"){
		location.href = basePath + "index.jsp";
	}else if(modeInfo == "enroll"){
		location.href = basePath + "module/personalCenter/personalManager.jsp?info=team";
	}else if(modeInfo == "enrollPass"){
		location.href = basePath + "module/personalCenter/personalManager.jsp?info=userInfo";
	}else if(modeInfo == "register"){
		init();
	}
}

function goHome(){
	location.href = basePath + "index.jsp";
}
