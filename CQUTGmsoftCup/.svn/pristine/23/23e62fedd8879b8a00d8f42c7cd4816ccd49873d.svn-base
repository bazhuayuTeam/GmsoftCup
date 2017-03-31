var currentId = "info";

function showClick(url,id){
	loadHtml(url);
	if(currentId != id){
		$("#" + currentId + "Link").css("color","#555");
		$("#" + currentId).css("background-color","#fff");
		$("#" + id).css("background-color","#52acea");
		$("#" + id + "Link").css("color","#fff");
		currentId = id;
	}
}

function loadHtml(url){
	$.ajax({
		url: url,
        cache: false,
        success: function(html){
            $("#frame_div").html(html);
        }
	});
}

$(function(){
	if(userName == null||userName == "" || userName == "null"){
		alert("请先登录");
		location.href = basePath + "module/common.jsp?info=login";
		return;
	}
	$("#userName").text(userName);
	if(info == "userInfo"){
		showClick("module/personalCenter/userInfo/userInfoManager.jsp","info");
	}else if(info == "team"){
		showClick('module/personalCenter/competition/competitionManager.jsp','team');
	}
	
	$("#pass").click(function(){
		UserService.quit(function(data){
			if(data){
				location.href = basePath + "index.jsp";	
			}
		});
	});
	
	$("#userName").click(function(){
		showClick("module/personalCenter/userInfo/userInfoManager.jsp","info");
	});
});

function goHome(){
	location.href = basePath + "index.jsp";
}

function toLogin(){
	alert("请先登录");
	location.href = basePath + "module/common.jsp?info=login";
}