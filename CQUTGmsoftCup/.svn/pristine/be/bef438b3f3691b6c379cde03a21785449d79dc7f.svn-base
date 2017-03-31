;
var leaveJudge={
	isChange:false,
	currentSelect:""
};

//----------------------------改变页面的输入时，离开时未保存验证是否保存----------------------------------
$(function(){
	isChangeData();
});

/**
 * 是否改变了数据
 */
function isChangeData(){	
	//下拉框
	$("table tr td select").change(function(){
		leaveJudge.isChange=true;
	});

	//输入框
	$("table tr td input").change(function(){
		leaveJudge.isChange=true;
	});
	
	//输入域
	$("textarea").change(function(){
		leaveJudge.isChange=true;
	});
	
	//保存按钮
	$("button").click(function(){
		if($(this).text()=="保存"){
			leaveJudge.isChange=false;
		}
	});
	
}

