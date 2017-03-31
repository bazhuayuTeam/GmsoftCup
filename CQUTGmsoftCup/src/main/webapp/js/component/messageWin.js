var mWin = {
	ok : function(){
		var content = '保存成功！';
		if(arguments.length>0 && arguments[0] != ''){
			content = arguments[0];
		}
		tips('',content);
	},
	alert : function(msg){
		top.parent.window.alertWin(msg);
	}
};
function tips(title, content) {
	$.growlUI(title, content, 1000);
}
