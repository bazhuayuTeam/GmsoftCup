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
	    semesterID : '',
	    schoolYearID : '',
	    order : '',
	    campusCode:''
	};
	var DeriveExcelOfBKExam = {};
	
	DeriveExcelOfBKExam.initDeriveExel = function(deriveSetting){
		DeriveExcelOfBKExam.initDeriveObj(deriveSetting);
		DeriveExcelOfBKExam.deriver();
	};
	
	//初始化参数
	DeriveExcelOfBKExam.initDeriveObj = function(deriveSetting){
		deriveObj.dwrFun = deriveSetting.dwrFun;
		deriveObj.properties = deriveSetting.properties;
		deriveObj.condition = deriveSetting.condition;
		deriveObj.needLink = deriveSetting.needLink;
		deriveObj.templateName = deriveSetting.templateName;
		deriveObj.lineNumber = deriveSetting.lineNumber;
		deriveObj.orderNumber = deriveSetting.orderNumber;
		deriveObj.excelName = deriveSetting.excelName;
		deriveObj.semesterID = deriveSetting.semesterID;
		deriveObj.schoolYearID = deriveSetting.schoolYearID;
		deriveObj.order = deriveSetting.order;
		deriveObj.campusCode = deriveSetting.campusCode;
	};
	//导出
	//String [] properties,String condition,boolean needLink,String templateName,int lineNumber,boolean orderNumber
	DeriveExcelOfBKExam.deriver = function(){
		deriveObj.dwrFun(deriveObj.properties,deriveObj.condition,deriveObj.needLink,deriveObj.templateName,deriveObj.lineNumber,deriveObj.orderNumber,deriveObj.excelName,deriveObj.semesterID,deriveObj.schoolYearID,deriveObj.order,deriveObj.campusCode,function(file){
			if(file){
				console.debug(file);
				dwr.engine.openInDownload(file);
			}
		});
	}
	
	window.DeriveExcelOfBKExam = DeriveExcelOfBKExam;
})(window);