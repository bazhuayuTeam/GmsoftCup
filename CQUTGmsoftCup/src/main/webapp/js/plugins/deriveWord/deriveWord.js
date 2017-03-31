/*----------------导出word文档*/
(function(window,undefined){
	var deriveObj = {
		dwrFun : '',
		properties : [],
		condition : '',
		needLink : false,
	    templateName : '',
	    lineNumber : 4,
	    orderNumber : true,
	    wordName : ''
	};
	var DeriveWord = {};
	
	DeriveWord.initDeriveWord = function(deriveSetting){
		DeriveWord.initDeriveObj(deriveSetting);
		var result= DeriveWord.deriver();
		return result;
	};
	
	//初始化参数
	DeriveWord.initDeriveObj = function(deriveSetting){
		deriveObj.dwrFun = deriveSetting.dwrFun;
		deriveObj.properties = deriveSetting.properties;
		deriveObj.condition = deriveSetting.condition;
		deriveObj.needLink = deriveSetting.needLink;
		deriveObj.templateName = deriveSetting.templateName;
		deriveObj.lineNumber = deriveSetting.lineNumber;
		deriveObj.orderNumber = deriveSetting.orderNumber;
		deriveObj.wordName = deriveSetting.wordName;
	};
	//导出
	//String [] properties,String condition,boolean needLink,String templateName,int lineNumber,boolean orderNumber
	DeriveWord.deriver = function(){
		deriveObj.dwrFun(deriveObj.properties,deriveObj.condition,deriveObj.needLink,deriveObj.templateName,deriveObj.lineNumber,deriveObj.orderNumber,deriveObj.wordName,function(file){
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
	
	window.DeriveWord = DeriveWord;
	
})(window);