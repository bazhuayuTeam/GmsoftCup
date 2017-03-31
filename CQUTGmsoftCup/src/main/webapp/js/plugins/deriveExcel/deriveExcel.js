(function(window,undefined){
	var deriveObj = {
		dwrFun : '',
		properties : [],
		condition : '',
		needLink : false,
	    templateName : '',
	    lineNumber : 4,
	    orderNumber : true,
	    excelName : ''
	};
	var DeriveExel = {};
	
	DeriveExel.initDeriveExel = function(deriveSetting){
		DeriveExel.initDeriveObj(deriveSetting);
		var result= DeriveExel.deriver();
		return result;
	};
	
	//初始化参数
	DeriveExel.initDeriveObj = function(deriveSetting){
		deriveObj.dwrFun = deriveSetting.dwrFun;
		deriveObj.properties = deriveSetting.properties;
		deriveObj.condition = deriveSetting.condition;
		deriveObj.needLink = deriveSetting.needLink;
		deriveObj.templateName = deriveSetting.templateName;
		deriveObj.lineNumber = deriveSetting.lineNumber;
		deriveObj.orderNumber = deriveSetting.orderNumber;
		deriveObj.excelName = deriveSetting.excelName;
	};
	//导出
	//String [] properties,String condition,boolean needLink,String templateName,int lineNumber,boolean orderNumber
	DeriveExel.deriver = function(){
		deriveObj.dwrFun(deriveObj.properties,deriveObj.condition,deriveObj.needLink,deriveObj.templateName,deriveObj.lineNumber,deriveObj.orderNumber,deriveObj.excelName,function(file){
			if(file!=null){
				console.debug("1:true");
				dwr.engine.openInDownload(file);
				return true;
			}else{
				console.debug("2:false");
				return false;
			}
		});
	}
	
	window.DeriveExel = DeriveExel;
})(window);