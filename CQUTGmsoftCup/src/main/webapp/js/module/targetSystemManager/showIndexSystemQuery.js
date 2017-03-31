;
// ZhaoXin 在评估指标页面用于预览指标


// 初始化页面，版本id为传值过来的id
function defaultInit(targetSysVersionID){
	pageData.currentIndexVersion=targetSysVersionID;
	load();
}

// 禁用元素
function forbid(){
	//$(".frame").find("ul").css("display","none");
	$("#QueryArea").css("display","none");
}






