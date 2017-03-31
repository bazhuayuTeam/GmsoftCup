/**
 * 通过传入日期获取对应的农历
 */
var CalendarData = new Array(100);
var madd = new Array(12);
var tgString = "甲乙丙丁戊己庚辛壬癸";
var dzString = "子丑寅卯辰巳午未申酉戌亥";
var numString = "一二三四五六七八九十";
var monString = "正二三四五六七八九十冬腊";
var weekString = "日一二三四五六";
var sx = "鼠牛虎兔龙蛇马羊猴鸡狗猪";
//定义阴历节日
var lFtv =[{"0101": "春节"},{"0115": "元宵节"},{"0505": "端午节"},{"0815": "中秋节"},{"0909": "重阳节"},{"1208": "腊八节"}]; 
//定义阳历节日
var sFtv =[{"0101" : "元旦节"},{"0214" : "情人节"},{"0305" : "学雷锋纪念日"},{"0308" : "妇女节"},{"0312" : "植树节"},{"0315" : "消费者权益日"},
    {"0401" : "愚人节"},{"0501" : "劳动节"},{"0504" : "青年节"},{"0601" : "国际儿童节"},{"0701" : "中国共产党诞辰"},{"0801" : "建军节"},{"0910" : "中国教师节"},
	{"1001" : "国庆节"}, {"1224" : "平安夜"}, {"1225" : "圣诞节"}];
var cYear, cMonth, cDay, TheDate;
CalendarData = new Array(0xA4B, 0x5164B, 0x6A5, 0x6D4, 0x415B5, 0x2B6, 0x957, 0x2092F, 0x497, 0x60C96, 0xD4A, 0xEA5, 0x50DA9, 0x5AD, 0x2B6, 0x3126E, 0x92E, 0x7192D, 0xC95, 0xD4A, 0x61B4A, 0xB55, 0x56A, 0x4155B, 0x25D, 0x92D, 0x2192B, 0xA95, 0x71695, 0x6CA, 0xB55, 0x50AB5, 0x4DA, 0xA5B, 0x30A57, 0x52B, 0x8152A, 0xE95, 0x6AA, 0x615AA, 0xAB5, 0x4B6, 0x414AE, 0xA57, 0x526, 0x31D26, 0xD95, 0x70B55, 0x56A, 0x96D, 0x5095D, 0x4AD, 0xA4D, 0x41A4D, 0xD25, 0x81AA5, 0xB54, 0xB6A, 0x612DA, 0x95B, 0x49B, 0x41497, 0xA4B, 0xA164B, 0x6A5, 0x6D4, 0x615B4, 0xAB6, 0x957, 0x5092F, 0x497, 0x64B, 0x30D4A, 0xEA5, 0x80D65, 0x5AC, 0xAB6, 0x5126D, 0x92E, 0xC96, 0x41A95, 0xD4A, 0xDA5, 0x20B55, 0x56A, 0x7155B, 0x25D, 0x92D, 0x5192B, 0xA95, 0xB4A, 0x416AA, 0xAD5, 0x90AB5, 0x4BA, 0xA5B, 0x60A57, 0x52B, 0xA93, 0x40E95);
madd[0] = 0;
madd[1] = 31;
madd[2] = 59;
madd[3] = 90;
madd[4] = 120;
madd[5] = 151;
madd[6] = 181;
madd[7] = 212;
madd[8] = 243;
madd[9] = 273;
madd[10] = 304;
madd[11] = 334;

function GetBit(m, n) {
    return (m >> n) & 1;
}
function e2c() {
    TheDate = (arguments.length != 3) ? new Date() : new Date(arguments[0], arguments[1], arguments[2]);
    var total, m, n, k;
    var isEnd = false;
    var tmp = TheDate.getYear();
    if (tmp < 1900) {
        tmp += 1900;
    }
    total = (tmp - 1921) * 365 + Math.floor((tmp - 1921) / 4) + madd[TheDate.getMonth()] + TheDate.getDate() - 38;

    if (TheDate.getYear() % 4 == 0 && TheDate.getMonth() > 1) {
        total++;
    }
    for (m = 0; ; m++) {
        k = (CalendarData[m] < 0xfff) ? 11 : 12;
        for (n = k; n >= 0; n--) {
            if (total <= 29 + GetBit(CalendarData[m], n)) {
                isEnd = true; break;
            }
            total = total - 29 - GetBit(CalendarData[m], n);
        }
        if (isEnd) break;
    }
    cYear = 1921 + m;
    cMonth = k - n + 1;
    cDay = total;
    if (k == 12) {
        if (cMonth == Math.floor(CalendarData[m] / 0x10000) + 1) {
            cMonth = 1 - cMonth;
        }
        if (cMonth > Math.floor(CalendarData[m] / 0x10000) + 1) {
            cMonth--;
        }
    }
}

function GetcDateString() {
    var tmp = "";
    tmp += tgString.charAt((cYear - 4) % 10);
    tmp += dzString.charAt((cYear - 4) % 12);
    tmp += "(";
    tmp += sx.charAt((cYear - 4) % 12);
    tmp += ")年 ";
    if (cMonth < 1) {
        tmp += "(闰)";
        tmp += monString.charAt(-cMonth - 1);
    } else {
        tmp += monString.charAt(cMonth - 1);
    }
    tmp += "月";
    tmp += (cDay < 11) ? "初" : ((cDay < 20) ? "十" : ((cDay == 20) ? "二十":((cDay < 30) ? "廿" : "三十")));
    if (cDay % 10 != 0 || cDay == 10) {
        tmp += numString.charAt((cDay - 1) % 10);
    }
    return tmp;
}

function GetLunarDay(solarYear, solarMonth, solarDay) {
    //solarYear = solarYear<1900?(1900+solarYear):solarYear;
    if (solarYear < 1921 || solarYear > 2020) {
        return "";
    } else {
        solarMonth = (parseInt(solarMonth) > 0) ? (solarMonth - 1) : 11;
        e2c(solarYear, solarMonth, solarDay);
        return GetcDateString();
    }
}

var month={"正月":"01","二月":"02","三月":"03","四月":"04","五月":"05","六月":"06",
		"七月":"07","八月":"08","九月":"09","十月":"10","冬月":"11","腊月":"12"};
var decade={"初":"0","十":"1","廿":"2","三":"3"};
var unit={"一":"1","二":"2","三":"3","四":"4","五":"5","六":"6","七":"7","八":"8","九":"9","十":"0",};
var currentDate=new Date();
var currentYearLunar=GetLunarDay(currentDate.getFullYear(),12,1).substring(0, 6).trim();
var currentDateLunar=GetLunarDay(currentDate.getFullYear(),currentDate.getMonth()+1,currentDate.getDate()).substring(0, 6).trim();
var yesterYearLunar=GetLunarDay(currentDate.getFullYear()-1,12,1).substring(0, 6).trim();
/**
 * lunarDate：当前时间的农历月日 如：五月初四
 * date：当前时间的月日 如：0608
 * 获取离当前最近的节日
 */
function GetRecentFestival(lunarDate,date){
	//console.info(lunarDate+"-----"+date);
	var object={newRecentFestivalDate:"",lunarRecentFestivalDate:"",festival:""};
    var lunarMonthAndDay=lunarDate.substring(lunarDate.length-4,lunarDate.length).trim();//当前时间的农历如：二月廿一
    var numMonthAndDay=month[lunarMonthAndDay.substring(0,2)]+dealDetailDayNum(lunarMonthAndDay.substring(2,4));//当前时间农历的数字形式如：0221
    
    var newRecentFestival=RecentFestival(sFtv,date,"").trim();//离当前时间最近的新历节日时间
    var lunarRecentFestival=RecentFestival(lFtv,date,numMonthAndDay).trim();//离当前时间最近的农历节日时间
    
    var newLunarRecentF=GetLunarDay(currentDate.getFullYear(),deleteZero(newRecentFestival.substring(0,2)),deleteZero(newRecentFestival.substring(2,4)));//获取最近新历节日对应的农历时间    
    var newLunarRecentFM=newLunarRecentF.split("年")[1].trim();//获取最近新历节日对应农历的月份及天
    var numNewLunarRecentFM=month[newLunarRecentFM.substring(0,2)]+dealDetailDayNum(newLunarRecentFM.substring(2,4));//获取最近新历节日对应农历的月份及天的数字形式
 /*   console.info("新历节日的农历时间"+numNewLunarRecentFM);
    console.info("农历节日的时间"+lunarRecentFestival);*/
    var recentFestivalDate="";
    var recentFestivalIsLunar="";
    if(lunarRecentFestival.substring(0,4)=="diff"&&currentYearLunar.substring(0,6)==newLunarRecentF.substring(0,6)){//新历最近的节日在同今年，农历的在上一年
		recentFestivalDate=lunarRecentFestival.substring(4,8);
		recentFestivalIsLunar=true;
    }else if(lunarRecentFestival.substring(0,4)!="diff"&&yesterYearLunar.substring(0,6)==newLunarRecentF.substring(0,6)){//农历的节日在今年，新历的在上一年
    	recentFestivalDate=newRecentFestival;
		recentFestivalIsLunar=false;
    }else{//同在今年或上一年
    	lunarRecentFestival=lunarRecentFestival.substring(0,4)=="diff"?lunarRecentFestival.substring(4,8):lunarRecentFestival;
    	recentFestivalDate=parseInt(numNewLunarRecentFM)<=parseInt(lunarRecentFestival)?newRecentFestival:lunarRecentFestival;//获得离当前时间最近的节日  
    	recentFestivalIsLunar=parseInt(numNewLunarRecentFM)<=parseInt(lunarRecentFestival)?false:true;//最近节日是否为农历节日
    } 
  /*  console.info("最近的节日时间:"+recentFestivalDate);
    console.info("最近的节日是不是农历:"+recentFestivalIsLunar);*/
    if(recentFestivalIsLunar){
         object.lunarRecentFestivalDate=exchangeNumToWord(recentFestivalDate); 
         object.newRecentFestivalDate=LunarToNew(recentFestivalDate,numMonthAndDay); 
    	 object.festival=festivalName(lFtv,recentFestivalDate);//获取节日名称
    }else{
    	object.newRecentFestivalDate= deleteZero(recentFestivalDate.substring(0,2))+"月"+deleteZero(recentFestivalDate.substring(2,4))+"日";
    	object.lunarRecentFestivalDate=newLunarRecentFM;
    	object.festival=festivalName(sFtv,newRecentFestival);
    }  
    return object;
}

/**
 * lunarRecentFestival:最近节日的农历月日 如：0505
 * numMonthAndDay:当前时间的农历月日  如：0504
 * 找最近农历节日对应的新历
 */
function LunarToNew(lunarRecentFestival,numMonthAndDay){
	/*console.info("最近节日的农历月日"+lunarRecentFestival);
	console.info("当前时间的农历月日"+numMonthAndDay);*/
	var date=new Date();
	var year=date.getFullYear();
	var currentMonth=date.getMonth()+1;
	var day=date.getDate();
	if(lunarRecentFestival.substring(0,2)==numMonthAndDay.substring(0,2)){//最近节日的农历月日与当前时间的农历是否在同一个月
		var intervalDays=parseInt(deleteZero(lunarRecentFestival.substring(2,4)))-parseInt(deleteZero(numMonthAndDay.substring(2,4)));
	    //console.info("间隙时间"+intervalDays);
		var newTNMonth=currentMonth;//农历节日对应新历的月份
		var daysOfMonth=getDaysOfMonth(year,currentMonth,0);
	    var detailDate=intervalDays+day;//新历的当前日期加节假日离当前的天数 即：对应新历的具体时间
		
	    while(detailDate>daysOfMonth){//新历具体时间是否大于这个月的总天数
			newTNMonth+=1;
			detailDate-=daysOfMonth;
			currentMonth+=1;
			daysOfMonth=getDaysOfMonth(year,currentMonth,0);
		}
		return newTNMonth+"月"+detailDate+"日";
	}else{ 
		var newTolunar="";
		var daysOfMonth=getDaysOfMonth(year,currentMonth,0);
		while(lunarRecentFestival!=numMonthAndDay){
			if((day+1)<=daysOfMonth){
				//var yesterdayLunar=GetLunarDay(year,currentMonth,day);
			    day+=1;
			    var currentLunar=GetLunarDay(year,currentMonth,day);
				newTolunar=currentLunar.split("年")[1].trim();   
				numMonthAndDay=month[newTolunar.substring(0,2)]+dealDetailDayNum(newTolunar.substring(2,4));
				/*if(yesterdayLunar.split("年")[0].trim()!=currentLunar.split("年")[0].trim())
				{numMonthAndDay="0100";day-=1;}*/
			}else{
				//var yesterdayLunar=GetLunarDay(year,currentMonth,day);
				currentMonth+=1;
				day=0;
				day+=1;
				daysOfMonth=getDaysOfMonth(year,currentMonth,0);
				var currentLunar=GetLunarDay(year,currentMonth,day);
				newTolunar=currentLunar.split("年")[1].trim();
				numMonthAndDay=month[newTolunar.substring(0,2)]+dealDetailDayNum(newTolunar.substring(2,4));
				/*if(yesterdayLunar.split("年")[0].trim()!=currentLunar.split("年")[0].trim())
				{numMonthAndDay="0100";day-=1;}*/
			}
			
		}
		return currentMonth+"月"+day+"日";
	}
}
/**
 * 获取当月的天数
 */
function getDaysOfMonth(year,month,date){
	var date=new Date(year,month,date);
	return date.getDate();
}
//处理初十及二十两个特殊数字
function dealDetailDayNum(dayNum){
	  var day=dayNum==="初十"?"10":(dayNum==="二十"?"20":(decade[dayNum.substring(0,1)]+unit[dayNum.substring(1,2)]));
      return day;
}
//将节日的农历数字转换成文字
function exchangeNumToWord(dateNum){
	var dateWord="";
	if(dateNum=='0100'){
		
	}
	for(var i in month){
		if(month[i]==dateNum.substring(0,2))
			dateWord+=i;
	}	
	if(dateNum.substring(2,4)=='10')
		return dateWord+'初十';
	else if(dateNum.substring(2,4)=='20')
		return dateWord+"二十";
	else {
		for(var i in decade){
		if(decade[i]==dateNum.substring(2,3))
		       dateWord+=i;
        }
		for(var i in unit){
			if(unit[i]==dateNum.substring(3,4))
				dateWord+=i;
		}
	}	
	return dateWord;
}
//获得节日名称
function festivalName(object,numMonthAndDay){
	for(var i=0;i<object.length;i++){
		for(var j in object[i]){
			if(parseInt(j)==parseInt(numMonthAndDay)){
				return object[i][j];
			}
		}
	}
}
/**
 * object：所有新历或农历节日
 * dateNum：当前时间月日的数字形式
 * lunarDateNum：当前时间农历月日的数字形式
 *找新历或农历离当前最近的节日 
 */
function RecentFestival(object,dateNum,lunarDateNum){
	var numMonthAndDay=(lunarDateNum===""?dateNum:lunarDateNum);
	var result="";
    firstStep: for(var m=0;m<object.length;m++){
    	for(var n in object[m]){
		   if(lunarDateNum!=""&&parseInt(n)>=parseInt(numMonthAndDay)){//邻近当前时间的农历节日   
			   if(currentYearLunar.substring(0, 6)!=currentDateLunar.substring(0, 6)){
				   result="diff"+n;
			   }else{
				   result=n;
			   }
			   break firstStep;
			}else if(lunarDateNum==""&&parseInt(n)>=parseInt(numMonthAndDay)){//当前时间的新历与今年的节日比较
				return n;
			}
	    }
	}
    if(result==""){
    	for(var m=0;m<object.length;m++){
        	for(var n in object[m]){
    		   if(parseInt((new Date()).getFullYear()+n)>=parseInt((new Date()).getFullYear()-1+numMonthAndDay)){//获取不在同一年的临近节日
    			   return n;
    			}
    	    }
    	}
    }
    return result;
}
/**
 * 处理月日的带0的情况
 */
function deleteZero(str){
	if(str.charAt(0)=='0'){
		return str.charAt(1);
	}else
		return str;
	
}