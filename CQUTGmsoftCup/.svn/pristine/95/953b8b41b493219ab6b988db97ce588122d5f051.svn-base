//(function($){...})(jQuery)用来定义一些需要预先定义好的函数
/*
 * 在 jQuery 中，fn 其实就是 JavaScript 中 propotype 的一个别名，$ 是 jQuery 的别名，所以
$.fn.pluginName 等同于 jQuery.prototype.pluginName
$.fn.pluginName 表示创建一个 jQuery 的属性，通俗的说是写一个 jQuery 函数
pluginName 才是函数名

实例
$.fn.setRedText = function() {
    return $(this).css("color", "red");
};
$("p").setRedText();
*/

(function($){// verification
	//验证正整数
	$.fn.verificationPositiveInteger = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^[0-9]*[1-9][0-9]*$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证非负整数 
	$.fn.verificationNonNegativeInteger = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern =  /^(0|[1-9]\d*)$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证非正整数 
	$.fn.verificationNonPositiveInteger = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^((-d+)|(0+))$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证负整数 
	$.fn.verificationNegativeInteger = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^-[0-9]*[1-9][0-9]*$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证整数
	$.fn.verificationInteger = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^-?d+$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证非负浮点数
	$.fn.verificationNonNegativeFloat = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = new RegExp("^\\d+(\\.\\d+)?$");
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证正浮点数
	$.fn.verificationPositiveFloat = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^(([0-9]+.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证非正浮点数
	$.fn.verificationNonPositiveFloat = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^((-d+(.d+)?)|(0+(.0+)?))$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证负浮点数
	$.fn.verificationNegativeFloat = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern =  /^(-(([0-9]+.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*.[0-9]+)|([0-9]*[1-9][0-9]*)))$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证浮点数
	$.fn.verificationFloat = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^(-?d+)(.d+)?$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证数字
	$.fn.verificationNumber = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = new RegExp("^[0-9]*$");
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证由26个英文字母组成的字符串 
	$.fn.verificationLetter = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^[a-za-z]+$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证由数字、26个英文字母或者下划线组成的字符串
	$.fn.verificationIntegerLetterUnderline = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^w+$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证email地址 
	$.fn.verificationEmail = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /^[w-]+(.[w-]+)*@[w-]+(.[w-]+)+$/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证匹配中文字符
	$.fn.verificationChinese = function() {
		if($.trim($(this).val())=="") return false;
		var Pattern = /[u4e00-u9fa5]/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证首尾空格
	$.fn.verificationHeadTailSpace= function() {
		if($.trim($(this).val())=="") return false;
		var Pattern =  /(^s*)|(s*$)/;
		return Pattern.test($.trim($(this).val()));
	}
	
	//验证无内容
	$.fn.verificationNoContent = function() {
		return $trim($.trim($(this).val())) == "";
	}
	
	//验证百分比0-100(保留两位有效数字)
    $.fn.verificationPercent = function(){
    	if($.trim($(this).val())=="") return false;
	    var Pattern = /^(100|[1-9]?\d(\.\d\d?)?)%$/;
        return Pattern.test($.trim($(this).val()));
   }
    
    //验证比例(0~1,保留两位有效数字)
    $.fn.verificationRate = function(){
    	if($.trim($(this).val())=="") return false;
		var Pattern = new RegExp("^\\d+(\\.\\d+)?$");
		if(Pattern.test($.trim($(this).val()))) {
			if($.trim($(this).val()) >= 0 && $.trim($(this).val()) <= 1) {
				return true;
			}
		}
		return false;
   }
    
  
    //验证年月日 2015-12-01
    $.fn.verificationYearMonthDay = function(){
 	   if($.trim($(this).val())=="") return false;
 	    var Pattern = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig;
        return Pattern.test($.trim($(this).val()));
    }
    
  //验证年月 2015-12或2015-02
    $.fn.verificationYearMonth = function(){
       if($.trim($(this).val())=="") return false;
 	   var Pattern = /^\d{4}-?(?:0[1-9]|1[0-2])$/;
 	   return Pattern.test($.trim($(this).val()));
    };
    
  //验证第一个年月日是否比第二个年月日大
    $.fn.verificationYearMonthDayLarger = function(dateOne,dateTwo){
    	var Pattern1 = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig;
    	var Pattern2 = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig;
    	if(Pattern1.test(dateOne) && Pattern2.test(dateTwo)) {
    		var yearMonthDay1 = dateOne.split("-");
    		var yearMonthDay2 = dateTwo.split("-");
    		return parseInt(yearMonthDay1[0]) > parseInt(yearMonthDay2[0]) || 
    			(parseInt(yearMonthDay1[0]) == parseInt(yearMonthDay2[0]) && parseInt(yearMonthDay1[1]) > parseInt(yearMonthDay2[1])) ||
    			(parseInt(yearMonthDay1[0]) == parseInt(yearMonthDay2[0]) && parseInt(yearMonthDay1[1]) == parseInt(yearMonthDay2[1]) && parseInt(yearMonthDay1[2]) > parseInt(yearMonthDay2[2]));
    	}	
    }
    
   //验证第一个年月是否比第二个年月大
    $.fn.verificationYearMonthLarger = function(dateOne,dateTwo){
    	var Pattern1 = /^\d{4}-?(?:0[1-9]|1[0-2])$/;
    	var Pattern2 = /^\d{4}-?(?:0[1-9]|1[0-2])$/;
    	if(Pattern1.test(dateOne) && Pattern2.test(dateTwo)) {
    		var yearMonth1 = dateOne.split("-");
    		var yearMonth2 = dateTwo.split("-");
    		return parseInt(yearMonth1[0]) > parseInt(yearMonth2[0]) || (parseInt(yearMonth1[0]) == parseInt(yearMonth2[0]) && parseInt(yearMonth1[1]) > parseInt(yearMonth2[1]));
    	}	
    }
    
  //验证年月日是否大于当前时间
    $.fn.verificationYearMonthDayNowLarger = function(){
    	if($.trim($(this).val())=="") return false;
    	var nowDate = new Date();
    	var nowDateStr = "";
    	nowDateStr +=nowDate.getFullYear() + "-";
    	nowDateStr+= (nowDate.getMonth() + 1)>9?"":"0"+(nowDate.getMonth() + 1) + "-";
    	nowDateStr+= nowDate.getDate()>9?"":"0"+nowDate.getDate();
    	return $().verificationYearMonthDayLarger($.trim($(this).val()), nowDateStr);
    }
    
  //验证年月是否大于当前时间
    $.fn.verificationYearMonthNowLarger = function(){
    	if($.trim($(this).val())=="") return false;
    	var nowDate = new Date();
    	var nowDateStr = "";
    	nowDateStr+=nowDate.getFullYear() + "-";
    	nowDateStr+= (nowDate.getMonth() + 1)>9?"":"0"+(nowDate.getMonth() + 1);
    	return $().verificationYearMonthLarger($.trim($(this).val()), nowDateStr);
    }
	
  //验证2015.12
    $.fn.verificationYearAndMonth = function(){
 	   if($.trim($(this).val())=="") return false;
 	   var Pattern = /^(?:19|20)[0-9][0-9][.](?:(?:0[1-9])|(?:1[0-2]))$/;
 	   if(Pattern.test($.trim($(this).val()))){
 		    var time=$.trim($(this).val()).split(/[.]/);
 		    var year=new Date().getFullYear();
 		    var month=parseInt(new Date().getMonth())+1;
 		    if(time[0]>year){
 		    	return false;
 		    }
 		    else if(time[0]==year&&parseInt(time[1])>parseInt(month)){
 		    	return false;
 		    }
 		    else{
 		    	return true;
 		    }
 	  	}
 	    else{
 	    	return false;	
 	    }
    }
    
})(jQuery)