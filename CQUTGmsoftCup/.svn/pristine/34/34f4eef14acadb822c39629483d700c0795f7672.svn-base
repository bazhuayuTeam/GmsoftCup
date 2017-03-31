//by:彭俊凯
//print For jqGrid

var datalist = {};
function printValue(datas,titles,modles,tableName,setting,num){
	datalist.title = titles;
	datalist.data = datas;
	datalist.modle = modles;
	datalist.tableName = tableName;
	datalist.setting = setting;
	datalist.num = num;
}

function transferValue(){
	return datalist;
}

//print For Div

function printForDiv(printHTML,setting){
	if(!printHTML || printHTML == ""){
		alert("无打印数据");
		return;
	}
	datalist.html = printHTML;
	if(setting && setting != ""){
		datalist.setting = setting;
	}else{
		var defultSetting = {};
		defultSetting.header = "";//页眉
		defultSetting.footer = "";//页脚
		defultSetting.portrait = 0;//横向-0、竖向-1 打印
		defultSetting.leftMargin = 0;//左边距
		defultSetting.topMargin = 0;//上边距
		defultSetting.rightMargin = 0;//右边距
		defultSetting.bottomMargin = 0;//下边距
		defultSetting.paperSize = "A4";//纸张
		datalist.setting = defultSetting;
	}
	if(null == basepath){
		alert("未设置'basepath',请在页面设置该变量");
		return;
	}else{
		window.open(basepath+"plugins/print/printPage.jsp?param=div");
	}
}