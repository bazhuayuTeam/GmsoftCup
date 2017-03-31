(function(window,undefined){
	var deriveObj = {
		dwrFun : '',
		properties : [],
		condition : '',
		needLink : false,
	    templateName : '',
	    lineNumber : 4,
	    orderNumber : true,
	    excelName : '',
	    examClassRoomID : '',
	    semesterId : '',
	};
	var DeriveExcelOfExam = {};
	
	DeriveExcelOfExam.initDeriveExel = function(deriveSetting){
		DeriveExcelOfExam.initDeriveObj(deriveSetting);
		DeriveExcelOfExam.deriver();
	};
	
	//初始化参数
	DeriveExcelOfExam.initDeriveObj = function(deriveSetting){
		deriveObj.dwrFun = deriveSetting.dwrFun;
		deriveObj.properties = deriveSetting.properties;
		deriveObj.condition = deriveSetting.condition;
		deriveObj.needLink = deriveSetting.needLink;
		deriveObj.templateName = deriveSetting.templateName;
		deriveObj.lineNumber = deriveSetting.lineNumber;
		deriveObj.orderNumber = deriveSetting.orderNumber;
		deriveObj.excelName = deriveSetting.excelName;
		deriveObj.examClassRoomID = deriveSetting.examClassRoomID;
		deriveObj.semesterId = deriveSetting.semesterId;
	};
	//导出
	//String [] properties,String condition,boolean needLink,String templateName,int lineNumber,boolean orderNumber
	DeriveExcelOfExam.deriver = function(){
		deriveObj.dwrFun(deriveObj.properties,deriveObj.condition,deriveObj.needLink,deriveObj.templateName,deriveObj.lineNumber,deriveObj.orderNumber,deriveObj.excelName,deriveObj.examClassRoomID,deriveObj.semesterId,function(file){
			if(file){
				console.debug(file);
				dwr.engine.openInDownload(file);
			}
		});
	}
	
	window.DeriveExcelOfExam = DeriveExcelOfExam;
})(window);