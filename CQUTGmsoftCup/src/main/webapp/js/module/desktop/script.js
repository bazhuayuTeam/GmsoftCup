
$(function(){
	createSelectYear();  //创建年份下拉,并给对应事件
	createMonthSelect();  //创建月份下拉，并给对应事件
	//根据年，月，用table绘制日历。 年月变动则 重新绘制
	createTabledate(parseInt(withID("aboluo-yearSelect").value),parseInt(withID("aboluo-selectmonth").value));
	//detailDateHoverEvent();
});

//阻止冒泡
function stopBubble(e){
	/*if(e && e.stopPropagation){// 别的浏览器
		e.stopPropagation();
	}else{ //IE
		window.event.cancelBubble=true;
	}*/
}	
//定义了yearselect并赋值,且添加事件，选择则对应的table日期也将改变,且已选中日期会跳到当前选择月的日期，然后给右边明细栏赋值
function createSelectYear(){
	withClass("aboluo-calendar-select-year").innerHTML='<select name="aboluo-yearSelect" id="aboluo-yearSelect"></select>';
	var yearSelect= withID("aboluo-yearSelect");
	var Nowtime=new Date();
	var currYear=Nowtime.getFullYear();
	for(var i=0;i<=79;i++){
		yearSelect.options.add(new Option((i+1970)+"年",i+1970));
		if(currYear==i+1970){
		yearSelect.options[i].selected=true;
		}
	}
	yearSelect.onchange=function(e){
		var aclick=withClass("aboluo-aclick");
		//重新赋值给变全局变量,所有的带状态的日期;然后下一步将创建table,完成动态样式,
		//这里要重读数据就5个位置,选择年时,上一个月,下一个月,设置节假日button,返回今天button
		getjjrszModelByYear(withID("aboluo-yearSelect").value);
		createTabledate(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
		if(aclick==""){
			//说明没选,或选的当天,算出选的这个月有多少天,与原来的那个月的天数一对比,如果原来的天数大于现在的天数,那么对换
			//这里先算当前月当前天,然后算出选择的那个月总天数,然后对比,如果当前天大于选择的那个月那天,对换
		 var pervdays1=getCurrMonthLashDay(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
		    	if(new Date().getDate()>pervdays1){
					setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,pervdays1);	
				}else{
					setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,new Date().getDate());
				}
		}else{
			var adate=aclick.getAttribute("date");
			var aarr=adate.split("-");
			aarr[0]=parseInt(aarr[0]);
			aarr[1]=parseInt(aarr[1]);
			aarr[2]=parseInt(aarr[2]);
			var pervdays=getCurrMonthLashDay(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
			if(aarr[2]>pervdays){
				aarr[2]=pervdays;
			}
				setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,aarr[2]);	
		}
		
	};
}

function getjjrszModelByYear(year){
	jjrmodelidlist=['1','2'];
	jjrmodeltimelist=['2015-08-30 00:00:00','2015-08-31 00:00:00']; //这里时间的格式为yyyy-MM-dd HH:mm:ss
	jjrmodelztlist=['1','2'];  //1为上班，2为休息
}


//创建月的下拉框，并赋值,且添加事件，选择则对应的table日期也将改变,且已选中日期会跳到当前选择月的日期，然后给右边明细栏赋值
function createMonthSelect(){
	var selectmonth=newElement('select');
	selectmonth.name="aboluo-selectmonth";
	selectmonth.id="aboluo-selectmonth";
	selectmonth.onchange=function(e){
		withID("monthOfEnglish").innerHTML=getMon(withID("aboluo-selectmonth").value);//添加英文月份
		var aclick=withClass("aboluo-aclick");
		createTabledate(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
		if(aclick==""){
			//说明没选,或选的当天,算出选的这个月有多少天,与原来的那个月的天数一对比,如果原来的天数大于现在的天数,那么对换
			//这里先算当前月当前天,然后算出选择的那个月总天数,然后对比,如果当前天大于选择的那个月那天,对换
		 var pervdays1=getCurrMonthLashDay(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
		    	if(new Date().getDate()>pervdays1){
					setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,pervdays1);	
				}else{
					setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,new Date().getDate());
				}
		}else{
			withID("monthOfEnglish").innerHTML=getMon(withID("aboluo-selectmonth").value);//添加英文月份
			var adate=aclick.getAttribute("date");
			var aarr=adate.split("-");
			aarr[0]=parseInt(aarr[0]);
			aarr[1]=parseInt(aarr[1]);
			aarr[2]=parseInt(aarr[2]);
			var pervdays=getCurrMonthLashDay(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
			if(aarr[2]>pervdays){
				aarr[2]=pervdays;
			}
				setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,aarr[2]);	
		}
	
	
	
	};
	var Nowtime=new Date();
	var currMonth=Nowtime.getMonth();
    for(var i=0;i<12;i++){
		selectmonth.options.add(new Option((i+1)+"月",i+1));
		if(currMonth==i){
			selectmonth.options[i].selected=true;
		}
	}
    //var next=withClass("aboluo-month-a-next");
    var parent=withClass("aboluo-calendar-month");
    parent.appendChild(selectmonth);
    withID("monthOfEnglish").innerHTML=getMon(withID("aboluo-selectmonth").value);//添加英文月份
}


//根据传入的年月，创建对应的table日期,并且在每个td中创建a标签用于事件，与样式内边框的设置
function createTabledate(year,yue){
	var rilitabledele=withClass("aboluo-rilitbody");
	if(rilitabledele!="" && rilitabledele!=null && rilitabledele!='undefined'){
	rilitabledele.parentNode.removeChild(rilitabledele);
	}
	var rilitable=newElement('tbody');
	rilitable.setAttribute("class","aboluo-rilitbody");
	var rili=withClass("aboluo-rilitable");
	rili.appendChild(rilitable);
	//先得到当前月第一天是星期几,然后根据这个星期算前面几天的上个月最后几天.
	var date=setdateinfo(year,yue,1);
	var weekday=date.getDay();
	var pervLastDay;
	if(weekday!=0){
		pervLastDay=weekday-1;
	}else{
		pervLastDay=weekday+6;
	}
	//得到上个月最后一天;
	var pervMonthlastDay=getPervMonthLastDay(year,yue);
	//上月最后几天循环
	var lastdays=pervMonthlastDay-pervLastDay+1;
	var tr=newElement('tr');
	tr.style.borderBottom="1px solid #e3e4e6";
	for(var i=lastdays;i<=pervMonthlastDay;i++){
		var td=newElement("td");
		var a=getA(parseInt(yue)-1==0?parseInt(year)-1:year,parseInt(yue)-1==0?12:parseInt(yue)-1,i);
		a.style.color="#BFBFC5";
//		a.href ='javascript:pervA('+parseInt(yue)-1==0?parseInt(year)-1:year+','+parseInt(yue)-1==0?12:parseInt(yue)-1+','+i+');';
		td.appendChild(a);
		td.setAttribute("class","aboluo-pervMonthDays");
		tr.appendChild(td);
	}
	//这个月开始的循环
	var startDays=8-weekday==8?1:8-weekday;
	for(var i=1;i<=startDays;i++){
		var td=newElement("td");
		var b=getA(year,yue,i);
		td.appendChild(b);
		tr.appendChild(td);
	}
	rilitable.appendChild(tr);
	//指定年月最后一天
	var currMonthLashDay=getCurrMonthLashDay(year,yue);
	//当月除开第一行的起点
	var currmonthStartDay=currMonthLashDay-(currMonthLashDay-startDays)+1;
	//当月还剩余的天数
	var syts=currMonthLashDay-startDays;
	//循环次数
	var xhcs=0;
	if(check(syts/7)){
	//是小数
	xhcs=Math.ceil(syts/7);//向上取整
	}else{
	xhcs=syts/7;	
	}
	
	//这是下个月开始的变量;
	var jilvn=1;
	for(var i=0;i<xhcs;i++){
		var tr1=newElement('tr');
		tr1.style.borderBottom="1px solid #e3e4e6";
		for(var n=1;n<=7;n++){
			var td=newElement('td');
			if(startDays==0){
				var c=getA(parseInt(yue)+1==parseInt(13)?parseInt(year)+1:year,parseInt(yue)+1==parseInt(13)?1:parseInt(yue)+1,jilvn);
				c.style.color="#BFBFC5";
				td.appendChild(c);
				td.setAttribute("class","aboluo-nextMonthDays");
				jilvn++;
				tr1.appendChild(td);
				continue;
			}else{
			startDays++;
			var d=getA(year,yue,startDays);
			td.appendChild(d);
				if(startDays==currMonthLashDay){
					startDays=0;
				}
			tr1.appendChild(td);	
			}
		
		}
		rilitable.appendChild(tr1);
	}
	setHolidayred();//设置星期六星期天的样式
	setTrHeight();//设置table日期的行高
	setA(); //设置td中a的事件
}



//给上一个月最后几天点击跳转月份
function pervA(year,yue,day){
	createTabledate(year,yue);  //创建对应的table(日期)
	setRigth(year,yue,day);    //设置右边明细栏内容
	updateSelect(year,yue);    //改变年月select值
}

//给上一个月最后几天点击跳转月份
function nextA(year,yue,day){
	createTabledate(year,yue);
	setRigth(year,yue,day);
	updateSelect(year,yue);
}

function updateSelect(year,yue){
	var selectmonth=withID("aboluo-selectmonth");
	var selectyear=withID("aboluo-yearSelect");
	selectmonth.value=yue;
	selectyear.value=year;
}



//遍历table将date事件等
function setHolidayred(){
	var rows=withClass("aboluo-rilitbody").rows;
	for(var i=0;i<rows.length;i++){
		for(var j=0;j<rows[i].cells.length;j++){
			var cell=rows[i].cells[j];
			var a=rows[i].cells[j].childNodes[0];
			var adate=a.getAttribute("date");
			var arr=adate.split("-");
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth();
			var day=date.getDate();
			if(arr[0]==year && arr[1]==month+1 && arr[2]==day){
				cell.setAttribute("class","aboluo-tdcurrToday");
				a.setAttribute("class","aboluo-currToday");
			}
			/*if(j>=rows[i].cells.length-2 ){
				if(cell.getAttribute("class")!="aboluo-nextMonthDays" && cell.getAttribute("class")!="aboluo-pervMonthDays"){
					a.style.color="red";
				}
			}*/
		}
	}
}

//给rightdiv创建元素并赋值，根据传入的年月日给内部的元素赋值 ,月份是 1-12
function setRigth(year,yue,day){

}

function formatByYearyueday(year,yue,day){
	year=year.toString();
	yue=yue.toString();
	day=day.toString();
	return year+"-"+(yue.length<2?'0'+yue:yue)+"-"+(day.length<2?'0'+day:day);
}

function formatByDate(date){
	date=date.substring(0,10);
	var daxx=date.toString().split("-");
	return daxx[0]+"-"+(daxx[1].length<2?'0'+daxx[1]:daxx[1])+"-"+(daxx[2].length<2?'0'+daxx[2]:daxx[2]);
}

//给tbody中的td中的A设置事件，上个月的天数,这个月的天数,下个月的天数三种对应的事件
function setA(){
	var tbody=withClass("aboluo-rilitbody");
	var arr=tbody.getElementsByTagName("a");
	for(var i=0;i<arr.length;i++){
		var date=arr[i].getAttribute("date");
		var datearr=date.split("-");
		if(arr[i].parentNode.className=="aboluo-pervMonthDays"){
		arr[i].setAttribute("onclick","javascript:pervA("+datearr[0]+","+datearr[1]+","+datearr[2]+",this);javascript:stopBubble(this);")
		}else if(arr[i].parentNode.className=="aboluo-nextMonthDays"){
			arr[i].setAttribute("onclick","javascript:nextA("+datearr[0]+","+datearr[1]+","+datearr[2]+",this);javascript:stopBubble(this);")	
		}else{
		arr[i].setAttribute("onclick","javascript:setRigth("+datearr[0]+","+datearr[1]+","+datearr[2]+");javascript:stopBubble(this);");
		}
	}
}

//a点击选中样式,先清除再设置选中样式
function setaclass(year,yue,day){
	var a=withClass("aboluo-aclick");
		a.className="";
		var date=new Date();
		var year1=date.getFullYear();
		var month1=date.getMonth();
		var day1=date.getDate();
		if(year1==year && yue==month1+1 && day1==day){
		}else{
			var tbody=withClass("aboluo-rilitbody");
			var arr=tbody.getElementsByTagName("a");
			for(var i=0;i<arr.length;i++){
				var date=arr[i].getAttribute("date");
				var datearr=date.split("-");
				if(datearr[0]==year && datearr[1]==yue && datearr[2]==day){
					arr[i].setAttribute("class","aboluo-aclick");
				}
			}
		}

}


//获取当前选取的日期
function getAclickDomDate(){
	var aclick=withClass("aboluo-aclick");
	if(aclick==""){
		//说明没选,那么就给当天,按12月算
		var date=new Date();
		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}else{
		return aclick.getAttribute("date");
	}
}

//获取当前选中的a元素
function getAclickDom(){
	var aclick=withClass("aboluo-aclick");
	if(aclick==""){
		//说明没选,那么就给当天,按12月算
		return withClass("aboluo-currToday");
	}else{
		return aclick;
	}
}


//创建元素
function newElement(val){
	return document.createElement(val);
}

//创建date对象并赋值
function setdateinfo(year,yue,day){
	var date=new Date();
	date.setFullYear(parseInt(year));
	date.setMonth(parseInt(yue)-1);
	date.setDate(parseInt(day));
	return date;
}

//根据年月日判断是不是星期六星期天 //yue 按12算
function isweekend(year,yue,day){
	var date=new Date();
	date.setFullYear(year);
	date.setMonth(yue-1);
	date.setDate(day);
	var week=date.getDay();
	if(week==0 || week==6){
		return true;
	}
	return false;
}

//根据getDay()返回对应的星期字符串
function getWeek(val){
	var weekxq=new Array();
	weekxq[0]="星期日";
	weekxq[1]="星期一";
	weekxq[2]="星期二";
	weekxq[3]="星期三";
	weekxq[4]="星期四";
	weekxq[5]="星期五";
	weekxq[6]="星期六";
	return weekxq[val];
}

//根据getMon()返回对应月份的英语名称
function getMon(val){
	var monName = new Array("January", "February", "March", 
	"April", "May", "June", "July", "August", "September",
	"October", "November", "December");
	return monName[val-1];
}

//判断c是否是小数
function check(c){
	var r=/^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
	return r.test(c);
}

//得到指定月的上个月最后一天传进来按 12月算
function getPervMonthLastDay(year,yue){
	//当月就是  yue-1 也就是计算机里面的0-11月份,那么算上个月的最后一天就是当月的0天
	return parseInt(new Date(year,yue-1,0).getDate());
}

//得到指定月最后一天 传进来按 12月算
function getCurrMonthLashDay(year,yue){
	if(yue>=12){
		year=year+1;
		yue=yue-12;
	}
	return parseInt(new Date(year,yue,0).getDate());
}


//创建a标签并设置公用属性
function getA(year,yue,day){
	var a=newElement("a");
	a.href="javascript:;";
	a.innerHTML=day;
	a.style.textDecoration="none";
	a.setAttribute("date",year+"-"+yue+"-"+day);
	return a;
}

//动态设置tr高度,动态给td中的A设置高度与宽度
function setTrHeight(){
	var table=withClass("aboluo-rilidiv");
	var thead=withClass("aboluo-rilithead");
	var tbody=withClass("aboluo-rilitbody");
	var tbodyheight=table.offsetHeight-thead.offsetHeight;
	var rows=tbody.getElementsByTagName('tr');
	for(var i=0;i<rows.length;i++){
		rows[i].style.height=(tbodyheight/rows.length-2)+"px";
		var tds=rows[i].getElementsByTagName("td");
		for(var j=0;j<tds.length;j++){
			var a=tds[j].childNodes[0];
			a.style.width=(tds[j].offsetWidth-10)+"px";
			a.style.height=(tds[j].offsetHeight-5)+"px";
			a.style.lineHeight=(tds[j].offsetHeight-5)+"px";
		}
	}
}
//得到id对象
function withID(id){
	return document.getElementById(id);
}
//得到传入参数为class的对象(同名返回第一个)
function withClass(id){
	var targets= targets ||  document.getElementsByTagName("*");
	var list=[];
	for(var k in targets){
		var target=targets[k];
		if(target.className==id){
			return target;
		}
	}
	return "";
}
//为日历插件添加hover事件
function detailDateHoverEvent(){	
	$(".aboluo-rilidiv .aboluo-rilitable tr td a").hover(
		function(){
			 var currentDate=$(this).attr("date").toString();//获取hover选中日期
			 var currentDateArr=currentDate.split("-");
			 var lunarDay=GetLunarDay(currentDateArr[0],currentDateArr[1],currentDateArr[2]);//获取农历
			 
			 $(this).append("<div class='detailDate_hover'><img alt='hover图标' src='images/module/desktop/calendar_hover.png' /><label style='margin-top:-60px;font-size:11px;width:45px'>"+lunarDay+"<label></div>");
		     $(this).find(".detailDate_hover").css("margin-top","-75px");
		},
		function(){
			 $(this).find(".detailDate_hover").remove();
		}
	);
}