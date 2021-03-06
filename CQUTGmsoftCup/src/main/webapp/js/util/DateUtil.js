// 将例如 2012-01-16 格式的字符串转化为日期对象
var DateUtil = {};
// str 为传入的日期格式的字符串 如：2012-01-16
DateUtil.dateFormat = function(str) {
	if(str.replace(/^\s+|\s+$/g,"")==""||(/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/).test(str)==false) {
		str = "1900-01-01";
	}
	var strArray = str.split('-');
	var dateObj = new Date(strArray[0], parseInt(strArray[1] - 1), strArray[2]);
	return dateObj;
}
// 计算两个日期相差多少天
DateUtil.dateDiff=function(beginDate, endDate) { // beginDate和endDate是2006-12-18格式
	var beginDateArray = beginDate.split("-")
	beginDateObj = new Date(beginDateArray[1] + '-' + beginDateArray[2] + '-' + beginDateArray[0]) // 转换为12-18-2006格式
	var endDateArray = endDate.split("-")
	endDateObj= new Date(endDateArray[1] + '-' + endDateArray[2] + '-' + endDateArray[0])
	return parseInt(Math.abs(endDateObj - beginDateObj) / 1000 / 60 / 60 / 24) // 把相差的毫秒数转换为天数
}

//获得两个时间的分钟差
DateUtil.dateDiffMills=function(beginDate, endDate) { // beginDate和endDate是2006-12-18格式
	var time1=beginDate.getTime();
	var time2=endDate.getTime();
	return parseInt(time2-time1)/1000/60;
}

//传入毫秒，返回年-月-日 时-分格式
DateUtil.dateDiffMills=function(millis) { // beginDate和endDate是2006-12-18格式
	if(millis==""||millis==0||millis==null||millis=="null"){
		return "";
	}

	var d=new Date(parseInt(millis));
	var year=d.getFullYear();
	var day=change(String(d.getDate()));
	var month=change(String(d.getMonth()+1));
	var hour=change(String(d.getHours()));
	var minute=change(String(d.getMinutes()));
	var second=change(String(d.getSeconds()));
	var f=year+"-"+month+"-"+day+" "+hour+":"+minute;
	return f;
}

function change(num){
	return num.length==1?"0"+num:num;
}

/*计算某一个日期是星期几
str 为传入的日期格式的字符串 如：2012-01-16*/
DateUtil.getWeekdayByDate=function(str){
	return DateUtil.dateFormat(str).getDay()==0 ? 7 : DateUtil.dateFormat(str).getDay();
}
//传入 一个日期对象获取日期部分 如:2012-01-16 
DateUtil.DateToStr=function(dateObj){
	var str = dateObj.getFullYear()+"-";
	str+=((dateObj.getMonth()+1)+"").length==1?"0"+(dateObj.getMonth()+1):(dateObj.getMonth()+1);
	str+="-";
	str+=(dateObj.getDate()+"").length==1?"0"+dateObj.getDate():dateObj.getDate();
	return str;
}
//传入 一个日期对象获取时分秒部分 如:9:30:00 
DateUtil.TimeToStr=function(dateObj){
	var str="";
	str+=(dateObj.getHours()+"").length==1?"0"+(dateObj.getHours()+""):(dateObj.getHours()+"");
	str+=":"
	str+=(dateObj.getMinutes()+"").length==1?"0"+(dateObj.getMinutes()+""):(dateObj.getMinutes()+"");
	str+=":"
	str+=(dateObj.getSeconds()+"").length==1?"0"+(dateObj.getSeconds()+""):(dateObj.getSeconds()+"");
	return str;
}
//传入 一个日期对象获取日期部分 如:2012-01-16 11:58:20 
DateUtil.DateToStrTime=function(dateObj){
	var str = dateObj.getFullYear()+"-";
	str+=((dateObj.getMonth()+1)+"").length==1?"0"+(dateObj.getMonth()+1):(dateObj.getMonth()+1);
	str+="-";
	str+=(dateObj.getDate()+"").length==1?"0"+dateObj.getDate():dateObj.getDate();
	str+=" ";
	str+=(dateObj.getHours()+"").length==1?"0"+(dateObj.getHours()+""):(dateObj.getHours()+"");
	str+=":";
	str+=(dateObj.getMinutes()+"").length==1?"0"+(dateObj.getMinutes()+""):(dateObj.getMinutes()+"");
	str+=":";
	str+=(dateObj.getMinutes()+"").length==1?"0"+(dateObj.getMinutes()+""):(dateObj.getMinutes()+"");
//	str+=" "+dateObj.getHours()+":"+dateObj.getMinutes()+":"+dateObj.getSeconds();
	return str;
}

//传入  一个日期对象转换为毫秒数 如:2012-01-16 11:58:20
DateUtil.DateToMS=function (time)   
{   
     time = time.replace(new RegExp("-","gm"),"/");
     var timeMS = (new Date(time)).getTime(); //得到毫秒数   
  
    return timeMS;   
}

//传入 一个日期对象获取日期部分 如:2012-01-16 11:58:20 
DateUtil.DateToStrTimeQuick=function(dateObj1,dateObj2){
	//计算年月日部分
	var str = dateObj1.getFullYear()+"-";
	str+=((dateObj1.getMonth()+1)+"").length==1?"0"+(dateObj1.getMonth()+1):(dateObj1.getMonth()+1);
	str+="-";
	str+=(dateObj1.getDate()+"").length==1?"0"+dateObj1.getDate():dateObj1.getDate();
	//计算时分秒部分
	str+=" (";
	str+=(dateObj1.getHours()+"").length==1?"0"+(dateObj1.getHours()+""):(dateObj1.getHours()+"");
	str+=":"
	str+=(dateObj1.getMinutes()+"").length==1?"0"+(dateObj1.getMinutes()+""):(dateObj1.getMinutes()+"");
	str+="-";
	str+=(dateObj2.getHours()+"").length==1?"0"+(dateObj2.getHours()+""):(dateObj2.getHours()+"");
	str+=":"
	str+=(dateObj2.getMinutes()+"").length==1?"0"+(dateObj2.getMinutes()+""):(dateObj2.getMinutes()+"");
	str+=")"
	return str;
}

//获取日期的小时分钟  返回的格式是09:00
DateUtil.DateToTime=function(dateObj1){
	//计算年月日部分
	var str="";
	str+=(dateObj1.getHours()+"").length==1?"0"+(dateObj1.getHours()+""):(dateObj1.getHours()+"");
	str+=":"
	str+=(dateObj1.getMinutes()+"").length==1?"0"+(dateObj1.getMinutes()+""):(dateObj1.getMinutes()+"");
	return str;
}

//传入 一个日期对象获取日期部分 如:2012年01月16日 11:58:20 
DateUtil.DateToStrTimeCN=function(dateObj){
	var str = dateObj.getFullYear()+"年";
	str+=((dateObj.getMonth()+1)+"").length==1?"0"+(dateObj.getMonth()+1):(dateObj.getMonth()+1);
	str+="月";
	str+=(dateObj.getDate()+"").length==1?"0"+dateObj.getDate():dateObj.getDate()+"日";
	str+=" "+dateObj.getHours()+":"+dateObj.getMinutes()+":"+dateObj.getSeconds();
//	str+=" "+(dateObj.getHours()+"").length==1?"0"+dateObj.getHours():dateObj.getHours();
//	str+=":"+(dateObj.getMinutes()+"").length==1?"0"+dateObj.getMinutes():dateObj.getMinutes();
//	str+=":"+(dateObj.getSeconds()+"").length==1?"0"+dateObj.getSeconds():dateObj.getSeconds();
	return str;
}

DateUtil.DateToTimeW=function(dateObj){
	var str = dateObj.getFullYear()+"年";
	str+=((dateObj.getMonth()+1)+"").length==1?"0"+(dateObj.getMonth()+1):(dateObj.getMonth()+1);
	str+="月";
	str+=(dateObj.getDate()+"").length==1?"0"+dateObj.getDate()+"日":dateObj.getDate()+"日";
	return str;
}
/**
 * 时间对象的格式化;
 */
DateUtil.format = function(date,format) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	
	if (date == null || date == ""){
		return "";
	}
	var o = {
		"M+" : date.getMonth() + 1, // month
		"d+" : date.getDate(), // day
		"h+" : date.getHours(), // hour
		"m+" : date.getMinutes(), // minute
		"s+" : date.getSeconds(), // second
		"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
		"S" : date.getMilliseconds()
		// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4
						- RegExp.$1.length));
	}

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

DateUtil.StringToDate = function(str){
	str =  str.replace("-","/");
      //// str =  str.replace("T"," "); 
	var date = new Date(str);

	return date;
}
//传入两个时间对象,返回它们之间的相差分钟数
DateUtil.calcTimeDiff=function(dateObj1,dateObj2){
//	var testTime=DateUtil.DateToStr(dateObj1)+"T"+DateUtil.TimeToStr(dateObj1);
//	console.group("js小测试");
//	console.log("testTime:"+testTime);
//	console.log("new Date():");
//	console.log(new Date(testTime));
//	console.log("new Date(Date.parse()):");
//	console.log(new Date(Date.parse(testTime)));
//	console.groupEnd();
//	testTime=DateUtil.DateToStr(dateObj1)+"T"+DateUtil.TimeToStr(dateObj1)+"+08:00";
//	console.group("js小测试");
//	console.log("testTime:"+testTime);
//	console.log("new Date():");
//	console.log(new Date(testTime));
//	console.log("new Date(Date.parse()):");
//	console.log(new Date(Date.parse(testTime)));
//	console.groupEnd();
	
	
	var newDate1=DateUtil.DateToStr(dateObj1)+"T"+DateUtil.TimeToStr(dateObj1)+"+08:00";
	var newDate2=DateUtil.DateToStr(dateObj2)+"T"+DateUtil.TimeToStr(dateObj2)+"+08:00";
	var date1=new Date(newDate1);
	var date2=new Date(newDate2);
//	console.group();
//	console.log(newDate1);
//	console.log(newDate2);
//	console.log(date1);
//	console.log(date2);
//	console.groupEnd();
	var limit=parseInt(Math.abs(date2-date1)/1000/60);
	console.debug(limit);
	return limit;
}
//传入一个时间对象和分钟数，返回时间对象+分钟数之后的时间对象
DateUtil.calcTimeBack=function(dateObj,lastTime){
	var newDate=new Date(Date.parse(DateUtil.DateToStr(dateObj)+"T"+DateUtil.TimeToStr(dateObj)+"+08:00"));
	var lastTimeMilliSeconds=lastTime*60*1000;
	var milliSeconds=newDate.getTime()+lastTimeMilliSeconds;
	console.debug(milliSeconds);
	var result=new Date();
	result.setTime(milliSeconds);
	return result;
}